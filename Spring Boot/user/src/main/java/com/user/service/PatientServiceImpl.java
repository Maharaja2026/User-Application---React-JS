package com.user.service;

import com.user.dao.PatientDaoImpl;
import com.user.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService
{
    @Autowired
    PatientDaoImpl dao;

    @Override
    public Patient savePatient(Patient patient) {
        return dao.savePatient(patient);
    }
}
