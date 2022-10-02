package com.practo.insta.hackaton.reporting.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeDeserializer;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DiagnosisDetails {

    protected String mrNo;

    protected String visitId;

    protected String description;

    protected String icdCode;

    protected String codeType;

    protected String doctorId;

    protected String doctorName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime diagnosisDateTime;

    protected String gender;

    protected String patientName;

    protected String cityName;

    protected String stateName;

    protected String deptId;

    protected String deptName;

    protected String specialization;

    protected String centerName;

    protected Integer centerId;

    protected String visitType;
}
