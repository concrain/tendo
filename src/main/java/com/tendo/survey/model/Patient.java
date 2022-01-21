package com.tendo.survey.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Patient {

    @OneToOne
    private Entry entry;
    @ManyToMany
    private List<Contact> contact;
    @ManyToMany
    private List<Address> address;
    @Id
    private String id;
    private String name;
    private String familyName;
    private String givenName;
    private boolean active;
    private String gender;
    private Date birthDate;
    private String resourceType;

    public Patient() {}
    public Patient(String id,
                   String familyName,
                   String givenName,
                   String name,
                   boolean active,
                   String gender,
                   Date birthDate,
                   List<Contact> contact,
                   List<Address> address,
                   String resourceType) {
        this.id = id;
        this.familyName = familyName;
        this.givenName = givenName;
        this.name = name;
        this.active = active;
        this.gender = gender;
        this.birthDate = birthDate;
        this.contact = contact;
        this.address = address;
        this.resourceType = resourceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contacts) {
        this.contact = contacts;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String firstName) {
        this.familyName = firstName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String lastName) {
        this.givenName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
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
        sb.append("  Patient:{\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    resource type: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    family name: ").append(toIndentedString(familyName)).append("\n");
        sb.append("    given name: ").append(toIndentedString(givenName)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    birth date: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");;
        sb.append("    contacts: ").append(toIndentedString(contact)).append("\n");
        sb.append("    addresses: ").append(toIndentedString(address)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append(" }");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Entity
    @Table(name = "CONTACT")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    static private class Contact {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String system;
        private String value;
        private String use;

        public Contact() {}
        public Contact(Long id,
                       String system,
                       String value,
                       String use) {
            this.id = id;
            this.system = system;
            this.value = value;
            this.use = use;
        }

        public Long getId() {
            return id;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("  Contact:{\n");
            sb.append("    id: ").append(toIndentedString(id)).append("\n");
            sb.append("    system: ").append(toIndentedString(system)).append("\n");
            sb.append("    value: ").append(toIndentedString(value)).append("\n");
            sb.append("    use: ").append(toIndentedString(use)).append("\n");
            sb.append(" }");
            return sb.toString();
        }

        private String toIndentedString(java.lang.Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n        ");
        }
    }

    @Entity
    @Table(name = "ADDRESS")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private static class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String line;
        private String use;

        public Address() {}
        public Address(Long id,
                       String use,
                       String line) {
            this.id = id;
            this.line = line;
            this.use = use;
        }

        public Long getId() {
            return id;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("  Address:{\n");
            sb.append("    id: ").append(toIndentedString(id)).append("\n");
            sb.append("    first name: ").append(toIndentedString(line)).append("\n");
            sb.append("    last name: ").append(toIndentedString(use)).append("\n");
            sb.append(" }");
            return sb.toString();
        }

        private String toIndentedString(java.lang.Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n        ");
        }
    }

}
