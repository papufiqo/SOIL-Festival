package com.soil.festival.service;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

@Service
public class MQTTService {
    private final String broker = "tcp://mosquitto:1883"; // from docker-compose
    private final String clientId = "SoilFestivalBackend";

    public MQTTService() {
        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.connect();
            client.subscribe("sensors/+/data", (topic, msg) -> {
                String payload = new String(msg.getPayload());
                System.out.println("Soil data received: " + payload);
                // TODO: parse JSON and save to DB
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
