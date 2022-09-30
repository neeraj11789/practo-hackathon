package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.domain.appointmentDetails;
import com.practo.insta.hackaton.reporting.request.PatientVisitRequestBody;
import com.practo.insta.hackaton.reporting.service.AppointmentDetailsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/reports/patient-appointment")
@Slf4j
@Api(value = "Patient Appointment Report Resource", produces = "application/json")
public class AppointmentDetailsController {

    @Autowired
    AppointmentDetailsService appointmentDetailsService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    private List<appointmentDetails> indexPatients(@RequestBody PatientVisitRequestBody body) {
        System.out.println(body);
        return appointmentDetailsService.index(body.getFromDatetime(), body.getToDatetime(), true);
    }
}
