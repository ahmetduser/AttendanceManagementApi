package com.example.attandancemanagement.controller;


import com.example.attandancemanagement.model.Event;
import com.example.attandancemanagement.model.Student;
import com.example.attandancemanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lucampus/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService service;

    // just studentId is enough in the json body
    @RequestMapping(value = "addAttendance/{eventName}", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addAttendance(@RequestBody Student student,
                              @PathVariable("eventName") String eventName) {
        service.addToEvent(student, eventName);
    }

    @RequestMapping(value = "removeAttendance/{eventName}", method = RequestMethod.DELETE, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void removeAttendance(@RequestBody Student student,
                                 @PathVariable("eventName") String eventName) {
        service.removeFromEvent(student, eventName);
    }


    @GetMapping("getAttendingEvents/{studentId}")
    public List<Event> findEvent(@PathVariable("studentId") String id) {
        return service.getAttendingEvents(id);
    }
}
