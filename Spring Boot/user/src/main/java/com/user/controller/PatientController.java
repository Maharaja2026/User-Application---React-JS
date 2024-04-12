package com.user.controller;

import com.user.entity.Patient;
import com.user.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/patient")
public class PatientController
{
    @Autowired
    PatientServiceImpl service;


    @PostMapping("savePatient")
    public ResponseEntity savePatient(@RequestBody Patient patient)
    {
        System.out.println(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePatient(patient));
    }
}
