package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlloader=new FXMLLoader(Main.class.getResource("AddDoctor.fxml"));
        Scene scene = new Scene(fxmlloader.load(),1000,700);
        stage.setTitle("Hospital");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


