package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointment {

    @OneToOne
    private Entry entry;
    @Id
    private String id;
    private String status;
    private String type;
    private String resourceType;
    @JsonFormat(pattern = "YYYY-MM-DDTHH: MM:SS")
    private String start;
    @JsonFormat(pattern = "YYYY-MM-DDTHH: MM:SS")
    private String end;
    private String patientReference;
    private String doctorReference;

    public Appointment() {}
    public Appointment(String id,
                String status,
                String type,
                String resourceType,
                String start,
                String end,
                String patientReference,
                String doctorReference) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.resourceType = resourceType;
        this.start = start;
        this.end = end;
        this.patientReference = patientReference;
        this.doctorReference = doctorReference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String startDate) {
        this.start = startDate;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String endDate) {
        this.end = endDate;
    }

    public String getPatientReference() {
        return patientReference;
    }

    public void setPatientReference(String patientReference) {
        this.patientReference = patientReference;
    }

    public String getDoctorReference() {
        return doctorReference;
    }

    public void setDoctorReference(String doctorReference) {
        this.doctorReference = doctorReference;
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
        sb.append("    start date: ").append(toIndentedString(start)).append("\n");
        sb.append("    end date: ").append(toIndentedString(end)).append("\n");
        sb.append("    resource type: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    patient reference: ").append(toIndentedString(patientReference)).append("\n");
        sb.append("    doctor reference: ").append(toIndentedString(doctorReference)).append("\n");
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
