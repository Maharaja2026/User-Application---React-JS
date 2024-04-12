package com.user.dao;

import com.user.dto.PhoneNumber;
import com.user.entity.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDaoImpl implements PatientDao{

    private JdbcTemplate jdbcTemplate;

    public PatientDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String PATIENT_INSERT_QUERY = "INSERT INTO patient (first_name, last_name, date_of_birth, gender, street_address, city, state, postal_code, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String PHONE_NUMBER_INSERT_QUERY = "INSERT INTO phone_number (medical_record_number, type, number) VALUES (?, ?, ?)";

    @Override
    public Patient savePatient(Patient patient) {

        jdbcTemplate.update(PATIENT_INSERT_QUERY, patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getGender()
                , patient.getAddress().getStreetAddress(), patient.getAddress().getCity(), patient.getAddress().getState(), patient.getAddress().getPostalCode()
                , patient.getEmail());

        int patientId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        patient.setMedicalRecordNumber(Integer.parseInt(String.valueOf(patientId)));

        for (PhoneNumber phoneNumber : patient.getPhoneNumbers()) {
            jdbcTemplate.update(PHONE_NUMBER_INSERT_QUERY, patientId, phoneNumber.getType(), phoneNumber.getNumber());
        }

        return patient;
    }
}
