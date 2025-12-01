package com.example.bajaj.service;

import com.example.bajaj.dto.WebhookGenerationRequest;
import com.example.bajaj.dto.WebhookGenerationResponse;
import com.example.bajaj.dto.WebhookTestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class AssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String WEBHOOK_GENERATION_URL = 
        "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    
    private static final String WEBHOOK_TEST_URL = 
        "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    private static final String USER_NAME = "Apurv Kulkarni";
    private static final String REG_NO = "22BCE9252";
    private static final String USER_EMAIL = "apurv.22bce9252@vitapstudent.ac.in";

    private static final String FINAL_SQL_QUERY = "WITH high_earners AS (SELECT e.EMP_ID, e.FIRST_NAME, e.LAST_NAME, e.DOB, e.DEPARTMENT FROM EMPLOYEE e WHERE EXISTS (SELECT 1 FROM PAYMENTS p WHERE p.EMP_ID = e.EMP_ID AND p.AMOUNT > 70000)), with_rank AS (SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, h.EMP_ID, h.FIRST_NAME, h.LAST_NAME, h.DOB, ROW_NUMBER() OVER (PARTITION BY d.DEPARTMENT_ID ORDER BY h.FIRST_NAME, h.LAST_NAME) AS rn FROM high_earners h JOIN DEPARTMENT d ON d.DEPARTMENT_ID = h.DEPARTMENT) SELECT DEPARTMENT_NAME, AVG(TIMESTAMPDIFF(YEAR, DOB, CURDATE())) AS AVERAGE_AGE, GROUP_CONCAT(CASE WHEN rn <= 10 THEN CONCAT(FIRST_NAME, ' ', LAST_NAME) END ORDER BY FIRST_NAME, LAST_NAME SEPARATOR ', ') AS EMPLOYEE_LIST FROM with_rank GROUP BY DEPARTMENT_ID, DEPARTMENT_NAME ORDER BY DEPARTMENT_ID DESC;";

    private String generatedWebhook;
    private String accessToken;

    public void executeAssignment() {
        logger.info("========== Starting Bajaj Assignment Workflow ==========");
        
        try {
            generateWebhookAndToken();

            submitSqlQueryToWebhook();

            logger.info("========== Assignment Workflow Completed Successfully ==========");
        } catch (Exception e) {
            logger.error("Error during assignment execution: {}", e.getMessage(), e);
            logger.error("========== Assignment Workflow Failed ==========");
        }
    }

    private void generateWebhookAndToken() {
        logger.info("Step 1: Generating webhook and access token...");

        try {
            WebhookGenerationRequest request = new WebhookGenerationRequest(
                USER_NAME,
                REG_NO,
                USER_EMAIL
            );

            logger.info("Sending request to: {}", WEBHOOK_GENERATION_URL);
            logger.debug("Request body - Name: {}, RegNo: {}, Email: {}", 
                USER_NAME, REG_NO, USER_EMAIL);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookGenerationRequest> httpEntity = new HttpEntity<>(request, headers);

            ResponseEntity<WebhookGenerationResponse> response = restTemplate.postForEntity(
                WEBHOOK_GENERATION_URL,
                httpEntity,
                WebhookGenerationResponse.class
            );

            WebhookGenerationResponse responseBody = response.getBody();
            if (response.getStatusCode().is2xxSuccessful() && responseBody != null) {
                this.generatedWebhook = responseBody.getWebhook();
                this.accessToken = responseBody.getAccessToken();

                logger.info("✓ Webhook generated successfully");
                logger.info("Webhook: {}", this.generatedWebhook);
                logger.debug("Access Token: {}", this.accessToken);
            } else {
                logger.error("Unexpected response status: {}", response.getStatusCode());
            }
        } catch (RestClientException e) {
            logger.error("Failed to call webhook generation API: {}", e.getMessage(), e);
            throw new RuntimeException("Webhook generation failed", e);
        } catch (Exception e) {
            logger.error("Unexpected error during webhook generation: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error during webhook generation", e);
        }
    }

    private void submitSqlQueryToWebhook() {
        logger.info("Step 2: Submitting SQL query to webhook...");

        if (this.accessToken == null || this.accessToken.isEmpty()) {
            logger.error("Access token is not available. Cannot submit SQL query.");
            throw new RuntimeException("Access token is missing");
        }

        try {
            WebhookTestRequest request = new WebhookTestRequest(FINAL_SQL_QUERY);

            logger.info("Sending request to: {}", WEBHOOK_TEST_URL);
            logger.debug("SQL Query: {}", FINAL_SQL_QUERY);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", this.accessToken);

            HttpEntity<WebhookTestRequest> httpEntity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                WEBHOOK_TEST_URL,
                httpEntity,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("✓ SQL query submitted successfully");
                logger.info("Response: {}", response.getBody());
            } else {
                logger.warn("Response status: {}", response.getStatusCode());
                logger.warn("Response body: {}", response.getBody());
            }
        } catch (RestClientException e) {
            logger.error("Failed to call webhook test API: {}", e.getMessage(), e);
            throw new RuntimeException("Webhook test submission failed", e);
        } catch (Exception e) {
            logger.error("Unexpected error during SQL query submission: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error during SQL query submission", e);
        }
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getGeneratedWebhook() {
        return this.generatedWebhook;
    }
}
