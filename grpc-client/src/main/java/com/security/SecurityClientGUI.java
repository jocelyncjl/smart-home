package com.security;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SecurityClientGUI extends Application {
    private ConsulClient consulClient;
    private String consulServiceName;
    private TextArea textArea;
    private Button turnOnButton;
    private Button turnOffButton;

    private StreamObserver<HelloSecurityProto.SecurityRequest> securityRequestStreamObserver;

    public SecurityClientGUI() {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        consulServiceName = "security-service";

        consulClient = new ConsulClient(consulHost, consulPort);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Security Client");

        // Create UI components
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        turnOnButton = new Button("Turn on the camera and alarm");
        turnOnButton.setOnAction(e -> sendTurnOnRequest());

        turnOffButton = new Button("Turn off the camera and alarm");
        turnOffButton.setOnAction(e -> sendTurnOffRequest());
        turnOffButton.setDisable(true);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(textArea, turnOnButton, turnOffButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        makeSecurityRequest();
    }

    private void makeSecurityRequest() {
        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            appendText("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        // Pick the first healthy instance (you can implement a load balancing strategy here)
        HealthService healthService = healthServices.get(0);

        // Extract host and port from the service details
        String serverHost = healthService.getService().getAddress();
        int serverPort = healthService.getService().getPort();

        // Create a gRPC channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverHost, serverPort).usePlaintext().build();

        // Create a stub for the gRPC service
        HelloSecurityGrpc.HelloSecurityStub securityStub = HelloSecurityGrpc.newStub(channel);

        // The agency stub invokes the grpc method
        securityRequestStreamObserver = securityStub.securityService(new StreamObserver<HelloSecurityProto.SecurityResponse>() {
            @Override
            public void onNext(HelloSecurityProto.SecurityResponse securityResponse) {
                if (!securityResponse.getWarnings().isEmpty()) {
                    appendText(securityResponse.getWarnings());
                    turnOnButton.setDisable(false);
                }
                if (!securityResponse.getOperations().isEmpty()) {
                    appendText(securityResponse.getOperations());
                }
                if (!securityResponse.getDangerRemove().isEmpty()) {
                    appendText(securityResponse.getDangerRemove());
                    turnOnButton.setDisable(true);
                    turnOffButton.setDisable(false);
                }
                if (!securityResponse.getOperationsTwo().isEmpty()) {
                    appendText(securityResponse.getOperationsTwo());
                    turnOffButton.setDisable(true);
                    securityRequestStreamObserver.onCompleted();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                appendText("Error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                appendText("Security request completed.");
            }
        });
    }

    private void sendTurnOnRequest() {
        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOnInstructs("Turn on the camera and alarm").build());
    }

    private void sendTurnOffRequest() {
        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOffInstructs("Turn off the camera and alarm").build());
    }

    private void appendText(String text) {
        textArea.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}