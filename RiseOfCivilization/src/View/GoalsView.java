package View;

import javax.swing.*;
import Model.GoalsModel;

public class GoalsView extends JFrame {
	private GameView game;
	private GoalsModel goals;
	
	public GoalsView(GameView GV) {
		super("Game Objectives");
		game = GV;
		goals = game.GetGameModel().GetGoals();
	}
}
