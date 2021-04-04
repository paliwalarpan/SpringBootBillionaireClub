package com.club.billionaire.initializers;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ConfigurationProperties(prefix = "forbes400.billionaire")
@Validated
@ConstructorBinding
public class Forbes400Properties {

    @NotNull
    @NotEmpty
    @URL
    private final String api;

    @Positive
    private final int maxrecord;

    public Forbes400Properties(@NotNull @NotEmpty @URL String api, @Positive int maxrecord) {
        this.api = api;
        this.maxrecord = maxrecord;
    }

    public String getApi() {
        return api;
    }

    public int getMaxrecord() {
        return maxrecord;
    }

    public String buildEndPoint() {
        return api + "?limit=" + maxrecord;
    }


}
