package com.example.demo1;



import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public abstract class PDF {

    DB_Connection db = new DB_Connection();


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    @FXML // fx:id="patComboBox"
    private ComboBox<String> patComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="patName"
    private TextField DocID; // Value injected by FXMLLoader

    @FXML // fx:id="patAge"
    private TextField DOCNAME; // Value injected by FXMLLoader

    @FXML // fx:id="patSymptoms"
    private TextArea docpass; // Value injected by FXMLLoader

    @FXML // fx:id="patPrescription"
    private TextArea docphone; // Value injected by FXMLLoader

    @FXML // fx:id="medicine"
    private TextField docaddress; // Value injected by FXMLLoader

    @FXML // fx:id="duration"
    private TextField docemail; // Value injected by FXMLLoader
    @FXML // fx:id="duration"
    private TextField patID; // Value injected by FXMLLoader

    @FXML // fx:id="duration"
    private TextField docspecial; // Value injected by FXMLLoader
    @FXML // fx:id="duration"

    private Button savbut1; // Value injected by FXMLLoader


    PatientClass current ;
DoctorClass  current1 ;



    @FXML
    void printDoctorInfo(ActionEvent event) {

        PatientClass.setId(Integer.parseInt(DocID.getText()));
        current1.setName(DOCNAME.getText());
        current1.setPassword(Integer.parseInt(docpass.getText()));
        current1.setPhone(Integer.parseInt(docphone.getText()));
        current1.setAddress(docaddress.getText());
        current1.setEmail(docemail.getText());
        current1.setSpecialization(docspecial.getText()


                + "\n\nDate & Time:   " + formatter.format(date) + "  " + timeFormatter.format(time));

    }
         CreatePDF.writeToPDF(current1);

    ProcessBuilder pd = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "file:///E:/Hospital%20Management%20System/Hospital%20Management%20System/prescription.pdf");
                 try {
        pb.start();
    } catch (IOException e) {
        e.printStackTrace();
    }
                 savbut1.setDisable(false);
}


