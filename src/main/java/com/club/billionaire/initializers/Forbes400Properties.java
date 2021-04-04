package com.club.billionaire.initializers;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Component
@ConfigurationProperties(prefix = "forbes400.billionaire")
@Validated
public class Forbes400Properties {

    @NotNull
    @NotEmpty
    @URL
    private String api;

    @Positive
    private int maxrecord;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getMaxrecord() {
        return maxrecord;
    }

    public void setMaxrecord(int maxrecord) {
        this.maxrecord = maxrecord;
    }

    public String buildEndPoint() {
        return api + "?limit=" + maxrecord;
    }


}
