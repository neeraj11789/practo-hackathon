package com.practo.insta.hackaton.reporting.service;

import com.practo.insta.hackaton.reporting.domain.PatientDetails;
import com.practo.insta.hackaton.reporting.repository.PatientRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientRegistrationService {

    @Autowired
    private PatientRegistrationRepository repository;

    public List<PatientDetails> index(final LocalDateTime fromDateTime, final LocalDateTime toDateTime, final Boolean updateExistingRecords) {
        List<PatientDetails> patientRegistrationsForTimeline = repository.getPatientRegistrationsForTimeline(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
        return patientRegistrationsForTimeline;
    }
}
