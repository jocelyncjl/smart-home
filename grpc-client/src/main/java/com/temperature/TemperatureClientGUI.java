package com.temperature;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;

public class TemperatureClientGUI extends Application {
    private ConsulClient consulClient;
    private String consulServiceName;
    private TextArea textArea;

    public TemperatureClientGUI() {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        consulServiceName = "temperature-service";

        consulClient = new ConsulClient(consulHost, consulPort);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Temperature Client");

        // Create UI components
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        Button requestButton = new Button("Get Temperature");
        requestButton.setOnAction(e -> makeTempRequest());

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(textArea, requestButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeTempRequest() {
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
        HelloTemperatureGrpc.HelloTemperatureBlockingStub tempService = HelloTemperatureGrpc.newBlockingStub(channel);

        try{
            // Prepare and send the unary request
            HelloTemperatureProto.TemperatureRequest.Builder builder = HelloTemperatureProto.TemperatureRequest.newBuilder();
            builder.setTempCall("Please help me monitor the temperature of different home areas at current time");
            HelloTemperatureProto.TemperatureRequest tempRequest = builder.build();
            Iterator<HelloTemperatureProto.TemperatureResponse> tempResponseIterator = tempService.temperatureService(tempRequest);

            // Process the response
            while (tempResponseIterator.hasNext()) {
                HelloTemperatureProto.TemperatureResponse tempResponse = tempResponseIterator.next();
                String homeArea = tempResponse.getHomeArea();
                double degreeCelsius = tempResponse.getDegreeCelsius();
                appendText("The temperature in the " + homeArea + " is " + degreeCelsius + "°C");
            }
        }catch(Exception e){
            appendText("Error: " + e.getMessage());
            e.printStackTrace();
        }

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