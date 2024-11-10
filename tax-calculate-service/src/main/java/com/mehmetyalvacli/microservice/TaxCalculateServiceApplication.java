package com.mehmetyalvacli.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TaxCalculateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxCalculateServiceApplication.class, args);
    }

}
