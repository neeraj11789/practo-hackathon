package com.practo.insta.hackaton.reporting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Patient {

    protected String id;

    protected String name;

    protected Integer age;
}
