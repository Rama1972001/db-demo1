package com.example.demo1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddDoctorController{



    @FXML
    private TextField tf_Email;

    @FXML
    private TextField tf_address;


    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_phoneNum;

    @FXML
    private TextField tf_pass;

    @FXML
    private TextField tf_special;


    ResultSet rs;



    @FXML
    public void Save_buttonClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        rs = DB_Connection.select("SELECT D_ID FROM doctor where D_ID = '"+tf_id.getText()+"'");

        String id = tf_id.getText();
        String name = tf_name.getText();
        String email = tf_Email.getText();
        String address = tf_address.getText();
        String specialization = tf_special.getText();
        String phone_number = tf_phoneNum.getText();
        String password=tf_pass.getText();

        if (name.isEmpty() || id.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty() || specialization.isEmpty() || address.isEmpty() ||phone_number.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else if(rs.next()){

            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("This doctor already exists!");
            alert2.showAndWait();
            clear_ButtonClicked();

        } else {
            getQuery();
            clear_ButtonClicked();
        }

    }

    @FXML
    private void clear_ButtonClicked() {
        tf_address.setText(null);
        tf_pass.setText(null);
        tf_special.setText(null);
        tf_name.setText(null);
        tf_phoneNum.setText(null);
        tf_Email.setText(null);
        tf_id.setText(null);
    }

    private void getQuery() throws SQLException, ClassNotFoundException {


        String query2 = "INSERT INTO doctor( D_ID, address, D_name, D_password, email," +
                " phone, specialization) VALUES ('" + tf_id.getText() + "','"+tf_name.getText() + "','"+
                 tf_pass.getText() + "','" + tf_Email.getText() + "','" + tf_phoneNum.getText() +"','" + tf_special.getText() +"')";
        System.out.println(query2);
        DB_Connection.execute(query2);

    }


}

