package application;



import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
	
	Media media;
	
	MediaPlayer mplayer;
	
	MediaView view;
	
	Pane mpane;
	
	MediaBar mediaBar;
	
	public Player (String file) {
		
		media= new Media(file);
		
		mplayer = new MediaPlayer(media);
		
		view = new MediaView(mplayer);
		
		mpane = new Pane();
		
		mpane.getChildren().add(view);
		
		setCenter(mpane);
		
		mediaBar = new MediaBar(mplayer);
		
		setBottom(mediaBar);
		
		setStyle("-fx-background-color: #bfc2c7");
		
		mplayer.play();
		
		
		
	}
}
