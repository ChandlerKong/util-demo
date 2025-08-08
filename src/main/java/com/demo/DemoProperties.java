package com.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    @NotBlank
    private String name;

    @Min(18)
    private int age;

    private List<String> tags;

    private Map<String, String> metadata;

    @Valid
    private Address address;

    @Data
    public static class Address {
        @NotBlank
        private String city;

        private String zip;
    }
}