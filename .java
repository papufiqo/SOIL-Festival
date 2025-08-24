// SensorData.java - Entity class for sensor data
public class SensorData {
    private String sensorId;
    private double moisture;
    private double temperature;
    private double nitrogen;
    private double phosphorus;
    private double potassium;
    private double phLevel;
    private long timestamp;
    
    // Constructors, getters, and setters
    public SensorData() {}
    
    public SensorData(String sensorId, double moisture, double temperature, 
                     double nitrogen, double phosphorus, double potassium, 
                     double phLevel, long timestamp) {
        this.sensorId = sensorId;
        this.moisture = moisture;
        this.temperature = temperature;
