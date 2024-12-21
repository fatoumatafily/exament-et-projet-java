package com.ism;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CommandeView.fxml"));
            BorderPane root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            primaryStage.setTitle("Gestion de Commandes");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args); 
    }
}
