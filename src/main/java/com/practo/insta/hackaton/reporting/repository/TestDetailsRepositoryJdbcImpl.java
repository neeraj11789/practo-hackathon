package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;
import com.practo.insta.hackaton.reporting.domain.TestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TestDetailsRepositoryJdbcImpl implements TestDetailsRepository{

    private static String GET_PATIENT_TEST_DETAILS_FOR_WINDOW="select pr.mr_no, pr.patient_id, pr.visit_type, pr.center_id, hcm.center_name, pd.patient_gender, tp.test_id, di.test_name, tp.pres_date, di.ddept_id, dd.ddept_name, d.doctor_id, d.doctor_name, pd.patient_city, c.city_name, pd.patient_state, sm.state_name, pd.country, cm.country_name\n" +
            "from tests_prescribed tp\n" +
            "left join patient_registration pr on (tp.pat_id=pr.patient_id)\n" +
            "left join doctor_consultation dc on (tp.pat_id=dc.patient_id)\n" +
            "left join doctors d on (d.doctor_id=dc.doctor_name)\n" +
            "left join patient_details pd on (pr.mr_no=pd.mr_no)\n" +
            "left join city c on (pd.patient_city=c.city_id)\n" +
            "left join state_master sm on (pd.patient_state=sm.state_id)\n" +
            "left join hospital_center_master hcm on (hcm.center_id=pr.center_id)\n" +
            "left join country_master cm on (pd.country=cm.country_id)\n" +
            "left join diagnostics di on (tp.test_id=di.test_id)\n" +
            "left join diagnostics_departments dd on (di.ddept_id=dd.ddept_id)\n" +
            "where tp.pres_date between :fromDate and :toDate;";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TestDetails> getPatientTestForTimeline(LocalDate fromDate, LocalDate toDate) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);
        List<TestDetails> testDetails = namedParameterJdbcTemplate.query(GET_PATIENT_TEST_DETAILS_FOR_WINDOW, params,
                (rs, rowNum) -> new TestDetails(
                        rs.getString("mr_no"),
                        rs.getString("patient_id"),
                        rs.getString("visit_type"),
                        rs.getString("center_id"),
                        rs.getString("center_name"),
                        rs.getString("patient_gender"),
                        rs.getString("test_id"),
                        rs.getString("test_name"),
                        LocalDateTime.of(rs.getDate("pres_date").toLocalDate(), rs.getTime("pres_date").toLocalTime()),
                        rs.getString("ddept_id"),
                        rs.getString("ddept_name"),
                        rs.getString("doctor_id"),
                        rs.getString("doctor_name"),
                        rs.getString("patient_city"),
                        rs.getString("city_name"),
                        rs.getString("patient_state"),
                        rs.getString("state_name"),
                        rs.getString("country"),
                        rs.getString("country_name")
                ));
        return testDetails;
    }
}
