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
<<<<<<< Updated upstream
    public EndScene(int score, double AverageReactionTime) {
=======
    private Scene scene_to_go;
    public EndScene(int score, double AverageReactionTime,String n, Scene start_scene, Stage stage) {


>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        root.getChildren().addAll(finalScore,finalAverageReactionTime);
=======

        Label youWere = new Label();

        youWere.setText(n);
        youWere.setTextFill(Color.WHITE);
        youWere.setScaleX(5);
        youWere.setScaleY(5);
        youWere.setLayoutX(265);
        youWere.setLayoutY(200);

        Button replay = new Button("Replay");
        replay.setLayoutX(260);
        replay.setLayoutY(10);
        replay.setTextFill(Color.WHITE);
        replay.setOnAction(event -> {
            Main.restart(stage);
            stage.setScene(start_scene);
        });

        root.getChildren().addAll(finalScore,finalAverageReactionTime,youWere,replay);
>>>>>>> Stashed changes



        scene = new Scene(root,600,600);
    }

    public Scene getScene() {
        return scene;
    }
}
