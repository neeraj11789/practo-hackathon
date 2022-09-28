package com.practo.insta.hackaton.reporting.repository;

import com.practo.insta.hackaton.reporting.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;

@Repository
public class PatientRegistrationRepositoryJdbcImpl implements PatientRegistrationRepository {

    private static String GET_PATIENT_REGISTRATIONS_FOR_WINDOW = "select * from cen.patient_registration " +
            "where (reg_date between :fromDate  AND :toDate );";

    private static String GET_PATIENT_REGISTRATIONS_FOR_TIMEFRAME = "SELECT pd.mr_no, pd.patient_gender, pd.patient_address,\n" +
            "\tct.city_name, sm.state_name, pd.patient_phone,\n" +
            "\tcm.country_name,\n" +
            "\tpd.user_name,\n" +
            "--\tpd.expected_dob, \n" +
            "\tpd.patient_area, pd.visit_id, pd.previous_visit_id, pd.casefile_no,\n" +
            "\tpd.vip_status,\n" +
            "--\tpd.first_visit_reg_date, \n" +
            "\tpr.reg_date,\n" +
            "\tpd.government_identifier, \n" +
            "\tpr.patient_id, " +
            "\t(pr.reg_time) AS visit_reg_date, " +
            "\tpr.status, pr.op_type,  otn.op_type_name,\n" +
            "\tpr.visit_type,\t\n" +
            "\tpd.patient_name || ' ' || coalesce(pd.middle_name, '') || CASE WHEN coalesce(pd.middle_name, '')!='' THEN ' ' ELSE '' end\n" +
            "\t|| coalesce(pd.last_name, '')\tAS patient_full_name,\n" +
            "\tget_patient_age(dateofbirth, expected_dob) AS age,\n" +
            "\tget_patient_age_in(dateofbirth, expected_dob) AS age_in,\n" +
            "\tdr.doctor_name, dep.dept_name,\n" +
            "\tpr.doctor, pr.complaint, pr.bed_type, pr.ward_id,\n" +
            "\tpcm.category_name,\n" +
            "\thcm.center_name\n" +
            "FROM patient_details pd\n" +
            "\tJOIN patient_registration pr ON (pd.mr_no = pr.mr_no)\n" +
            "\tLEFT JOIN op_type_names otn ON (otn.op_type = pr.op_type)\n" +
            "\tLEFT JOIN department dep ON (pr.dept_name = dep.dept_id)\n" +
            "\tLEFT JOIN doctors dr ON (dr.doctor_id = pr.doctor)\n" +
            "\tLEFT JOIN city ct ON (pd.patient_city = ct.city_id)\n" +
            "\tLEFT JOIN state_master sm ON (pd.patient_state = sm.state_id)\n" +
            "\tLEFT JOIN country_master cm ON (pd.country = cm.country_id)\n" +
            "\tLEFT JOIN patient_category_master pcm ON (pr.patient_category_id = pcm.category_id)\n" +
            "\tLEFT JOIN hospital_center_master hcm ON (pr.center_id = hcm.center_id)\n" +
            "WHERE (pr.reg_date between :fromDate  AND :toDate ) \n" +
            "AND (pd.mr_no in (SELECT mr_no from user_mrno_association\n" +
            " WHERE emp_username = current_setting('application.username') OR current_setting('application.username') = '_system') \n" +
            " or pd.patient_group in \n" +
            " (SELECT ufa.confidentiality_grp_id as patient_group \n" +
            " from user_confidentiality_association ufa\n" +
            " JOIN confidentiality_grp_master cgm\n" +
            " ON (cgm.confidentiality_grp_id = ufa.confidentiality_grp_id)\n" +
            "where emp_username = current_setting('application.username')\n" +
            " AND ufa.status = 'A' and cgm.status = 'A' UNION SELECT 0))\n" +
            "ORDER BY pr.reg_date DESC";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<PatientVisit> getPatientRegistrationsForTimeline(final LocalDate fromDate, final LocalDate toDate){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate)
                .addValue("toDate", toDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<PatientVisit> patientDetails = namedParameterJdbcTemplate.query(GET_PATIENT_REGISTRATIONS_FOR_TIMEFRAME, params,
                (rs, rowNum) -> new PatientVisit(
                        rs.getString("mr_no"),
                        rs.getString("patient_gender"),
                        rs.getString("patient_address"),
                        rs.getString("city_name"),
                        rs.getString("state_name"),
                        rs.getString("patient_phone"),
                        rs.getString("country_name"),
                        rs.getString("user_name"),
//                        LocalDateTime.of(rs.getDate("expected_dob"),
                        rs.getString("patient_area"),
                        rs.getString("visit_id"),
                        rs.getString("previous_visit_id"),
                        rs.getString("casefile_no"),
                        rs.getString("vip_status"),
//                        LocalDateTime.of(rs.getDate("first_visit_reg_date"),
                        rs.getString("reg_date"),
                        rs.getString("government_identifier"),
                        rs.getString("patient_id"),
                        rs.getTimestamp("visit_reg_date").toLocalDateTime(),
                        rs.getString("status"),
                        rs.getString("op_type"),
                        rs.getString("op_type_name"),
                        rs.getString("visit_type"),
                        rs.getString("patient_full_name"),
                        rs.getInt("age"),
                        rs.getString("age_in"),
                        rs.getString("doctor_name"),
                        rs.getString("dept_name"),
                        rs.getString("doctor"),
                        rs.getString("complaint"),
                        rs.getString("bed_type"),
                        rs.getString("ward_id"),
                        rs.getString("category_name"),
                        rs.getString("center_name")
                ));
        return patientDetails;
    }
}
