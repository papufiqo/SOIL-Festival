import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Random;

public class SensorSimulator {
    public static void main(String[] args) throws Exception {
        MqttClient client = new MqttClient("tcp://localhost:1883", "SoilSensorSimulator");
        client.connect();
        Random rnd = new Random();

        while (true) {
            String payload = String.format(
                "{\"sensorId\":\"demo1\",\"humidity\":%d,\"composition\":{\"N\":%d,\"P\":%d}}",
                40 + rnd.nextInt(20), rnd.nextInt(5), rnd.nextInt(5)
            );
            client.publish("sensors/demo1/data", new MqttMessage(payload.getBytes()));
            System.out.println("Published: " + payload);
            Thread.sleep(5000);
        }
    }
}
