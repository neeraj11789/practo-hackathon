package com.practo.insta.hackaton.reporting.exception;

import lombok.Data;


@Data
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(int code, String message) {
        super( message );
        this.code = code;
    }
}
