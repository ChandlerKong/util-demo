//package com.demo;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Component
//public class ValueDemoBean {
//
//    // 1. 基本类型
//    @Value("${demo.name}")
//    private String name;
//
//    @Value("${demo.age}")
//    private int age;
//
//    @Value("${demo.enabled}")
//    private boolean enabled;
//
//    @Value("${demo.threshold}")
//    private double threshold;
//
//    // 2. 默认值
//    @Value("${demo.missing:默认值}")
//    private String withDefault;
//
//    // 3. SpEL 表达式（常量、运算）
//    @Value("#{2 * 3}")
//    private int expressionResult;
//
//    @Value("#{T(Math).PI}")
//    private double pi;
//
//    @Value("#{T(Math).random()}")
//    private double random;
//
//    // 4. 读取 Environment 属性
//    @Value("#{environment['server.port']}")
//    private int serverPort;
//
//    // 5. List 分割
//    @Value("#{'${demo.tags}'.split(',')}")
//    private List<String> tags;
//
//    // 6. 注入 class 类型
//    @Value("${demo.clazz}")
//    private Class<?> clazz;
//
//    // 7. 注入其他 Bean 方法调用
//    @Value("#{valueHelper.getMessage()}")
//    private String beanMessage;
//
//
//    // 8. 注入其他 Bean （不推荐开发中使用，只是能这么用）
//    @Value("#{valueHelper}")
//    private ValueHelper valueHelper;
//
//    @PostConstruct
//    public void printAll() {
//        System.out.println("=== @Value Demo Bean ===");
//        System.out.println("name = " + name);
//        System.out.println("age = " + age);
//        System.out.println("enabled = " + enabled);
//        System.out.println("threshold = " + threshold);
//        System.out.println("withDefault = " + withDefault);
//        System.out.println("expressionResult = " + expressionResult);
//        System.out.println("pi = " + pi);
//        System.out.println("random = " + random);
//        System.out.println("serverPort = " + serverPort);
//        System.out.println("tags = " + tags);
//        System.out.println("clazz = " + clazz.getName());
//        System.out.println("beanMessage = " + beanMessage);
//        System.out.println("beanMessage01 = " + valueHelper.getMessage());
//        System.out.println("=========================");
//    }
//}