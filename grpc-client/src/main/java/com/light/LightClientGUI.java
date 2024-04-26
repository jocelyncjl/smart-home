package com.light;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.light.HelloLightGrpc;
import com.light.HelloLightProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class LightClientGUI extends Application {
    private ConsulClient consulClient;
    private String consulServiceName;
    private TextArea textArea;

    public LightClientGUI() {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        consulServiceName = "light-service";

        consulClient = new ConsulClient(consulHost, consulPort);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Light Client");

        // Create UI components
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        Button requestButton = new Button("Make Light Request");
        requestButton.setOnAction(e -> makeLightRequest());

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(textArea, requestButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeLightRequest() {
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
        HelloLightGrpc.HelloLightBlockingStub lightStub = HelloLightGrpc.newBlockingStub(channel);

        // Prepare and send the unary request
        HelloLightProto.LightRequest lightRequest = HelloLightProto.LightRequest.newBuilder()
                .setLightcall("Please help me increase the light brightness of the table lamp")
                .build();
        HelloLightProto.LightResponse lightResponse = lightStub.lightService(lightRequest);

        // Process the response
        String lightResult = lightResponse.getLightResult();
        appendText("Response: " + lightResult);

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