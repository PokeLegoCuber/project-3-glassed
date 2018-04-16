package edu.cuny.brooklyn.project.frame;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameFrame {
	private FlashFrame flashFrame;
	private PuzzlerFrame puzzlerFrame;
	private TreasureFrame treasureFrame;
	private SettingFrame settingFrame;
	
	public GameFrame() {
		flashFrame = new FlashFrame();
		puzzlerFrame = new PuzzlerFrame();
		treasureFrame = new TreasureFrame();
		settingFrame = new SettingFrame();
	}
	
	public FlashFrame getFlashFrame() {
		return flashFrame;
	}
	
	public PuzzlerFrame getPuzzlerFrame() {

		return puzzlerFrame;
	}

	public TreasureFrame getTreasureFrame() {

		return treasureFrame;
	}
	
	public SettingFrame getSettingFrame() {
		return settingFrame;
	}
}
