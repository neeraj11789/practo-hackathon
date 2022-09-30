package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.appointmentDetails;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDetailsRepository {

    public List<appointmentDetails> getPatientAppointmentForTimeline(final LocalDate fromDate, final LocalDate toDate);
}
