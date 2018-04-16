package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox {
	
	Slider timeSlider = new Slider();
	
	Slider volumeSlider = new Slider();
	
	Button playButton = new Button("||");
	
	Label volume = new Label("Volume: ");
	
	MediaPlayer mplayer;
	
	public MediaBar (MediaPlayer play) {
		
		mplayer = play;
		
		setAlignment(Pos.CENTER);
		
		setPadding(new Insets(5,10,5,10));
		
		volumeSlider.setPrefWidth(90);
		
		volumeSlider.setMinWidth(45);
		
		HBox.setHgrow(timeSlider, Priority.ALWAYS);
		
		playButton.setPrefWidth(30);
		
		getChildren().add(playButton);
		
		getChildren().add(timeSlider);
		
		getChildren().add(volume);
		
		getChildren().add(volumeSlider);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
			Status status = mplayer.getStatus();
			
			if(status == Status.PLAYING) {
				
				if(mplayer.getCurrentTime().greaterThanOrEqualTo(mplayer.getTotalDuration())) {
					
					mplayer.seek(mplayer.getStartTime());
					
					mplayer.play();
					
				
			} else {
				
				mplayer.pause();
				
				playButton.setText(">");
				
			}
				
			}
			
			if(status == Status.PAUSED || status== Status.HALTED || status== Status.STOPPED) {
				
				mplayer.play();
				
				playButton.setText("||");
			
			}
			
			
			}
		});
		
		mplayer.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				updatesValue();
			}
		});
		
	
		
		timeSlider.valueProperty().addListener(new InvalidationListener() {
			
			public void invalidated(Observable ov) {
				
				if(timeSlider.isPressed()) {
					
					mplayer.seek(mplayer.getMedia().getDuration().multiply(timeSlider.getValue()/100));
				
				}
			
			}
		
		});
		
		volumeSlider.valueProperty().addListener(new InvalidationListener(){
			
			public void invalidated(Observable ov) {
				
				if(volumeSlider.isPressed()) {
					
					mplayer.setVolume(volumeSlider.getValue()/100);
				}
			}
		});
	}
		
		public void updatesValue() {
			
			Platform.runLater(new Runnable() {
		
				public void run() {
				
					timeSlider.setValue(mplayer.getCurrentTime().toMillis()/mplayer.getTotalDuration().toMillis()*100);
				}
			});
		}
}
