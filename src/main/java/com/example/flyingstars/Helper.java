package com.example.flyingstars;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.Random;

public class Helper {
     ArrayList<Color>  ColorList=new ArrayList<>();
    public Helper() {
    add_colors();
    }


    public  void add_colors() {
        ColorList.add(Color.RED);
        ColorList.add(Color.GREEN);
        ColorList.add(Color.YELLOW);
        ColorList.add(Color.WHITE);
        ColorList.add(Color.BLUE);
        ColorList.add(Color.ORANGE);
        ColorList.add(Color.PURPLE);
        ColorList.add(Color.SKYBLUE);
        ColorList.add(Color.PINK);
        ColorList.add(Color.BROWN);

    }

        public  Color get_circle_color() {
            Random random = new Random();
            int i = random.nextInt(ColorList.size());
            return ColorList.get(i);
        }

    public Color get_circle_color(Color current) {
        ArrayList<Color> ColorListI = ( ArrayList<Color> ) ColorList.clone();
        ColorListI.remove(current);
        Random random = new Random();
        int i = random.nextInt(ColorListI.size());
        return ColorListI.get(i);
    }


    public double[] get_random_position(Polygon polygon, double minX, double maxX, double minY, double maxY) {
        Random random = new Random();
        Polygon copy = new Polygon();
        copy.getPoints().addAll(polygon.getPoints());
        copy.setScaleX(1.2); // Increase width by 50%
        copy.setScaleY(1.2); // Increase height by 50%
        double x, y;

        while (true) {
            x = minX + random.nextDouble() * (maxX - minX);
            y = minY + random.nextDouble() * (maxY - minY);

            if (!copy.contains(x, y)) {
                return new double[]{x, y};
            }
        }
    }
}
