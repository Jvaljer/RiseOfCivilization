package Controler;

import java.awt.event.*;

public class ShowGoals implements ActionListener {
	private GameCtrl game;
	private GoalsCtrl goals;
	
	public ShowGoals(GameCtrl GC, GoalsCtrl GsC) {
		game = GC;
		goals = GsC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//when the player clicks on this button we wanna open a new window showing how he advanced the different objectivs
	}
}
