package com.example.flyingstars;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Main extends Application {

    static int score=0;
    static int attempts=2;


    static ArrayList<Star>  stars = new ArrayList<Star>();
    static Group root = new Group();
    static Label scorel =new Label();
    static Label attemptsl =new Label();


    static Label reactionTimel = new Label();
    static long hitTime;
    static double totalReactionTime = 0;
    static double AverageReactionTime = 0;


    public static void onHit(Star star, BallCircle circle, Line hitLine) {

        stars.remove(star);
        root.getChildren().remove(star);
        circle.changeStar(stars.getLast());
       boolean successful_hit = hitLine.getStroke().equals(circle.circle.getFill());
       if(successful_hit) {
           ++score;
           scorel.setText("Score: "+score);

           long currentTime = System.currentTimeMillis();
           double reactionTimeInSeconds = (currentTime - hitTime) / 1000.0;
           totalReactionTime += reactionTimeInSeconds;
           hitTime = currentTime;


           AverageReactionTime = totalReactionTime / score;
           AverageReactionTime = (double) Math.round(AverageReactionTime * 100) / 100;

           reactionTimel.setText("Average Reaction Time: "+AverageReactionTime+"s");
       }
       else {
           --attempts;
           attemptsl.setText("Attempts: "+attempts);

           root.getChildren().remove(circle);
           BallCircle new_circle = new BallCircle(stars.getLast(),new int[]{50,50});
           root.getChildren().add(new_circle);

       }


    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {

        Image backgroundImage = new Image("Back.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(600);
        root.getChildren().add(backgroundView);



        Image icon = new Image("StartScene.jpg");
        stage.getIcons().add(icon);


        int size = 2;
        for (double i =1;i<=2.5; i=i+0.5) {
            Star star =new Star();
            star.StarSize(i);
            root.getChildren().add(star);
            stars.add(star);
        }


        BallCircle circle = new BallCircle(stars.getLast(), new int[]{500,400});
        root.getChildren().add(circle);

        scorel.setText("Score: "+ score);
        scorel.setLayoutX(15);
        scorel.setLayoutY(10);
        scorel.setTextFill(Color.WHITE);
        scorel.setScaleX(1.5);
        scorel.setScaleY(1.5);

        attemptsl.setText("Attempts: "+attempts);
        attemptsl.setLayoutX(100);
        attemptsl.setLayoutY(10);
        attemptsl.setTextFill(Color.WHITE);
        attemptsl.setScaleX(1.5);
        attemptsl.setScaleY(1.5);


        reactionTimel.setText("Average Reaction Time: "+ attempts);
        reactionTimel.setLayoutX(230);
        reactionTimel.setLayoutY(10);
        reactionTimel.setTextFill(Color.WHITE);
        reactionTimel.setScaleX(1.5);
        reactionTimel.setScaleY(1.5);


        root.getChildren().addAll(scorel,attemptsl,reactionTimel);

        Scene scene = new Scene(root,600,600);

        StartScene startScene = new StartScene(stage, scene);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), event -> {


            if (attempts == 0) {
                EndScene endScene = new EndScene(score,AverageReactionTime);
                stage.setScene(endScene.getScene());
            }
        }));

        animation.setCycleCount(-1);
        animation.play();


        stage.setResizable(false);
        stage.setScene(startScene.getScene());
        stage.setTitle("Flying Stars");
        stage.show();


    }
}
