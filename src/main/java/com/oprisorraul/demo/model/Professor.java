package com.oprisorraul.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Professor {
    @Id
    @SequenceGenerator(
            name = "professor_id_sequence",
            sequenceName = "professor_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "professor_id_sequence"
    )
    private Integer id;
    private String name;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Professor(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Professor() {}

}