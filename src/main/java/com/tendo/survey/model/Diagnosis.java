package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "diagnosis")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Diagnosis {

    @OneToOne
    private Entry entry;

    public Diagnosis() {}
    public Diagnosis(String id,
                     String lastUpdated,
                     String status,
                     String system,
                     String code,
                     String name,
                     String resourceType,
                     String appointmentReference) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.system = system;
        this.code = code;
        this.name = name;
        this.resourceType = resourceType;
        this.appointmentReference = appointmentReference;
    }

    @Id
    private String id;
    @JsonFormat(pattern = "YYYY-MMDDTHH: MM:SS")
    private String lastUpdated;
    private String status;
    private String system;
    private String code;
    private String name;
    private String resourceType;
    private String appointmentReference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String resourceType) {
        this.name = resourceType;
    }

    public String getName() {
        return name;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String codingSystem) {
        this.system = codingSystem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppointmentReference() {
        return appointmentReference;
    }

    public void setAppointmentReference(String appointmentReference) {
        this.appointmentReference = appointmentReference;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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
        sb.append("  Diagnosis:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    resource type: ").append(toIndentedString(name)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    coding system: ").append(toIndentedString(system)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    appointment reference: ").append(toIndentedString(appointmentReference)).append("\n");
        sb.append("    last updated: ").append(toIndentedString(lastUpdated)).append("\n");
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
