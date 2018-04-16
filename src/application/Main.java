package application;
	



import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




public class Main extends Application {
	
	Player mplayer;
	
	FileChooser filechooser;
	
	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("Open");
		
		Menu file = new Menu("File");
		
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		
		menu.getMenus().add(file);
		
		filechooser = new FileChooser();
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				mplayer.mplayer.pause();
				
				File file = filechooser.showOpenDialog(primaryStage);
				
				if(file!=null) {
					
					try {
						mplayer = new Player(file.toURI().toURL().toExternalForm());
						
						Scene scene = new Scene(mplayer,720,535, Color.AQUA);
						
						primaryStage.setScene(scene);
						
					} catch (MalformedURLException e1) {
						
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		
		
		
		 mplayer = new Player ("file:///C:/Users/Tunna/Downloads/video.mp4");
		 
		primaryStage.setTitle("Flash Player");
		 
		 mplayer.setTop(menu);
		
		Scene scene = new Scene(mplayer,720,510, Color.BLACK);
		
		primaryStage.setScene(scene);
	
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	
	}
}
