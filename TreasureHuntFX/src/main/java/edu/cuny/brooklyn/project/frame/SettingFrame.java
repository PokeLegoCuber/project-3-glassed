package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingFrame extends Frame {
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_START_GAME_KEY = "startGame";
	private final static String MSG_APP_TITLE_FLASH_KEY = "appTitleFlash";
	
	private Scene scene;
	private Label flashLabel;
	private Button backButton;
	private Label difficultyLabel;
	private ComboBox<Integer> difficultyBox;
	private CheckBox fullscreenBox;
	
	public SettingFrame() {
		buildScene();
	}

	public void setNextFrameToShow(FlashFrame flashFrame, Stage stage) {
		backButton.setOnAction(e -> {
			GameSettings.setDifficulty(difficultyBox.getValue());
			GameSettings.setFullscreen(fullscreenBox.isSelected());
			flashFrame.show(stage);
		});
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_FLASH_KEY));
		stage.show();
	}

	private void buildScene() {
	    backButton = new Button("back");
	    
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		
		flashLabel = new Label("Setting");
		
		difficultyLabel = new Label("difficulty:");
		difficultyBox = new ComboBox<Integer>();
		
		ObservableList<Integer> options = FXCollections.observableArrayList();
		for(int i = 1; i <= GameSettings.NUM_TYPES_OF_PUZZLERS; i++) {
			options.add(i);
		}
		    
		difficultyBox.setItems(options);
		difficultyBox.getSelectionModel().select(new Integer(GameSettings.getDifficulty()));

		fullscreenBox = new CheckBox("start in fullscreen:");
		fullscreenBox.setSelected(GameSettings.getFullscreen()); 

		HBox difficultyHbox = new HBox(difficultyLabel, difficultyBox);
		difficultyHbox.setSpacing(GameSettings.V_SPACING);
		
		vbox.getChildren().addAll(flashLabel, difficultyHbox, fullscreenBox, backButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
		scene.getStylesheets().add("styles.css");
	}

}
