package com.example.bajaj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class for Bajaj Assignment Application.
 * Starts the Spring Boot application and triggers the assignment workflow.
 */
@SpringBootApplication
public class BajajAssignmentApplication {

    private static final Logger logger = LoggerFactory.getLogger(BajajAssignmentApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Bajaj Assignment Application...");
        SpringApplication.run(BajajAssignmentApplication.class, args);
    }
}
