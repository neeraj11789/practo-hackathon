package com.practo.insta.hackaton.reporting.service;

import com.practo.insta.hackaton.reporting.domain.PatientVisit;
import com.practo.insta.hackaton.reporting.repository.PatientVisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PatientVisitService {

    @Autowired
    private PatientVisitRepository repository;

    @Value("${elasticsearch.index.name.patient_visit}")
    private String patientVisitElasticIndexName;

    @Autowired
    private ElasticSearchService elasticsearchService;

    public List<PatientVisit> index(final LocalDateTime fromDateTime, final LocalDateTime toDateTime, final Boolean updateExistingRecords) {
        List<PatientVisit> patientRegistrationsForTimeline = repository.getPatientRegistrationsForTimeline(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
        elasticsearchService.bulkIndex(patientRegistrationsForTimeline, patientVisitElasticIndexName);
        return patientRegistrationsForTimeline;
    }
}
