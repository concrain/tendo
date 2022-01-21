package com.tendo.survey.service;

import com.tendo.survey.model.Doctor;
import com.tendo.survey.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return repository.save(doctor);
    }
    @Override
    public Doctor getDoctorById(String doctorId) {
        return repository.findById(doctorId).get();
    }
    @Override
    public List<Doctor> getAllDoctors(){
        return repository.findAll();
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        Optional<Doctor> doctorDB = repository.findById(doctor.getId());
        repository.save(doctor);
    }

    @Override
    public void deleteDoctorById(String doctorId) {
        try {
            repository.deleteById(doctorId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
