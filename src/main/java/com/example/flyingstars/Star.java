package com.example.flyingstars;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
public class Star extends Group {

   public ArrayList<Line> LineList =new ArrayList<>();


    Star(){


        Line line1 = new Line(0, -100, 47, -31);
        Line line2 = new Line(47, -31, 95, -31);
        Line line3 = new Line(95, -31, 58, 18);
        Line line4 = new Line(58, 18, 71, 85);
        Line line5 = new Line(71, 85, 0, 50);
        Line line6 = new Line(0, 50, -71, 85);
        Line line7 = new Line(-71, 85, -58, 18);
        Line line8 = new Line(-58, 18, -95, -31);
        Line line9 = new Line(-95, -31, -47, -31);
        Line line10 = new Line(-47, -31, 0, -100);

        LineList.add(line1);
        LineList.add(line2);
        LineList.add(line3);
        LineList.add(line4);
        LineList.add(line5);
        LineList.add(line6);
        LineList.add(line7);
        LineList.add(line8);
        LineList.add(line9);
        LineList.add(line10);
        StarColors();

        setLayoutX(300);
        setLayoutY(300);


        getChildren().addAll(line1,line2,line3,line4,line5,line6,line7,line8,line9,line10);
    }
    public void StarSize(double s){
        for(Line line :LineList){
            line.setStartX(line.getStartX() * s);
            line.setStartY(line.getStartY() * s);
            line.setEndX(line.getEndX() * s);
            line.setEndY(line.getEndY() * s);
        }

    }

    public void StarColors(){
        Helper helper = new Helper();
        ArrayList<Color> ColorList=helper.ColorList;



        for (int i = 0; i < ColorList.size(); i++) {
            Random random = new Random();
            int rand = random.nextInt(ColorList.size());
            Color temp = ColorList.get(i);
            ColorList.set(i, ColorList.get(rand));
            ColorList.set(rand, temp);
        }
        for (int i = 0; i < LineList.size(); i++) {
            LineList.get(i).setStroke(ColorList.get(i));
        }

    }
    public Color CollisionColor(Circle circle){
        for(Line line: LineList){
            if (circle.getBoundsInParent().intersects(line.getBoundsInLocal())){
                return (Color) line.getStroke();
            }
        }
        return null;
    }

    public Bounds calculateBounds() {
        // Initialize bounds to reasonable values
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        // Iterate over each line in the Star to calculate bounds
        for (Line line : LineList) {
            double startX = line.getStartX() + getLayoutX();
            double startY = line.getStartY() + getLayoutY();
            double endX = line.getEndX() + getLayoutX();
            double endY = line.getEndY() + getLayoutY();

            minX = Math.min(minX, Math.min(startX, endX));
            minY = Math.min(minY, Math.min(startY, endY));
            maxX = Math.max(maxX, Math.max(startX, endX));
            maxY = Math.max(maxY, Math.max(startY, endY));
        }

        // Return the bounding box
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    public Polygon getPolygon() {
        Star star;
        star = this;
        Polygon polygon = new Polygon();

        for (Line line : star.LineList) {

            double endX = line.getEndX() + star.getLayoutX();
            double endY = line.getEndY() + star.getLayoutY();
            polygon.getPoints().addAll(endX, endY);
        }
        return polygon;
    }

}
