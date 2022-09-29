package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.TestDetails;

import java.time.LocalDate;
import java.util.List;

public interface TestDetailsRepository {

    public List<TestDetails> getPatientTestForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
