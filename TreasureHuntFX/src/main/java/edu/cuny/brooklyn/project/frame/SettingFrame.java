package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingFrame extends Frame {
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_START_GAME_KEY = "startGame";
	private final static String MSG_APP_TITLE_FLASH_KEY = "appTitleFlash";
	
	private Scene scene;
	private Label flashLabel;
	private Button backButton;
	
	public SettingFrame() {
		buildScene();
	}

	public void setNextFrameToShow(FlashFrame flashFrame, Stage stage) {
		backButton.setOnAction(e -> flashFrame.show(stage));
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
		flashLabel = new Label("setting controls here");
//		startButton = new Button(I18n.getBundle().getString(MSG_START_GAME_KEY));
		vbox.getChildren().addAll(flashLabel, backButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}

}
