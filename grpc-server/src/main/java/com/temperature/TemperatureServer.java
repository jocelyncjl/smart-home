package com.temperature;

import io.grpc.ServerBuilder;

public class TemperatureServer{
    public static void main(String[] args) {
        ServerBuilder serverBuilder = ServerBuilder.forPort(9000);
    }


}
