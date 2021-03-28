package com.club.billionaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class BillionaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillionaireApplication.class, args);
    }

}
