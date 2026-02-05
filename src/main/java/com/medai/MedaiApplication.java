package com.medai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.medai")
public class MedaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedaiApplication.class, args);
    }

}
