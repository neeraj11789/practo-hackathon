package com.practo.insta.hackaton.reporting.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeDeserializer;
import com.practo.insta.hackaton.reporting.util.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PatientVisit extends BaseDomain{

    protected String mrNo;

    protected String patientGender;

    protected String patientAddress;

    protected String cityName;

    protected String stateName;

    protected String patientPhone;

    protected String countryName;

    protected String userName;

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    protected LocalDateTime expectedDob;

    protected String patientArea;

    protected String visitId;

    protected String previousVisitId;

    protected String casefileNo;

    protected String visitStatus;
//
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    protected LocalDateTime firstVisitRegDate;
//
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected String regDate;

    protected String governmentIdentifier;

    protected String patientId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime visitRegDate;

//    protected LocalDateTime visitRegDate;

    protected String status;

    protected String opType;

    protected String opTypeName;

    protected String visitType;

    protected String PatientFullName;

    protected int age;

    protected String ageIn;

    protected String doctorName;

    protected String departmentName;

    protected String doctor;

    protected String complaint;

    protected String bedType;

    protected String wardId;

    protected String categoryName;

    protected String centerName;
}
