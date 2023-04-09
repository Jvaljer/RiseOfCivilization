package Controler;

import java.awt.event.*;
import View.GoalsView;

public class ShowGoals implements ActionListener {
	private GameCtrl game;
	
	public ShowGoals(GameCtrl GC) {
		game = GC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new GoalsView(game.GetGameView(),game.GetGameModel().GetGoals());
	}
}
