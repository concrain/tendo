package com.tendo.survey.service;

import com.tendo.survey.model.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);
    Doctor getDoctorById(String doctorId);
    void updateDoctor(Doctor doctor);
    void deleteDoctorById(String doctorId);
    List<Doctor> getAllDoctors();
}
