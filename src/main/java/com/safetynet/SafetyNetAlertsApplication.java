package com.safetynet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SafetyNetAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafetyNetAlertsApplication.class, args);
    }

}