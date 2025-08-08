package com.demo;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AppService {

    private final AppProperties appProperties;

    public AppService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @PostConstruct
    public void printConfig() {
        System.out.println("App name: " + appProperties.getName());
        System.out.println("Version: " + appProperties.getVersion());
        System.out.println("Features: " + appProperties.getFeatures());
        System.out.println("Settings: " + appProperties.getSettings());
    }
}