package com.example.flyingstars;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Star star =new Star();


        star.StarSize(3);
        root.getChildren().add(star);
        Scene scene = new Scene(root,600,600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }
}
