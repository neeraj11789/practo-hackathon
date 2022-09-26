package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.PatientDetails;

import java.time.LocalDate;
import java.util.List;

public interface PatientRegistrationRepository {
    public List<PatientDetails> getPatientRegistrationsForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
