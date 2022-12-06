package com.example.attandancemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Student {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String major;
    private String studentId;

    public Student(int id, String name, String email, String major, String studentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
        this.studentId = studentId;
    }
}
