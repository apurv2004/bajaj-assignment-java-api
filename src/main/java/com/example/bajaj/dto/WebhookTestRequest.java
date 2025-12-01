package com.example.bajaj.dto;

/**
 * DTO for the webhook test API request.
 * Contains the final SQL query to be submitted to the second API.
 */
public class WebhookTestRequest {

    private String finalQuery;

    public WebhookTestRequest() {
    }

    public WebhookTestRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}
