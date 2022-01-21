package com.tendo.survey.service;

import com.tendo.survey.model.Patient;
import com.tendo.survey.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public Patient addPatient(Patient patient) {
        return repository.save(patient);
    }
    @Override
    public Patient getPatientById(String patientId) {
        return repository.findById(patientId).get();
    }
    @Override
    public List<Patient> getAllPatients(){
        return repository.findAll();
    }

    @Override
    public Optional<Patient> getPatientByName(String name) {
        return repository.getPatientByName(name);
    }

    @Override
    public void updatePatient(Patient patient) {
        Optional<Patient> patientDB = repository.findById(patient.getId());
        repository.save(patient);
    }

    @Override
    public void deletePatientById(String reportId) {
        try {
            repository.deleteById(reportId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

}

