package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.PatientVisit;
import com.practo.insta.hackaton.reporting.request.PatientVisitRequestBody;
import com.practo.insta.hackaton.reporting.service.PatientVisitService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-visit")
@Slf4j
@Api(value = "Patient Visit Report Resource", produces = "application/json")
public class PatientVisitResource {

    @Autowired
    PatientVisitService patientVisitService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<PatientVisit> indexPatients(@RequestBody PatientVisitRequestBody body){
        System.out.println(body);
        return patientVisitService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
