package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchDoctorControllor {

    public Doctor getDoctor(int id) {
        String query = "SELECT * FROM Doctor WHERE id=?";
        Doctor temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Doctor(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"), results.getString("specialization"),
                        results.getTime("timeFrom").toLocalTime(), results.getTime("timeTo").toLocalTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }










}
