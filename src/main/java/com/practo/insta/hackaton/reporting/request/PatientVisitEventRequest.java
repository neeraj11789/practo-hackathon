package com.practo.insta.hackaton.reporting.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatientVisitEventRequest implements Serializable {

    protected String eventId = UUID.randomUUID().toString();

    protected String visitId;

    @DateTimeFormat(iso = DATE_TIME)
    protected LocalDateTime creationTime;

    protected LocalDateTime processingTime;

    protected LocalDateTime receivedTime = LocalDateTime.now();
}
