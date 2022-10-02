package com.practo.insta.hackaton.reporting.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeDeserializer;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseDomain {

    @NonNull
    protected String id;

    protected String externalId = UUID.randomUUID().toString();

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime indexTime = LocalDateTime.now();
}
