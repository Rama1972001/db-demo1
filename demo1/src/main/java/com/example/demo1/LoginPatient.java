package com.example.demo1;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPatient {


    @FXML
    private Button submitButton;

    @FXML
    private TextField tf_ID;

    @FXML
    private TextField tf_PW;

    @FXML
    private Label detector;


    @FXML
    //LogInButtonClick
    public void LogIn() {

        String username = tf_ID.getText();
        String p_password = tf_PW.getText();
        try {
            ResultSet rs1 = DB_Connection.select("SELECT * FROM patient INNER JOIN patients_history ON patient.P_ID = patients_history.P_ID WHERE patient.P_ID =" + Integer.parseInt(username) + " and P_password=" + Integer.parseInt(p_password));

            if (!rs1.next()) { // comparing sql query with the user input

                detector.setText("Login Failed!");
            } else {

                PatientClass patient = new PatientClass(rs1.getInt("P_ID"), rs1.getString("P_name"),
                        rs1.getString("address"), rs1.getString("email"), rs1.getDate("birth").toLocalDate(),
                        rs1.getInt("phone"), rs1.getString("blood_type"), rs1.getString("allergy"));

                detector.setText("Login has done successfully!");

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Patient1_Profile.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Patient1_Controller dc = fxmlLoader.<Patient1_Controller>getController();
                dc.setProfile(patient);
                stage.setScene(scene);

                Main m = new Main();


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void BackButtonClick() throws IOException {
        Main m = new Main();

    }
}
