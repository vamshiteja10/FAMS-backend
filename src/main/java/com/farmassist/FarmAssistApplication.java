package com.farmassist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmAssistApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmAssistApplication.class, args);
        System.out.println("=================================================");
        System.out.println("   FarmAssist Backend Started Successfully!      ");
        System.out.println("   REST APIs: http://localhost:8080/api          ");
        System.out.println("=================================================");
    }
}
