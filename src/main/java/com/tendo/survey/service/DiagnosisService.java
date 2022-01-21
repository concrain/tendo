package com.tendo.survey.service;

import com.tendo.survey.model.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis addDiagnosis(Diagnosis diagnosis);
    Diagnosis getDiagnosisById(String diagnosisId);
    void updateDiagnosis(Diagnosis diagnosis);
    void deleteDiagnosisById(String diagnosisId);
    List<Diagnosis> getAllDiagnosis();
}
