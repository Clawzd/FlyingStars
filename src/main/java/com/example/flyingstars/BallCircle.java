package com.example.flyingstars;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;


public class BallCircle extends Group {


    private double offsetX;
    private double offsetY;
    Star star;

    public Circle circle;
    public boolean isDragging = false;

    public void changeStar(Star star) {
        this.star = star;
    }
    public Star getStar() {
       return  this.star;
    }
    public boolean getDrag() {

       return  this.isDragging;
    }



    public boolean isTrapped() {
        return( (star.getPolygon().getBoundsInParent().getWidth() ) > 590 && (star.getPolygon().getBoundsInParent().getHeight() > 590 ) )&& star.getPolygon().getBoundsInParent().contains(MyPosition.lX,MyPosition.lY);
    }

    public BallCircle(Star star1,int[] position) {
        Helper helper = new Helper();

        this.star = star1;
        double[] position2 = helper.get_random_position(star1.getPolygon(),10,600,10,600);
        // Create a Circle
        circle = new Circle(position2[0], position2[1], 10);
        circle.setFill(helper.get_circle_color());



        circle.setOnMousePressed(event -> {
            offsetX = event.getSceneX() - circle.getCenterX();
            offsetY = event.getSceneY() - circle.getCenterY();
        });


        circle.setOnMouseReleased(event -> {
            System.out.println("Mouse released");
            isDragging = false;

        });




        circle.setOnMouseDragged(event -> {
            isDragging = true;
            Star star = this.getStar();
            double newCenterX = event.getSceneX() - offsetX;
            double newCenterY = event.getSceneY() - offsetY;
            double circleRadius = circle.getRadius();
           Polygon polygon = star.getPolygon();

            if((polygon.contains(newCenterX,newCenterY)) || check_not_in_scene(circle,newCenterX,newCenterY) ) {
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
                    break;

                }

            }

        });

        getChildren().add(circle);

    }

    public boolean check_not_in_scene(Circle circle,double newCenterX, double newCenterY) {
        double x = newCenterX;
        double y = newCenterY;
        boolean cond = false;
        int sceneHeight = 600;
        int sceneWidth = 600;
        if ( y  - circle.getRadius() < 0) {
            cond  =   true;
        } else if ( y  + circle.getRadius() > sceneHeight) {
            cond = true;
        } else if (x  - circle.getRadius() < 0) {
            cond =   true;
        } else if ( x + circle.getRadius() > sceneWidth) {
            cond =   true;
        }

        return  cond;
    }


    public boolean intersect_on_released() {
        Star star = this.getStar();
        for (Line line : star.LineList) {
            Shape inter = Shape.intersect(circle,line);
            if( inter.getBoundsInLocal().getWidth() > 0 &&
                    inter.getBoundsInLocal().getHeight() > 0) {
                return ! this.isDragging;
            }

        }

        return false;
    }

}