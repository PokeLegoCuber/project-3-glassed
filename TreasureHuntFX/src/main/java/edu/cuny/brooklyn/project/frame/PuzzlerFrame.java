package edu.cuny.brooklyn.project.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import edu.cuny.brooklyn.project.puzzler.Puzzler;
import edu.cuny.brooklyn.project.puzzler.PuzzlerMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuzzlerFrame extends Frame {
	private final static Logger LOGGER = LoggerFactory.getLogger(PuzzlerFrame.class);
	
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_ANSWER_PUZZLER_KEY = "answerPuzzler";
	private final static String MSG_APP_TITLE_PUZZLER_KEY = "appTitlePuzzler";
	
	private PuzzlerMaker puzzlerMaker;
	private Puzzler puzzler;
	private int  answeringAttempts;
	private VBox root;
	private Label puzzlerLabel;
	private Label difficultyLabel;
	private TextField puzzlerAnswer;
	private Button answerButton;
	private TreasureFrame nextFrame;
	private Stage nextStage;
	private Scene scene;
	
	public PuzzlerFrame() {
		puzzlerMaker = new PuzzlerMaker();
		
		buildScene();
		
		answerButton.setOnAction(e -> answerPuzzler());
	}
	
	private void answerPuzzler() {
		String answer = puzzlerAnswer.getText();
		if (answer.isEmpty()) {
			LOGGER.debug("User's answer to the puzzler is empty!");
			return;
		}
		
		answeringAttempts ++;
		
		if (!puzzler.isCorrect(answer)) {
			LOGGER.debug("User's answer to the puzzler is wrong! This is attempt #" + answeringAttempts);
			return;
		} else {
			LOGGER.debug("User's answer to the puzzler is correct, move on to locate the treasure." );
			puzzlerAnswer.clear();
			TreasureClue clue = TreasureClue.getClue(nextFrame.getTreasureField().getTreasureXLeft(),
					nextFrame.getTreasureField().getTreasureYTop(),
					nextFrame.getTreasureField().getTreasureBoundingBoxWidth(),
					nextFrame.getTreasureField().getTreasureBoundingBoxLength(),
					answeringAttempts);	
			nextFrame.setClue(clue);
			nextFrame.startLocatingTreasure(clue.getClueTxt());
			nextFrame.setAttempts(answeringAttempts);
			nextFrame.show(nextStage);
		}
	}
	
	public void setNextFrameToShow(TreasureFrame treasureFrame, Stage stage) {
		nextFrame = treasureFrame;
		nextStage = stage;
	}
	
	@Override
	public void show(Stage stage) {
		updateDifficultyLabel();
		
		puzzler = puzzlerMaker.make();
		puzzlerLabel.setText(puzzler.getMessage());
		answeringAttempts = 0;

		scene.setRoot(root);
	    
	    stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_PUZZLER_KEY));
		stage.show();
	}

	public void initScene(Stage stage) {
		if(!GameSettings.getFullscreen()) {
			// hack
			nextFrame.asRootOf(scene);
			stage.setScene(scene);
			stage.sizeToScene();
			stage.getScene().setRoot(root);
		} else {
			stage.setScene(scene);
		}
		stage.setFullScreen(GameSettings.getFullscreen());
		show(stage);
	}

	private void buildScene() {
	    Button exitBtn = new Button("Exit");
	    exitBtn.setOnAction(e -> System.exit(0));
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		puzzlerLabel = new Label(I18n.getBundle().getString(MSG_GAME_DESCRIPTION_KEY));
		difficultyLabel = new Label("difficulty: " + GameSettings.getDifficulty());
		puzzlerAnswer = new TextField();
		answerButton = new Button(I18n.getBundle().getString(MSG_ANSWER_PUZZLER_KEY));
		vbox.getChildren().addAll(puzzlerLabel, difficultyLabel, puzzlerAnswer, answerButton, exitBtn);
		root = vbox; //, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
		scene = new Scene(root);
		scene.getStylesheets().add("styles.css");
	}
	
	private void updateDifficultyLabel() {
		difficultyLabel.setText("difficulty: " + GameSettings.getDifficulty());
	}
}
