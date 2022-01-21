package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bundle")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bundle {

    @Id
    @JsonProperty("id")
    private String id;
    private Timestamp timestamp;
    private String resourceType;

    @ManyToOne(cascade=CascadeType.PERSIST)
    Entry entry;

    public Bundle() {}
    public Bundle(String id,
                  Timestamp timestamp,
                  String resourceType,
                  Entry entry) {
        this.id = id;
        this.timestamp = timestamp;
        this.resourceType = resourceType;
        this.entry = entry;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
        sb.append("  Bundle:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    resource type: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    entry: ").append(toIndentedString(entry)).append("\n");
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
