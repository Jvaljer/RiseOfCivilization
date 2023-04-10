package View;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JPanel;
import Model.GoalsModel;
import Types.Goals;
import javax.swing.JLabel;

public class ScoreView extends JFrame {
	private GoalsModel goals;
	private int score;
	private boolean win;
	
	public ScoreView(GoalsModel GM, int sc, boolean w) {
		super("FINAL SCORE");
		goals = GM;
		score = sc;
		win = w;
		
		setPreferredSize(new Dimension(200,200));
		
		JPanel pane = new JPanel();
		
		for(Goals key : goals.GetGoalsMap().keySet()) {
			JLabel label = new JLabel(key.toString()+" : "+ goals.GetGoalsMap().get(key));
			pane.add(label);
		}
		
		setContentPane(pane);
	}
}
