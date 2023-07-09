package com.project.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			// Group root = new Group();
			root.setStyle(
					"-fx-background-image:url('https://media.discordapp.net/attachments/755083836169257062/1071084215606722560/image.png?width=1190&height=670');-fx-background-size : 100% 100%");
			Scene scene = new Scene(root, 1280, 720);
			
			scene.getStylesheets().add(getClass().getResource("./applicationStyle.css").toExternalForm());
//			primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
