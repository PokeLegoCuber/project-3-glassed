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
	
	public FlashFrame() {
		buildScene();
	}

	public void setNextFrameToShow(PuzzlerFrame puzzlerFrame, Stage stage) {
		startButton.setOnAction(e -> puzzlerFrame.show(stage));
	}
	
	@Override
	public void show(Stage stage) {
	    Button exitBtn = new Button("Exit");
	    exitBtn.setOnAction(e -> System.exit(0));
	    
	    
	    VBox root = new VBox();
	    root.getChildren().add(exitBtn);
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_FLASH_KEY));
		stage.show();
	}

	private void buildScene() {
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		flashLabel = new Label(I18n.getBundle().getString(MSG_GAME_DESCRIPTION_KEY));
		startButton = new Button(I18n.getBundle().getString(MSG_START_GAME_KEY));
		vbox.getChildren().addAll(flashLabel, startButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}

}
