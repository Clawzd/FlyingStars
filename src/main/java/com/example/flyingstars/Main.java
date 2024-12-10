package com.example.flyingstars;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {

    static ArrayList<Star>  stars = new ArrayList<Star>();
    static Group root = new Group();

    public double interval = 0.03;
    public double acceler= 0;
    public static Timeline timeline;
    public static BallCircle circle;

    static int score=0;
    static int attempts=3;

    static Label scorel =new Label();
    static Label attemptsl =new Label();
    static Label reactionTimel = new Label();

    static long hitTime;
    static double totalReactionTime = 0;
    static double AverageReactionTime = 0;




    public static void onHit(Star star, BallCircle circle1, Line hitLine) {

        stars.remove(star);
        root.getChildren().remove(star);

        Star new_star = new Star();
        root.getChildren().add(new_star);
        new_star.StarSize(stars.getFirst().size*0.6);
        new_star.setRotate(stars.getFirst().current_angle);
        new_star.setAngle(stars.getFirst().current_angle);
        stars.addFirst(new_star);
        circle.changeStar(stars.getLast());
        boolean successful_hit = hitLine.getStroke().equals(circle1.circle.getFill());
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

           root.getChildren().remove(circle1);
           circle = new BallCircle(stars.getLast());
           root.getChildren().add(circle);


       }


    }
    public static void startTimeline() {
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage)  {

        Image backgroundImage = new Image("Back.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(600);
        root.getChildren().add(backgroundView);



        Image icon = new Image("StartScene.jpg");
        stage.getIcons().add(icon);


        for (double i =0.5;i<=2; i=i+0.5) {
            Star star =new Star();
            star.StarSize(i);
            root.getChildren().add(star);
            stars.add(star);
        }


        circle = new BallCircle(stars.getLast());
        Scene scene = new Scene(root,600,600);
        StartScene startScene = new StartScene(stage, scene);

        root.getChildren().add(circle);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(interval),
                        event -> {

                            if(stars.getLast().hits_boundary()) {
                                Star star = stars.getLast();
                                stars.remove(star);
                                root.getChildren().remove(star);

                                Star new_star = new Star();
                                new_star.StarSize(stars.getFirst().size*0.6);
                                new_star.setRotate(stars.getFirst().current_angle);
                                new_star.setAngle(stars.getFirst().current_angle);
                                root.getChildren().add(new_star);
                                stars.addFirst(new_star);
                                circle.changeStar(stars.getLast());
                            }


                             if(circle.intersect()) {
                                System.out.println("Hit Hi");


                            }


                                for (Star star: stars) {
                                    if(startScene.acceleration()) {
                                    acceler+=0.000003;
                                    }
                                    star.StarSize(1.003+acceler);
//                                  Here is the rotation function remove the comment if you want to test rotation, Jonathan
                                    if(startScene.rotate()) {
                                    star.setRotate(star.incrementCurrentAngle());
                                    }

                                }
                        }
                )
        );
        //

        timeline.setCycleCount(Timeline.INDEFINITE);


        Helper.LabelHelper(scorel,15,"Score: "+ score);
        Helper.LabelHelper(attemptsl,100,"Attempts: "+attempts);
        Helper.LabelHelper(reactionTimel,230, "Average Reaction Time: "+ AverageReactionTime);



        root.getChildren().addAll(scorel,attemptsl,reactionTimel);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(250), event -> {


            if (attempts == 0) {
                EndScene endScene = new EndScene(score,AverageReactionTime," Out Of Attempts");
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
