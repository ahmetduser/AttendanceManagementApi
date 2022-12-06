package com.example.attandancemanagement.service;

import com.example.attandancemanagement.model.Event;
import com.example.attandancemanagement.model.Student;
import com.example.attandancemanagement.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository repo;

    @Override
    public void addToEvent(Student student, String eventName) {
        repo.addStudentToEvent(student, eventName);
    }

    @Override
    public void removeFromEvent(Student student, String eventName) {
        repo.removeStudentFromEvent(student, eventName);
    }

    @Override
    public List<Event> getAttendingEvents(String studentId) {
        return repo.getAttendingEvents(studentId);
    }
}
