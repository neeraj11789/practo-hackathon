package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;

import java.time.LocalDate;
import java.util.List;

public interface DiagnosisDetailsRepository {

    List<DiagnosisDetails> getPatientDiagnosisForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
