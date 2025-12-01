package com.example.bajaj.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

/**
 * Configuration class for REST client setup.
 * Provides RestTemplate bean with custom settings for HTTP requests.
 */
@Configuration
public class AppConfig {

    /**
     * Configure RestTemplate with appropriate timeouts and error handling.
     *
     * @param builder RestTemplateBuilder for constructing RestTemplate
     * @return configured RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(20))
                .build();
    }
}
