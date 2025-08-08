package com.demo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AppProperties {
    private String name;
    private String version;
    private List<String> features;
    private Map<String, Object> settings;
}