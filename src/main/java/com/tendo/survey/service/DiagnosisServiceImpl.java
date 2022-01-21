package com.tendo.survey.service;

import com.tendo.survey.model.Diagnosis;
import com.tendo.survey.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository repository;

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        return repository.save(diagnosis);
    }
    @Override
    public Diagnosis getDiagnosisById(String diagnosisId) {
        return repository.findById(diagnosisId).get();
    }
    @Override
    public List<Diagnosis> getAllDiagnosis(){
        return repository.findAll();
    }

    @Override
    public void updateDiagnosis(Diagnosis diagnosis) {
        Optional<Diagnosis> doctorDB = repository.findById(diagnosis.getId());
        repository.save(diagnosis);
    }

    @Override
    public void deleteDiagnosisById(String diagnosisId) {
        try {
            repository.deleteById(diagnosisId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
