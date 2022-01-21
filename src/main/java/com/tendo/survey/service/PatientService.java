package com.tendo.survey.service;

import com.tendo.survey.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient addPatient(Patient patient);
    Patient getPatientById(String patientId);
    void updatePatient(Patient patient);
    void deletePatientById(String patientId);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientByName(String name);
}

