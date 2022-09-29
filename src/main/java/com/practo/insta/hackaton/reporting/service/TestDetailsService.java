package com.practo.insta.hackaton.reporting.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.practo.insta.hackaton.reporting.domain.TestDetails;
import com.practo.insta.hackaton.reporting.repository.TestDetailsRepository;
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
public class TestDetailsService {

    @Autowired
    private TestDetailsRepository repository;

    public List<TestDetails> index(final LocalDateTime fromDateTime, final LocalDateTime toDateTime, final Boolean updateExistingRecords) {
        List<TestDetails> patientTestForTimeline = repository.getPatientTestForTimeline(fromDateTime.toLocalDate(), toDateTime.toLocalDate());

        // Create the low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        BulkRequest.Builder br = new BulkRequest.Builder();
        patientTestForTimeline.forEach(patientTest -> {
            br.operations(op -> op
                    .index(idx -> idx
                            .index("patient-test")
                            .id(patientTest.getVisitId())
                            .document(patientTest)
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
        return patientTestForTimeline;
    }
}
