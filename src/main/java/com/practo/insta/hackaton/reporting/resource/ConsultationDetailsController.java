package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.ConsultationDetails;
import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;
import com.practo.insta.hackaton.reporting.service.ConsultationDetailsService;
import com.practo.insta.hackaton.reporting.service.DaignosisDetailsService;
import com.practo.insta.hackaton.reporting.request.PatientVisitRequestBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-consultation")
@Slf4j
@Api(value = "Patient Consultation Report Resource", produces = "application/json")
public class ConsultationDetailsController {

    @Autowired
    ConsultationDetailsService consultationDetailsService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<ConsultationDetails> indexPatients(@RequestBody PatientVisitRequestBody body) {
        System.out.println(body);
        return consultationDetailsService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
