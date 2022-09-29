package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;
import com.practo.insta.hackaton.reporting.domain.TestDetails;
import com.practo.insta.hackaton.reporting.service.ConsultationDetailsService;
import com.practo.insta.hackaton.reporting.service.DaignosisDetailsService;
import com.practo.insta.hackaton.reporting.request.PatientVisitRequestBody;
import com.practo.insta.hackaton.reporting.service.TestDetailsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-test")
@Slf4j
@Api(value = "Patient Test Report Resource", produces = "application/json")
public class TestDetailsController {

    @Autowired
    TestDetailsService testDetailsService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<TestDetails> indexPatients(@RequestBody PatientVisitRequestBody body){
        System.out.println(body);
        return testDetailsService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
