package com.example.bajaj.dto;

public class WebhookGenerationRequest {

    private String name;
    private String regNo;
    private String email;

    public WebhookGenerationRequest() {
    }

    public WebhookGenerationRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
