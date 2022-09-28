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
public class DiagnosisDetailsRepositoryJdbcImpl implements DiagnosisDetailsRepository {

    private static String GET_PATIENT_DIAGNOSIS_DETAILS_FOR_WINDOW = "select pr.mr_no, md.visit_id, md.description, md.icd_code, md.code_type, md.doctor_id, d.doctor_name, md.diagnosis_datetime, pd.patient_gender, pd.patient_name, c.city_name, sm.state_name, d.dept_id, d.specialization, dp.dept_name, hcm.center_name, pr.center_id, pr.visit_type\n" +
            "from mrd_diagnosis md\n" +
            "left join doctors d on (md.doctor_id=d.doctor_id)\n" +
            "left join patient_registration pr on (md.visit_id=pr.patient_id)\n" +
            "left join patient_details pd on (pr.mr_no=pd.mr_no)\n" +
            "left join city c on (pd.patient_city=c.city_id)\n" +
            "left join state_master sm on (pd.patient_state=sm.state_id)\n" +
            "left join department dp on (d.dept_id=dp.dept_id)\n" +
            "left join hospital_center_master hcm on (hcm.center_id=pr.center_id)\n" +
            "where md.diagnosis_datetime between :fromDate and :toDate;";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<DiagnosisDetails> getPatientDiagnosisForTimeline(LocalDate fromDate, LocalDate toDate) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);

        List<DiagnosisDetails> diagnosisDetails = namedParameterJdbcTemplate.query(GET_PATIENT_DIAGNOSIS_DETAILS_FOR_WINDOW, params,
                (rs, rowNum) -> new DiagnosisDetails(
                        rs.getString("mr_no"),
                        rs.getString("visit_id"),
                        rs.getString("description"),
                        rs.getString("icd_code"),
                        rs.getString("code_type"),
                        rs.getString("doctor_id"),
                        rs.getString("doctor_name"),
                        LocalDateTime.of(rs.getDate("diagnosis_datetime").toLocalDate(), rs.getTime("diagnosis_datetime").toLocalTime()),
                        rs.getString("patient_gender"),
                        rs.getString("patient_name"),
                        rs.getString("city_name"),
                        rs.getString("state_name"),
                        rs.getString("dept_id"),
                        rs.getString("dept_name"),
                        rs.getString("specialization"),
                        rs.getString("center_name"),
                        rs.getInt("center_id"),
                        rs.getString("visit_type")
                ));

        return diagnosisDetails;

    }
}
