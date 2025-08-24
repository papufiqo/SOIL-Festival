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
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.phLevel = phLevel;
        this.timestamp = timestamp;
    }
    
    // Getters and setters for all properties
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    public double getMoisture() { return moisture; }
    public void setMoisture(double moisture) { this.moisture = moisture; }
    
    // ... other getters and setters
}

// SoilQualityService.java - Service for calculating soil quality metrics
@Service
public class SoilQualityService {
    private static final double MOISTURE_WEIGHT = 0.25;
    private static final double NUTRIENT_WEIGHT = 0.45;
    private static final double PH_WEIGHT = 0.30;
    
    public SoilQualityAnalysis analyzeSoilQuality(SensorData sensorData) {
        SoilQualityAnalysis analysis = new SoilQualityAnalysis();
        
        // Calculate soil health index
        double nutrientScore = (sensorData.getNitrogen() * 0.4 + 
                               sensorData.getPhosphorus() * 0.3 + 
                               sensorData.getPotassium() * 0.3) / 100.0;
        
        double healthIndex = (sensorData.getMoisture() * MOISTURE_WEIGHT +
                            nutrientScore * NUTRIENT_WEIGHT +
                            (7.0 - Math.abs(7.0 - sensorData.getPhLevel())) * PH_WEIGHT) * 100;
        
        analysis.setHealthIndex(healthIndex);
        analysis.setSensorId(sensorData.getSensorId());
        analysis.setTimestamp(sensorData.getTimestamp());
        
        // Determine soil status
        if (healthIndex >= 80) {
            analysis.setStatus(SoilStatus.OPTIMAL);
        } else if (healthIndex >= 60) {
            analysis.setStatus(SoilStatus.HEALTHY);
        } else if (healthIndex >= 40) {
            analysis.setStatus(SoilStatus.NEEDS_ATTENTION);
        } else {
            analysis.setStatus(SoilStatus.POOR);
        }
        
        return analysis;
    }
}

// CircularEconomyService.java - Service for managing circular economy operations
@Service
public class CircularEconomyService {
    @Autowired
    private MaterialTrackingRepository materialRepository;
    
    @Autowired
    private IndustrialSymbiosisService symbiosisService;
    
    public void processMaterialExchange(String materialId, String fromCompany, 
                                       String toCompany, double quantity) {
        MaterialExchange exchange = new MaterialExchange();
        exchange.setMaterialId(materialId);
        exchange.setFromCompany(fromCompany);
        exchange.setToCompany(toCompany);
        exchange.setQuantity(quantity);
        exchange.setTimestamp(System.currentTimeMillis());
        exchange.setStatus(ExchangeStatus.COMPLETED);
        
        materialRepository.save(exchange);
        
        // Update sustainability metrics
        updateSustainabilityMetrics(materialId, quantity);
        
        // Check for symbiosis opportunities
        symbiosisService.checkForSymbiosisOpportunities(fromCompany, toCompany, materialId);
    }
    
    private void updateSustainabilityMetrics(String materialId, double quantity) {
        // Update recycling rates, waste reduction metrics, etc.
        // This would connect with the dashboard to update real-time metrics
    }
}

// CamaraApiService.java - Service for CAMARA API integration
@Service
public class CamaraApiService {
    @Value("${camara.api.base-url}")
    private String camaraBaseUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public NetworkSlice createNetworkSlice(String applicationId, QoDParameters parameters) {
        String url = camaraBaseUrl + "/network-slicing/slices";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());
        
        Map<String, Object> request = new HashMap<>();
        request.put("applicationId", applicationId);
        request.put("maxLatency", parameters.getMaxLatency());
        request.put("minBandwidth", parameters.getMinBandwidth());
        request.put("sliceType", "L4S_ENHANCED");
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        
        ResponseEntity<NetworkSlice> response = restTemplate.exchange(
            url, HttpMethod.POST, entity, NetworkSlice.class);
        
        return response.getBody();
    }
    
    private String getAccessToken() {
        // Implementation to get OAuth2 token for CAMARA API
        return "access-token";
    }
}
