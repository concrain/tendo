package com.tendo.survey.service;

import com.tendo.survey.model.Appointment;
import com.tendo.survey.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return repository.save(appointment);
    }
    @Override
    public Appointment getAppointmentById(String appointmentId) {
        return repository.findById(appointmentId).get();
    }
    @Override
    public List<Appointment> getAllAppointments(){
        return repository.findAll();
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Optional<Appointment> bundleDB = repository.findById(appointment.getId());
        repository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(String appointmentId) {
        try {
            repository.deleteById(appointmentId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
