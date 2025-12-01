package com.example.bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the webhook generation API response.
 * Maps the response from the first API call that provides webhook and accessToken.
 */
public class WebhookGenerationResponse {

    @JsonProperty("webhook")
    private String webhook;

    @JsonProperty("accessToken")
    private String accessToken;

    public WebhookGenerationResponse() {
    }

    public WebhookGenerationResponse(String webhook, String accessToken) {
        this.webhook = webhook;
        this.accessToken = accessToken;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
