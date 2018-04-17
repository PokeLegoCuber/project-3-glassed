package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashFrame extends Frame {
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_START_GAME_KEY = "startGame";
	private final static String MSG_APP_TITLE_FLASH_KEY = "appTitleFlash";
	
	private Scene scene;
	private Label flashLabel;
	private Button startButton;
	private Button settingButton;
	
	public FlashFrame() {
		buildScene();
	}

	public void setNextFrameToShow(PuzzlerFrame puzzlerFrame, Stage stage) {
		startButton.setOnAction(e -> puzzlerFrame.initScene(stage));
	}
	
	public void setConfigFrame(SettingFrame settingFrame, Stage stage) {
		settingButton.setOnAction(e -> settingFrame.show(stage));
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_FLASH_KEY));
		stage.show();
	}

	private void buildScene() {
	    Button exitBtn = new Button("Exit");
	    exitBtn.setOnAction(e -> System.exit(0));
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		flashLabel = new Label(I18n.getBundle().getString(MSG_GAME_DESCRIPTION_KEY));
		settingButton = new Button("Setting");
		startButton = new Button(I18n.getBundle().getString(MSG_START_GAME_KEY));
		vbox.getChildren().addAll(flashLabel, startButton, settingButton, exitBtn);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
		scene.getStylesheets().add("styles.css");
	}

}
