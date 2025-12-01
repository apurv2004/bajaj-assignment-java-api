package com.example.bajaj.runner;

import com.example.bajaj.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AssignmentRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentRunner.class);

    @Autowired
    private AssignmentService assignmentService;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Application started. Initiating assignment workflow...");
        
        try {
            assignmentService.executeAssignment();
        } catch (Exception e) {
            logger.error("Assignment workflow encountered an error: {}", e.getMessage(), e);
            logger.warn("Application will continue running despite workflow error");
        }
    }
}
