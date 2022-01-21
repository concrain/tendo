package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "entry")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Entry {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Doctor doctor;
    @OneToOne
    private Patient patient;
    @OneToOne
    private Appointment appointment;
    @OneToOne
    private Diagnosis diagnosis;
    @OneToOne
    @Nullable
    private Report report;

    public Entry() {}
    public Entry(Doctor doctor,
                 Patient patient,
                 Appointment appointment,
                 Diagnosis diagnosis) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointment = appointment;
        this.diagnosis = diagnosis;
    }
    public Entry(Doctor doctor,
                 Patient patient,
                 Appointment appointment,
                 Diagnosis diagnosis,
                 Report report) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Optional<Report> getReport() {
        return Optional.ofNullable(report);
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Appointment:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    patient: ").append(toIndentedString(patient)).append("\n");
        sb.append("    doctor: ").append(toIndentedString(doctor)).append("\n");
        sb.append("    diagnosis: ").append(toIndentedString(diagnosis)).append("\n");
        sb.append("    appointment: ").append(toIndentedString(appointment)).append("\n");
        sb.append("    report: ").append(toIndentedString(report)).append("\n");
        sb.append(" }");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
