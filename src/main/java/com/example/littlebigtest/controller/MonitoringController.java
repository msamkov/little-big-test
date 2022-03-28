package com.example.littlebigtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    @Value("${info.app.version}")
    private String version;

    @GetMapping(value = "/monitoring/version")
    public String version() {
        return version;
    }

}
