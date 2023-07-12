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

			root.setStyle(
					"-fx-background-image:url('https://cdn.discordapp.com/attachments/1100049349712564265/1127530838729437184/lichsuvn.jpg?width=1246&height=701?width=1190&height=670');-fx-background-size : 100% 100%");
			Scene scene = new Scene(root, 1280, 720);
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
