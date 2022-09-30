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
public class TestDetails {

    protected String mrNo;

    protected String visitId;

    protected String visitType;

    protected String centerId;

    protected String centerName;

    protected String patientGender;

    protected String testId;

    protected String testName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime testDate;

    protected String deptId;

    protected String deptName;

    protected String doctorId;

    protected String doctorName;

    protected String cityId;

    protected String cityName;

    protected String stateId;

    protected String stateName;

    protected String countryId;

    protected String countryName;

}
