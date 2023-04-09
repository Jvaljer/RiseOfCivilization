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
/**
 * This view appears when you press the Goals button.
 * It shows you the progress of the main and secondary goals of the game
 *
 * @author Abel
 */
public class GoalsView extends JFrame {
	private GameView game;
	private GoalsModel model;

	private JPanel pane;
    private JPanel primary_objectives;
    private JPanel secondary_objectives;

/**
 * this is the constructor of GoalsView. It creates the panes and adds the progress bars with
 * the progress given in GoalsModel
 */
    public GoalsView(GameView GV, GoalsModel GM) {
    	game = GV;
    	model = GM;
    	
    	// Create the main panel
    	pane = new JPanel(new GridLayout(2, 1));

        // Create the primary goals panel
    	primary_objectives = new JPanel();
    	primary_objectives.setLayout(new BoxLayout(primary_objectives, BoxLayout.Y_AXIS));
    	JLabel title = new JLabel("Primary Objectives -");
    	primary_objectives.add(title);
    	
    	secondary_objectives = new JPanel();
    	secondary_objectives.setLayout(new BoxLayout(secondary_objectives, BoxLayout.Y_AXIS));
    	JLabel title_ = new JLabel("Secondary Objectives -");
    	secondary_objectives.add(title_);
    	
        // Create some sample primary goals
    	for(Goals key : model.GetGoalsMap().keySet()) {
    		JLabel goal = new JLabel(key.toString());
    		int max;
    		switch(key) {
    		case ExpandedSlots:
    			max = 100;
    			break;
    		case CollectResources:
    			max = 150;
    			break;
    		case KilledEnnemies:
    			max = 25;
    			break;
    		default:
    			max = 50;
    			break;
    		}
    		JProgressBar progress = new JProgressBar(0,max);
    		progress.setValue(model.GetGoalsMap().get(key));
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

/** Function to modify the progress bar for a goal*/
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
