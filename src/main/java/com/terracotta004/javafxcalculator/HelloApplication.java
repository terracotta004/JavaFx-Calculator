package com.terracotta004.javafxcalculator;

/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloApplication extends Application {
    private TextField display;
    private StringBuilder input = new StringBuilder();

    @Override
    public void start(Stage primaryStage) {
        display = new TextField();
        display.setEditable(false);
        display.setPrefHeight(50);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        int row = 1, col = 0;
        for (String text : buttons) {
            Button button = new Button(text);
            button.setPrefSize(50, 50);
            button.setOnAction(e -> handleInput(text));
            grid.add(button, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(display, 0, 0);
        root.add(grid, 0, 1);

        primaryStage.setScene(new Scene(root, 250, 300));
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    private void handleInput(String value) {
        switch (value) {
            case "C":
                input.setLength(0);
                display.clear();
                break;
            case "=":
                try {
                    double result = eval(input.toString());
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
            default:
                input.append(value);
                display.setText(input.toString());
        }
    }

    private double eval(String expression) throws ScriptException {
        return (double) new ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expression, new javax.script.SimpleBindings());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

