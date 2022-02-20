package com.softserve.betterlearningroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@AutoConfigurationPackage
@SpringBootApplication
@EnableCaching
public class BeLeRoApplication {

    public static void main(String[] args) { SpringApplication.run(BeLeRoApplication.class, args); }

}