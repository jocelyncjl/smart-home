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
import java.util.concurrent.TimeUnit;

public class SecurityClientGUI extends Application {
    private ConsulClient consulClient;
    private String consulServiceName;
    private TextArea textArea;

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

        Button startButton = new Button("Start Security");
        startButton.setOnAction(e -> makeSecurityRequest());

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(textArea, startButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
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
                // The client sends the command as it accepts danger warning info from the server
                if (!securityResponse.getWarnings().isEmpty()) {
                    appendText(securityResponse.getWarnings());
                    securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOnInstructs("Turn on the camera and alarm").build());
                }
                // The client accepts data from the server as it completes operation successfully
                if (!securityResponse.getOperations().isEmpty()) {
                    appendText(securityResponse.getOperations());
                }
                // The client accepts danger lift tips from the server and sends another command to the server
                if (!securityResponse.getDangerRemove().isEmpty()) {
                    appendText(securityResponse.getDangerRemove());
                    securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOffInstructs("Turn off the camera and alarm").build());
                }
                // The client accepts the successful operations from the server and client requests end
                if (!securityResponse.getOperationsTwo().isEmpty()) {
                    appendText(securityResponse.getOperationsTwo());
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

        // Shutdown the channel when done
        channel.shutdown();
    }

    private void appendText(String text) {
        textArea.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}