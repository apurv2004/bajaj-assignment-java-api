package com.example.bajaj.dto;

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
