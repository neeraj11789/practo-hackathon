package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;
import com.practo.insta.hackaton.reporting.request.PatientRegistrationRequestBody;
import com.practo.insta.hackaton.reporting.service.DaignosisDetailsService;
import io.swagger.annotations.Api;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-diagnosis")
@Slf4j
@Api(value = "Patient Diagnosis Report Resource", produces = "application/json")
public class DiagnosisController {

    @Autowired
    DaignosisDetailsService daignosisDetailsService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<DiagnosisDetails> indexPatients(@RequestBody PatientRegistrationRequestBody body){
        System.out.println(body);
        return daignosisDetailsService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
