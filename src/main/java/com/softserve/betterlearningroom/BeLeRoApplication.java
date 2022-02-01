package com.softserve.betterlearningroom;

import com.softserve.betterlearningroom.dao.impl.ClassroomDAOImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AutoConfigurationPackage
@SpringBootApplication
public class BeLeRoApplication {

    public static void main(String[] args) { SpringApplication.run(BeLeRoApplication.class, args); }

}