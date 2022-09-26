package com.practo.insta.hackaton.reporting.repository;

import com.google.common.collect.Lists;
import com.practo.insta.hackaton.reporting.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRegistrationRepositoryJdbcImpl implements PatientRegistrationRepository {

    private static String GET_PATIENT_REGISTRATIONS_FOR_WINDOW = "select * from cen.patient_registration " +
            "where (reg_date between :fromDate  AND :toDate );";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<PatientDetails> getPatientRegistrationsForTimeline(final LocalDate fromDate, final LocalDate toDate){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);
        ArrayList<PatientDetails> patientDetailsArrayList = namedParameterJdbcTemplate.queryForObject(GET_PATIENT_REGISTRATIONS_FOR_WINDOW, params,
                (rs, rowNum) -> Lists.newArrayList(new PatientDetails(
                        rs.getString("mr_no"),
                        rs.getString("patient_id"),
                        "OP",
                        "A",
                        new Doctor("DOC001", "Dr Anil", "Nephrologist"),
                        new Department("DEPT001", "Nephro-Urology"),
                        new Center("CENT001", "Center 1"),
                        new Patient("PAT0001", "Neeraj Gupta", 31),
                        LocalDateTime.of(rs.getDate("reg_date").toLocalDate(), rs.getTime("reg_time").toLocalTime())
                )));
        return patientDetailsArrayList;
    }
}
