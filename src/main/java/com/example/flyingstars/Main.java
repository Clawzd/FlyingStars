package com.example.flyingstars;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;


public class Main extends Application {

    static ArrayList<Star>  stars = new ArrayList<Star>();
    static Group root = new Group();


    public static void onHit(Star star, BallCircle circle, Line hitLine) {

        stars.remove(star);
        root.getChildren().remove(star);
        circle.changeStar(stars.getLast());
       boolean successful_hit = hitLine.getStroke().equals(circle.circle.getFill());
       if(successful_hit) {
           System.out.println("Yah");
       }
       else {
           System.out.println("Nooooo");
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

        int size = 2;
        for (double i =1;i<=2.5; i=i+0.5) {
            Star star =new Star();
            star.StarSize(i);
            root.getChildren().add(star);
            stars.add(star);
        }


        BallCircle circle = new BallCircle(stars.getLast(), new int[]{500,400});

        root.getChildren().add(circle);

        Scene scene = new Scene(root,600,600);


        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }
}
