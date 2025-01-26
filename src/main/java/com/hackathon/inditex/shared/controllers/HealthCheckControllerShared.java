package com.hackathon.inditex.shared.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class HealthCheckControllerShared {

    @GetMapping
    public String healthCheck() {
        return "API is working";
    }
}