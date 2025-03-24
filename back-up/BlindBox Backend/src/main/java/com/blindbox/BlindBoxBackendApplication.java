package com.blindbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlindBoxBackendApplication implements CommandLineRunner {

    @Value("${jwt.secret:NOT_FOUND}") // If not found, it prints "NOT_FOUND"
    private String jwtSecret;

    public static void main(String[] args) {
        SpringApplication.run(BlindBoxBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("JWT Secret: " + jwtSecret);
    }

}
