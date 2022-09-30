package com.practo.insta.hackaton.reporting.exception;


public class ElasticIndexException extends BaseException{
    public ElasticIndexException(int code, String message) {
        super(code, message);
    }
}
