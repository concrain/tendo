package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Doctor {

    @OneToOne
    private Entry entry;

    public Doctor() {}
    public Doctor(String id,
                  String familyName,
                  String givenName,
                  String resourceType) {
        this.id = id;
        this.familyName = familyName;
        this.givenName = givenName;
        this.resourceType = resourceType;
    }

    @Id
    private String id;
    private String familyName;
    private String givenName;
    private String resourceType;

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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String nameFamily) {
        this.familyName = nameFamily;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String nameGiven) {
        this.givenName = nameGiven;
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
        sb.append("  Doctor:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    resource type: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    family name: ").append(toIndentedString(familyName)).append("\n");
        sb.append("    given name: ").append(toIndentedString(givenName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
