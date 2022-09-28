package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.PatientVisit;
import com.practo.insta.hackaton.reporting.request.PatientRegistrationRequestBody;
import com.practo.insta.hackaton.reporting.service.PatientRegistrationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-registration")
@Slf4j
@Api(value = "Patient Visit Report Resource", produces = "application/json")
public class PatientsResource {

    @Autowired
    PatientRegistrationService patientRegistrationService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<PatientVisit> indexPatients(@RequestBody PatientRegistrationRequestBody body){
        System.out.println(body);
        return patientRegistrationService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
