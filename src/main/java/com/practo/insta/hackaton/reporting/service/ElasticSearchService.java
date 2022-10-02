package com.practo.insta.hackaton.reporting.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.practo.insta.hackaton.reporting.domain.BaseDomain;
import com.practo.insta.hackaton.reporting.exception.ElasticIndexException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.practo.insta.hackaton.reporting.util.ExceptionHelper.ELASTIC_INDEX_ERROR;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public void bulkIndex(List<? extends BaseDomain> documents, final String indexName){

        /**
         * @todo: TO AVOID DUPLICATES: From the list of the documents -
         * Create the 2 buckets - one for the documents to be updated
         * &
         * another bucket for the documents to be inserted
         * Rename function as bulkUpsert
         */
        BulkRequest.Builder br = new BulkRequest.Builder();
        documents.forEach(d -> br.operations(op -> op
                .index(idx -> idx
                        .index(indexName)
                        .id(d.getExternalId())
                        .document(d)
                )
        ));
        try {
            BulkResponse result = elasticsearchClient.bulk(br.build());
            // Log errors, if any
            if (result.errors()) {
                log.error("################# Bulk Index had errors ###################");
                for (BulkResponseItem item: result.items()) {
                    if (item.error() != null) {
                        log.error("Item: {} Index Error: {}", item, item.error().reason());
                    }
                }
            }
        } catch (IOException e) {
            log.info("###### Got the exception:: {} while doing bulk indexing", e.getMessage());
            throw new ElasticIndexException(ELASTIC_INDEX_ERROR.getCode(), ELASTIC_INDEX_ERROR.getMessage());
        }
    }
}
