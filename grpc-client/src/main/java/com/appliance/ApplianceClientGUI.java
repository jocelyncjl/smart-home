package com.appliance;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplianceClientGUI extends Application {
    private ConsulClient consulClient;
    private String consulServiceName;
    private TextArea textArea;
    private TextField applianceNameField;
    private TextField applianceOperationField;

    public ApplianceClientGUI() {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        consulServiceName = "appliance-service";

        consulClient = new ConsulClient(consulHost, consulPort);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Appliance Client");

        // Create UI components
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        TextField[] applianceNameFields = new TextField[3];
        TextField[] applianceOperationFields = new TextField[3];

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(textArea);

        for (int i = 0; i < 3; i++) {
            applianceNameFields[i] = new TextField();
            applianceNameFields[i].setPromptText("Appliance Name " + (i + 1));

            applianceOperationFields[i] = new TextField();
            applianceOperationFields[i].setPromptText("Appliance Operation " + (i + 1));

            vbox.getChildren().addAll(applianceNameFields[i], applianceOperationFields[i]);
        }

        Button requestButton = new Button("Send Requests");
        requestButton.setOnAction(e -> {
            for (int i = 0; i < 3; i++) {
                String applianceName = applianceNameFields[i].getText();
                String applianceOperation = applianceOperationFields[i].getText();
                makeAppRequest(applianceName, applianceOperation);
            }
        });

        vbox.getChildren().add(requestButton);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeAppRequest(String applianceName, String applianceOperation) {
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
        HelloApplianceGrpc.HelloApplianceStub applianceStub = HelloApplianceGrpc.newStub(channel);

        // Process the response
        StreamObserver<HelloApplianceProto.ApplianceRequest> applianceRequestObserver = applianceStub.applianceService(new StreamObserver<HelloApplianceProto.ApplianceResponse>() {
            @Override
            public void onNext(HelloApplianceProto.ApplianceResponse applianceResponse) {
                // Obtain the message from the server
                appendText(applianceResponse.getOperationResponse());
            }

            @Override
            public void onError(Throwable throwable) {
                appendText("Error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                appendText("The appliance server responses end");
            }
        });

        // Prepare and send the request
        HelloApplianceProto.ApplianceRequest.Builder builder = HelloApplianceProto.ApplianceRequest.newBuilder();
        builder.setApplianceName(applianceName);
        builder.setApplianceOperation(applianceOperation);
        HelloApplianceProto.ApplianceRequest applianceRequest = builder.build();
        applianceRequestObserver.onNext(applianceRequest);

        applianceRequestObserver.onCompleted();

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