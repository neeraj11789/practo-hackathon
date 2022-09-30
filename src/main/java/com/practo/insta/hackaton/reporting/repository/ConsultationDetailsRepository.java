package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.ConsultationDetails;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationDetailsRepository {

    public List<ConsultationDetails> getPatientConsultationForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
