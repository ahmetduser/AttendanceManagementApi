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
public class Attendance {
    @Id @GeneratedValue
    private int id;
    private String studentId;
    private String eventName;

    public Attendance(int id, String studentId, String eventName) {
        this.id = id;
        this.studentId = studentId;
        this.eventName = eventName;
    }
}
