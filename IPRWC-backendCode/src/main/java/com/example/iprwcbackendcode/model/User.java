package com.example.iprwcbackendcode.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstname;
    private String middlename;
    private String lastname;

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middleName) {
        this.middlename = middleName;
    }
}
