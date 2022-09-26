package com.practo.insta.hackaton.reporting.domain;

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

    protected LocalDateTime registrationDateTime;
}
