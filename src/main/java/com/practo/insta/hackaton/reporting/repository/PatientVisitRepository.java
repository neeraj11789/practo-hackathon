package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.PatientVisit;

import java.time.LocalDate;
import java.util.List;

public interface PatientVisitRepository {
    List<PatientVisit> getPatientRegistrationsForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
