package com.example.flyingstars;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartScene {
    private Scene scene;

    private ChoiceBox<String> choiceBox =new ChoiceBox<>();

    public StartScene(Stage stage, Scene gameScene) {

        // زر البدء
        Button startButton = new Button("Start");

        startButton.setScaleX(2);
        startButton.setScaleY(2);
        startButton.setTextFill(Color.WHITE);

        startButton.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));

        startButton.setLayoutX(290);
        startButton.setLayoutY(500);
        startButton.setOnAction(e -> stage.setScene(gameScene));


        choiceBox.getItems().addAll("Easy", "Medium", "Hard");
        choiceBox.setValue("Easy");
        choiceBox.setLayoutX(500);
        choiceBox.setLayoutY(500);
        choiceBox.getItems();


        // صورة الخلفية
        Image backgroundImage = new Image("StartScene.jpg"); // ضع ملف الصورة في نفس مسار المشروع أو استخدم المسار الكامل
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600); // عرض الخلفية
        backgroundView.setFitHeight(600); // ارتفاع الخلفية

        // التخطيط
        Pane root = new Pane();
        root.getChildren().addAll(backgroundView, startButton,choiceBox); // أضف الخلفية والزر إلى التخطيط

        // إعداد المشهد
        scene = new Scene(root, 600, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public String getDifficulty() {
        return choiceBox.getValue();
    }
}
