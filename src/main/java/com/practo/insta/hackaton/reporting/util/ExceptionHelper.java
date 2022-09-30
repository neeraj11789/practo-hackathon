package com.practo.insta.hackaton.reporting.util;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionHelper {

    VISIT_NOT_FOUND(400101 , "Visit Not Found"), // All Visit related XXX1XX
    ELASTIC_INDEX_ERROR(920001 , "Elastic Index Exception"); // All Elastic related 9200XX

    final private int code;

    final private String message;
}
