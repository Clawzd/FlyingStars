package com.example.flyingstars;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileWriter;


public class EndScene {
    private Scene scene;
    public EndScene(int score) {
        Pane root =new Pane();

        Image backgroundImage = new Image("Back.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(600);
        root.getChildren().add(backgroundView);

        Label finalScore = new Label();

        finalScore.setText("Final Score: "+score);
        finalScore.setTextFill(Color.WHITE);
        finalScore.setScaleX(3);
        finalScore.setScaleY(3);
        finalScore.setLayoutX(260);
        finalScore.setLayoutY(300);

        root.getChildren().addAll(finalScore);
        scene = new Scene(root,600,600);
    }

    public Scene getScene() {
        return scene;
    }
}
