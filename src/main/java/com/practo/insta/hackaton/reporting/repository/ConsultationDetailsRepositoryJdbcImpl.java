package com.practo.insta.hackaton.reporting.repository;

import com.google.common.collect.Lists;
import com.practo.insta.hackaton.reporting.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultationDetailsRepositoryJdbcImpl implements ConsultationDetailsRepository {

    private static String GET_PATIENT_CONSULTATION_DETAILS_FOR_WINDOW = "select pr.mr_no, pr.patient_id, pr.visit_type, pr.center_id, hcm.center_name, (pr.reg_date+pr.reg_time) as reg_date, extract(minute from (dc.start_datetime - (pr.reg_date + pr.reg_time)))::Integer as wait_time, (extract(epoch from dc.end_datetime - dc.start_datetime)/3600)::Integer as consultation_time, dc.status, d.dept_id, dp.dept_name, d.doctor_id, d.doctor_name, pd.patient_gender, pd.patient_city, c.city_name, pd.patient_state, sm.state_name, d.specialization, pd.country, cm.country_name\n" +
            "from doctor_consultation dc\n" +
            "left join patient_registration pr on (dc.patient_id=pr.patient_id)\n" +
            "left join doctors d on (d.doctor_id=dc.doctor_name)\n" +
            "left join patient_details pd on (pr.mr_no=pd.mr_no)\n" +
            "left join city c on (pd.patient_city=c.city_id)\n" +
            "left join state_master sm on (pd.patient_state=sm.state_id)\n" +
            "left join hospital_center_master hcm on (hcm.center_id=pr.center_id)\n" +
            "left join country_master cm on (pd.country=cm.country_id)\n" +
            "left join department dp on (d.dept_id=dp.dept_id)\n" +
            "where dc.start_datetime between :fromDate and :toDate;";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ConsultationDetails> getPatientConsultationForTimeline(LocalDate fromDate, LocalDate toDate) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);

        List<ConsultationDetails> consultationDetails = namedParameterJdbcTemplate.query(GET_PATIENT_CONSULTATION_DETAILS_FOR_WINDOW, params,
                (rs, rowNum) -> new ConsultationDetails(
                        rs.getString("mr_no"),
                        rs.getString("patient_id"),
                        rs.getString("visit_type"),
                        LocalDateTime.of(rs.getDate("reg_date").toLocalDate(), rs.getTime("reg_date").toLocalTime()),
                        rs.getString("patient_gender"),
                        rs.getInt("center_id"),
                        rs.getString("center_name"),
                        rs.getInt("wait_time"),
                        rs.getInt("consultation_time"),
                        rs.getString("status"),
                        rs.getString("dept_id"),
                        rs.getString("dept_name"),
                        rs.getString("doctor_id"),
                        rs.getString("doctor_name"),
                        rs.getString("specialization"),
                        rs.getString("patient_city"),
                        rs.getString("city_name"),
                        rs.getString("patient_state"),
                        rs.getString("state_name"),
                        rs.getString("country"),
                        rs.getString("country_name")
                ));
        return consultationDetails;
    }
}
