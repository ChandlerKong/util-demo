package com.demo.api.downloadPDF.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

// 直接注入使用
@RestController
public class TestController {

    private final ThreadPoolExecutor executor;

    public TestController(@Qualifier("customThreadPoolExecutor") ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @GetMapping("/test")
    public String test() {
        executor.submit(() -> {
            System.out.println("Hello from custom thread pool");
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Bye Bye");
        });
        return "OK";
    }
}