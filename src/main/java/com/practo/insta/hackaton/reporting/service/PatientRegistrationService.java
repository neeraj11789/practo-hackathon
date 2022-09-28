package com.practo.insta.hackaton.reporting.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.practo.insta.hackaton.reporting.domain.PatientVisit;
import com.practo.insta.hackaton.reporting.repository.PatientRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PatientRegistrationService {

    @Autowired
    private PatientRegistrationRepository repository;

    public List<PatientVisit> index(final LocalDateTime fromDateTime, final LocalDateTime toDateTime, final Boolean updateExistingRecords) {
        List<PatientVisit> patientRegistrationsForTimeline = repository.getPatientRegistrationsForTimeline(fromDateTime.toLocalDate(), toDateTime.toLocalDate());

    // Create the low-level client
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
    // Create the transport with a Jackson mapper
    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());
    // And create the API client
    ElasticsearchClient client = new ElasticsearchClient(transport);

    BulkRequest.Builder br = new BulkRequest.Builder();
    patientRegistrationsForTimeline.forEach(patientVisit -> {
        br.operations(op -> op
                .index(idx -> idx
                        .index("patient-registration-001")
                        .id(patientVisit.getMrNo())
                        .document(patientVisit)
                )
        );
    });
    try {
        BulkResponse result = client.bulk(br.build());
        // Log errors, if any
        if (result.errors()) {
            log.error("Bulk had errors");
            for (BulkResponseItem item: result.items()) {
                if (item.error() != null) {
                    log.error("Index Error: {}", item.error().reason());
                }
            }
        }
    } catch (IOException e) {
        log.info("Got the exception:: {}", e.getMessage());
    }
        return patientRegistrationsForTimeline;
    }
}
