package com.practo.insta.hackaton.reporting.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeDeserializer;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PatientDetails {

    protected String mrNo;

    protected String visitId;

    protected String visitType;

    protected String status;

    protected Doctor doctor;

    protected Department department;

    protected Center center;

    protected Patient patient;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime registrationDateTime;
}
