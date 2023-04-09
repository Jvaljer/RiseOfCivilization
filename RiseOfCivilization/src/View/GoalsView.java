package View;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import Types.Goals;
import Model.GoalsModel;
import java.awt.*;
import java.util.Arrays;

public class GoalsView extends JFrame {
	private GameView game;
	private GoalsModel model;

	private JPanel pane;
    private JPanel primary_objectives;
    private JPanel secondary_objectives;

    public GoalsView(GameView GV, GoalsModel GM) {
    	game = GV;
    	model = GM;
    	
    	// Create the main panel
    	pane = new JPanel(new GridLayout(2, 1));

        // Create the primary goals panel
    	primary_objectives = new JPanel();
    	primary_objectives.setLayout(new BoxLayout(primary_objectives, BoxLayout.Y_AXIS));

        // Create some sample primary goals
    	for(Goals key : model.GetGoalsMap().keySet()) {
    		JLabel goal = new JLabel(key.toString());
    		JProgressBar progress = new JProgressBar(0,100);
    		if(key.importance==1) {
    			primary_objectives.add(goal);
    			primary_objectives.add(progress);
    		} else {
    			secondary_objectives.add(goal);
    			secondary_objectives.add(progress);
    		}
    	}
    	
        pane.add(primary_objectives);
        pane.add(secondary_objectives);

        setContentPane(pane);

        setTitle("Game Objectives");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Function to modify the progress bar for a goal
    public void modifyProgressBar(String goalTitle, int steps) {
        Component[] primaryGoalsComponents = primary_objectives.getComponents();
        Component[] secondaryGoalsComponents = secondary_objectives.getComponents();

        for (Component component : primaryGoalsComponents) {
            if (component instanceof JLabel && ((JLabel) component).getText().equals(goalTitle)) {
                JProgressBar progressBar = (JProgressBar) primaryGoalsComponents[Arrays.asList(primaryGoalsComponents).indexOf(component) + 1];
                progressBar.setValue(progressBar.getValue() + steps);
                return;
            }
        }

        for (Component component : secondaryGoalsComponents) {
            if (component instanceof JLabel && ((JLabel) component).getText().equals(goalTitle)) {
                JProgressBar progressBar = (JProgressBar) secondaryGoalsComponents[Arrays.asList(secondaryGoalsComponents).indexOf(component) + 1];
                progressBar.setValue(progressBar.getValue() + steps);
                return;
            }
        }

        // If the goal title is not found, show an error message
        JOptionPane.showMessageDialog(this, "Goal not found: " + goalTitle);
    }
}
