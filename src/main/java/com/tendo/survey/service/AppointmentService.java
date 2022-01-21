package com.tendo.survey.service;

import com.tendo.survey.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment addAppointment(Appointment appointment);
    Appointment getAppointmentById(String appointmentId);
    void updateAppointment(Appointment appointment);
    void deleteAppointmentById(String appointmentId);
    List<Appointment> getAllAppointments();
}
