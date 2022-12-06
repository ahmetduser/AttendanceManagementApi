package com.example.attandancemanagement.service;

import com.example.attandancemanagement.model.Event;
import com.example.attandancemanagement.model.Student;

import java.util.List;

public interface AttendanceService {

    void addToEvent(Student student, String eventName);

    void removeFromEvent(Student student, String eventName);

    List<Event> getAttendingEvents(String studentId);
}
