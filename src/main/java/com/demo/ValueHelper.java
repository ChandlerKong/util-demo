package com.demo;

import org.springframework.stereotype.Component;

@Component("valueHelper")
public class ValueHelper {
    public String getMessage() {
        return "来自 Bean 的信息";
    }
}