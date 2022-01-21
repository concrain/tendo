package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "report")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Report {

    @OneToOne
    private Entry entry;
    @Id
    @GeneratedValue
    private Long id;
    private int doctorRecommendation;
    private String diagnosisFeedback;
    private String appointmentFeedback;

    public Report() {}
    public Report(int doctorRecommendation,
                  String diagnosisFeedback,
                  String appointmentFeedback) {
        this.doctorRecommendation = doctorRecommendation;
        this.diagnosisFeedback = diagnosisFeedback;
        this.appointmentFeedback = appointmentFeedback;
    }

    public Long getId() {
        return id;
    }

    public Integer getDoctorRecommendation() {
        return doctorRecommendation;
    }

    public void setDoctorRecommendation(int doctorRecommendation) {
        this.doctorRecommendation = doctorRecommendation;
    }

    public String getDiagnosisFeedback() {
        return diagnosisFeedback;
    }

    public void setDiagnosisFeedback(String diagnosedWith) {
        this.diagnosisFeedback = diagnosedWith;
    }

    public String getAppointmentFeedback() {
        return appointmentFeedback;
    }

    public void setAppointmentFeedback(String patientFeedback) {
        this.appointmentFeedback = patientFeedback;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Appointment:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    doctor recommendation: ").append(toIndentedString(doctorRecommendation)).append("\n");
        sb.append("    diagnosis feedback: ").append(toIndentedString(diagnosisFeedback)).append("\n");
        sb.append("    appointment feedback: ").append(toIndentedString(appointmentFeedback)).append("\n");
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
