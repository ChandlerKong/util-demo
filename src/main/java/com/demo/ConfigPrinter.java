package com.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigPrinter {

    private final DemoProperties demoProperties;
    private final UserProperties userProperties;

    public ConfigPrinter(DemoProperties demoProperties, UserProperties userProperties) {
        this.demoProperties = demoProperties;
        this.userProperties = userProperties;
    }

    @PostConstruct
    public void print() {
        System.out.println("== DemoProperties ==");
        System.out.println("Name: " + demoProperties.getName());
        System.out.println("Age: " + demoProperties.getAge());
        System.out.println("Tags: " + demoProperties.getTags());
        System.out.println("Metadata: " + demoProperties.getMetadata());
        System.out.println("City: " + demoProperties.getAddress().getCity());
        System.out.println("ZIP: " + demoProperties.getAddress().getZip());

        System.out.println("\n== UserProperties ==");
        System.out.println("Enabled: " + userProperties.isEnabled());
        System.out.println("Roles: " + userProperties.getRoles());
    }
}