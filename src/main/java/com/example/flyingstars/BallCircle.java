package com.example.flyingstars;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flyingstars.MyPosition;
import com.example.flyingstars.Helper;

public class BallCircle extends Group {


    private double offsetX;
    private double offsetY;
    Star star;

    public Circle circle;

    public void changeStar(Star star) {
        this.star = star;
    }
    public Star getStar() {
       return  this.star;
    }


    public BallCircle(Star star1,int[] position) {
        Helper helper = new Helper();

        this.star = star1;
        double[] position2 = helper.get_random_position(star1.getPolygon(),10,600,10,600);
        // Create a Circle
        circle = new Circle(position2[0], position2[1], 10);  // Position (50, 50), radius 30
        circle.setFill(helper.get_circle_color());



        // Set up mouse drag event to move the circle
        circle.setOnMousePressed(event -> {
            // Store the offset from the circle's position to the mouse cursor
            offsetX = event.getSceneX() - circle.getCenterX();
            offsetY = event.getSceneY() - circle.getCenterY();
        });

        circle.setOnMouseDragged(event -> {
            Star star = this.getStar();
            // Move the circle by updating its center based on the mouse position
            double newCenterX = event.getSceneX() - offsetX;
            double newCenterY = event.getSceneY() - offsetY;
            double circleRadius = circle.getRadius();

           Polygon polygon = star.getPolygon();

            if(polygon.contains(newCenterX,newCenterY) ) {
                newCenterX = MyPosition.lX;
                newCenterY = MyPosition.lY;

            }
            else {
                MyPosition.setLastValid(newCenterX,newCenterY);;
            }



            // Update circle's position
            circle.setCenterX(newCenterX);
            circle.setCenterY(newCenterY);

            for (Line line : star.LineList) {
                Shape inter = Shape.intersect(circle,line);
                if( inter.getBoundsInLocal().getWidth() > 0 &&
                        inter.getBoundsInLocal().getHeight() > 0) {
                    System.out.println("hit");
                    Main.onHit(star,this,line);


                }

            }

        });

        getChildren().add(circle);

    }



}