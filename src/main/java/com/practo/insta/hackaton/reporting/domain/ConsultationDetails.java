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
public class ConsultationDetails {

    protected String mrNo;

    protected String visitId;

    protected String visitType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime consultationDateTime;

    protected String patientGender;

    protected Integer centerId;

    protected String centerName;

    protected Integer waitTime;

    protected Integer consultationTime;

    protected String status;

    protected String deptId;

    protected String deptName;

    protected String doctorId;

    protected String doctorName;

    protected String doctorSpecialization;

    protected String cityId;

    protected String cityName;

    protected String stateId;

    protected String stateName;

    protected String countryId;

    protected String countryName;
}
