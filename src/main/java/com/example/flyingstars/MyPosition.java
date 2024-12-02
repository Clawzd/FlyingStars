package com.example.flyingstars;


public class MyPosition {
    public double X;
    public static double lX;
    public double Y;
    public static double lY;
    public MyPosition(double X,  double Y) {
        this.X = X;
        this.Y = Y;
    }

    public static void setLastValid(double X, double Y) {
        lX = X;
        lY = Y;
    }

}
