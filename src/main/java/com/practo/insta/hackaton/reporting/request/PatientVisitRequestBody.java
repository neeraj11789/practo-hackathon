package com.practo.insta.hackaton.reporting.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientVisitRequestBody {

    @DateTimeFormat(iso = DATE_TIME)
    LocalDateTime fromDatetime;

    @DateTimeFormat(iso = DATE_TIME)
    LocalDateTime toDatetime;
}
