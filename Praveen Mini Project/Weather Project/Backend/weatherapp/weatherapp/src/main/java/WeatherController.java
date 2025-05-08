import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*") 
public class WeatherController {

    private final String apiKey = "aa429f7f03c5d3bee2e28ce50d785cd4";

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                     "&units=metric&appid=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<?, ?> response = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("City not found or error occurred.");
        }
    }
}
