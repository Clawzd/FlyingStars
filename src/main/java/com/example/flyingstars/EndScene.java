package com.example.flyingstars;
import javafx.scene.control.Button;
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
    private Scene scene_to_go;
    public EndScene(int score, double AverageReactionTime,String n) {


        Pane root =new Pane();

        Image backgroundImage = new Image("Back.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(600);
        root.getChildren().add(backgroundView);

        Label finalScore = new Label();

        finalScore.setText("Your Score: "+score);
        finalScore.setTextFill(Color.WHITE);
        finalScore.setScaleX(3);
        finalScore.setScaleY(3);
        finalScore.setLayoutX(260);
        finalScore.setLayoutY(300);

        Label finalAverageReactionTime = new Label();

        finalAverageReactionTime.setText("Your Average Reaction Time: "+AverageReactionTime+"s");
        finalAverageReactionTime.setTextFill(Color.WHITE);
        finalAverageReactionTime.setScaleX(2.5);
        finalAverageReactionTime.setScaleY(2.5);
        finalAverageReactionTime.setLayoutX(220);
        finalAverageReactionTime.setLayoutY(350);


        Label youWere = new Label();

        youWere.setText(n);
        youWere.setTextFill(Color.WHITE);
        youWere.setScaleX(5);
        youWere.setScaleY(5);
        youWere.setLayoutX(265);
        youWere.setLayoutY(200);



        root.getChildren().addAll(finalScore,finalAverageReactionTime,youWere);



        scene = new Scene(root,600,600);
    }

    public Scene getScene() {
        return scene;
    }
}
