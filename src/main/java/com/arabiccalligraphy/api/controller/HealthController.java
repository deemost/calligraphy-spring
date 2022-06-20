package com.arabiccalligraphy.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public Map<String, String > health() {
        return Collections.singletonMap("status", "ok");
    }
}
