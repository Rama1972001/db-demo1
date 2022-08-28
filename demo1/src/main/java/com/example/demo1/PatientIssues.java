package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientIssues {

    @FXML
    private Button add;
    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button search;

    @FXML
    void AddPatientButton(ActionEvent event) throws IOException {
        Stage stage =(Stage) add.getScene().getWindow();
        stage.close();
        Stage  primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Addpatient.fxml") );
        primarystage.setTitle("Add Patient page");
        primarystage.setScene(new Scene(root,600,600));
        primarystage.show();

    }

    @FXML
    void DeletePatientButton(ActionEvent event) {

    }

    @FXML
    void EditPatientButton(ActionEvent event) {

    }

    @FXML
    void SearchPatientButton(ActionEvent event) {

    }

}
