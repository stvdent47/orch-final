package mephi.orchfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class OrchFinalApplication {
    @GetMapping("/api/sentiment")
    public Map<String, String> analyze(@RequestParam(value = "text", defaultValue = "") String text) {
        String sentiment;
        if (text.toLowerCase().contains("bad")) {
            sentiment = "negative";
        }
        else if (text.toLowerCase().contains("good")) {
            sentiment = "positive";
        }
        else {
            sentiment = "neutral";
        }

        return Map.of("sentiment", sentiment);
    }

    public static void main(String[] args) {
        SpringApplication.run(OrchFinalApplication.class, args);
    }
}
