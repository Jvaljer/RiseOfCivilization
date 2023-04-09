package View;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * This class is the view of the actions. The action view is represented in
 * a seperate panel at the bottom of the dashboard.
 * Its default height is half of the height of the dashboard.
 * This view uses a grid bag layout and adds buttons for the player actions.
 *
 * @author Amaury
 */
@SuppressWarnings("serial")
public class ActionView extends JPanel {
	private DashboardView dashboard_view;
	/** contains the list of button in the Jpanel */private ArrayList<JButton> Button_list;

	/**
	 * this is the constructor of the actions view that creates the constraint
	 * and add the buttons and their corresponding icons
	 *
	 * @author Amaury
	 */
	public ActionView(DashboardView dv) {
		dashboard_view = dv;
		
		setPreferredSize(new Dimension(dashboard_view.getWidth(), (int) (dashboard_view.getHeight()/3)));
		setBackground(Color.LIGHT_GRAY);
		
		Button_list = new ArrayList<JButton>();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton button = new JButton("");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets=new Insets(5,10,5,10);
		c.ipady=10-button.getMinimumSize().height;
		c.ipadx=10-button.getMinimumSize().width;

		button.setName("Build");
		button.setToolTipText("build");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Build.png"));
		c.gridx = 0;
		c.gridy = 0;
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("LevelUp");
		button.setToolTipText("LevelUp");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/LevelUp.png"));
		c.gridx = 1;
		c.gridy = 0;
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Move");
		button.setToolTipText("Move");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Move.png"));
		c.gridx = 2;
		c.gridy = 0;
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Collect");
		button.setToolTipText("Collect");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Collect.png"));

		c.gridx = 0;
		c.gridy = 1;
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Train");
		button.setToolTipText("Train");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Train.png"));
		c.gridx = 1;
		c.gridy = 1;
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Expand");
		button.setToolTipText("Expand");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Expand.png"));
		c.gridx = 2;
		c.gridy = 1;
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("Shop");
		button.setToolTipText("Shop");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Shop.png"));
		c.gridx = 0;
		c.gridy = 3;
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("Drop");
		button.setToolTipText("Drop");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Drop.png"));
		c.gridx = 1;
		c.gridy = 3;
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("Objectives");
		button.setToolTipText("Objectives");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Objectives.png"));
		c.gridx = 2;
		c.gridy = 3;
		Button_list.add(button);
		add(button, c);
		for (JButton but :Button_list) {
			but.setEnabled(false);
		}
	}

	/** returns the list of button in the Jpanel */
	public ArrayList<JButton> GetButtonList(){
		return Button_list;
	}

	/** returns the button with the corresponding name in the Jpanel */
	public JButton GetButtonFromName(String name) {
		for(JButton but : Button_list) {
			if(but.getName()==name) {
				return but;
			}
		}
		return null;
	}
}