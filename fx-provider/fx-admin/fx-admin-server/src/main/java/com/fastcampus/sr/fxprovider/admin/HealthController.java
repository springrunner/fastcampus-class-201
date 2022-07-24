package com.fastcampus.sr.fxprovider.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping({"/health", "/actuator/health"})
    public Map<String,String> health() {
        return Map.of("status", "OK");
    }
}
