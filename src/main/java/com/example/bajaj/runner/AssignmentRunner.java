package com.example.bajaj.runner;

import com.example.bajaj.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner that triggers the assignment workflow automatically on startup.
 * This component is automatically invoked by Spring Boot during application initialization.
 */
@Component
public class AssignmentRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentRunner.class);

    @Autowired
    private AssignmentService assignmentService;

    /**
     * Executes on application startup.
     * Calls the AssignmentService to execute the complete workflow.
     *
     * @param args command line arguments passed to the application
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("Application started. Initiating assignment workflow...");
        
        try {
            // Execute the main assignment workflow
            assignmentService.executeAssignment();
        } catch (Exception e) {
            logger.error("Assignment workflow encountered an error: {}", e.getMessage(), e);
            // Continue running the application even if workflow fails
            logger.warn("Application will continue running despite workflow error");
        }
    }
}
