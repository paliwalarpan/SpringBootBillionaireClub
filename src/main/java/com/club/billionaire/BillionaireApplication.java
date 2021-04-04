package com.club.billionaire;

import com.club.billionaire.initializers.Forbes400Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@Import(SwaggerConfig.class)
@EnableConfigurationProperties(Forbes400Properties.class)
public class BillionaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillionaireApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

}
