package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.DiagnosisDetails;
import com.practo.insta.hackaton.reporting.domain.appointmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AppointmentDetailsRepositoryJdbcImpl implements AppointmentDetailsRepository {

    private static String GET_PATIENT_APPOINTMENT_DETAILS_FOR_WINDOW = "select pr.mr_no, pr.patient_id, pr.visit_type, pr.center_id, hcm.center_name, pd.patient_gender, sa.appointment_time, sa.appointment_status,  d.doctor_id, d.doctor_name, dp.dept_id, dp.dept_name, pd.patient_city, c.city_name, pd.patient_state, sm.state_name, pd.country, cm.country_name\n" +
            "from scheduler_appointments sa\n" +
            "left join patient_registration pr on (sa.visit_id=pr.patient_id)\n" +
            "left join doctors d on (d.doctor_id=sa.prim_res_id)\n" +
            "left join department dp on (dp.dept_id=d.dept_id)\n" +
            "left join patient_details pd on (pr.mr_no=pd.mr_no)\n" +
            "left join city c on (pd.patient_city=c.city_id)\n" +
            "left join state_master sm on (pd.patient_state=sm.state_id)\n" +
            "left join hospital_center_master hcm on (hcm.center_id=pr.center_id)\n" +
            "left join country_master cm on (pd.country=cm.country_id)\n" +
            "where sa.appointment_time between :fromDate and :toDate;";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<appointmentDetails> getPatientAppointmentForTimeline(LocalDate fromDate, LocalDate toDate) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);
        List<appointmentDetails> appointmentDetails = namedParameterJdbcTemplate.query(GET_PATIENT_APPOINTMENT_DETAILS_FOR_WINDOW, params,
                (rs, rowNum) -> new appointmentDetails(
                        rs.getString("mr_no"),
                        rs.getString("patient_id"),
                        rs.getString("visit_type"),
                        rs.getString("center_id"),
                        rs.getString("center_name"),
                        rs.getString("patient_gender"),
                        LocalDateTime.of(rs.getDate("appointment_time").toLocalDate(), rs.getTime("appointment_time").toLocalTime()),
                        rs.getString("appointment_status"),
                        rs.getString("doctor_id"),
                        rs.getString("doctor_name"),
                        rs.getString("dept_id"),
                        rs.getString("dept_name"),
                        rs.getString("patient_city"),
                        rs.getString("city_name"),
                        rs.getString("patient_state"),
                        rs.getString("state_name"),
                        rs.getString("country"),
                        rs.getString("country_name")
                ));
        return appointmentDetails;
    }
}
