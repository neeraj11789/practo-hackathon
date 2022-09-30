package com.practo.insta.hackaton.reporting.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class BaseDomain {

    @NotNull
    protected String id;

    protected String externalId = UUID.randomUUID().toString();

    protected LocalDateTime domainCreationTime = LocalDateTime.now();
}
