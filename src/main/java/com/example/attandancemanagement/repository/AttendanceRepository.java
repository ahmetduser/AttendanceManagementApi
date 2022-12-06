package com.example.attandancemanagement.repository;

import com.example.attandancemanagement.model.Event;
import com.example.attandancemanagement.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRepository {

    private Connection con = null;
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;


    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public void addStudentToEvent(Student student, String eventName) {

        // id is auto incremented
        String add = "INSERT INTO attending (studentId, eventName) " +
                "VALUES (?, ?)";
        String updateSlots = "UPDATE event SET attending = attending + 1"
                + " WHERE name = '" + eventName + "'";

        //TODO check before there is free slot
        PreparedStatement stm;
        PreparedStatement update;

        try {
            stm = getDBConnection().prepareStatement(add);
            stm.setString(1, student.getStudentId());
            stm.setString(2, eventName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            update = getDBConnection().prepareStatement(updateSlots);
            stm.executeUpdate();
            update.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeStudentFromEvent(Student student, String eventName) {
        // id is auto incremented
        String remove = "DELETE FROM attending WHERE studentId = ? AND eventName = ?";
        String updateSlots = "UPDATE event SET attending = attending - 1"
                + " WHERE name = '" + eventName + "'";

        //TODO check before there is free slot
        PreparedStatement stm;
        PreparedStatement update;

        try {
            stm = getDBConnection().prepareStatement(remove);
            stm.setString(1, student.getStudentId());
            stm.setString(2, eventName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            update = getDBConnection().prepareStatement(updateSlots);
            stm.executeUpdate();
            update.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAttendingEvents(String studentId) {
        List<Event> eventList = new ArrayList<>();
        Event event;
        String selectAll = "SELECT event.id, event.name, event.subject, event.slots, event.attending, event.roomNumber " +
                "FROM event, attending " +
                "WHERE attending.studentId = '" + studentId + "' AND " +
                "attending.eventName = event.Name";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setSubject(rs.getString("subject"));
                event.setSlots(rs.getInt("slots"));
                event.setAttending(rs.getInt("attending"));
                event.setRoomNumber(rs.getString("roomNumber"));
                eventList.add(event);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eventList;
    }


}
