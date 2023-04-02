package View;

import Model.MapModel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class is the view of the infobar. The infobar is represented in
 * a seperate panel at the bottom of the dashboard.
 * Its default height is half of the height of the dashboard.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class ActionView extends JPanel {
	private MapModel map_model;
	private ArrayList<JButton> Button_list;

	public ActionView(MapModel mm) {
		Button_list=new ArrayList<JButton>();

		map_model = mm;
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/2));
		setBackground(Color.LIGHT_GRAY);

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
		button.setIcon(new ImageIcon("./icons/Expand.png"));
		c.gridx = 0;
		c.gridy = 3;
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("Drop");
		button.setToolTipText("Drop");
		button.setBackground(Color.BLACK);
		button.setIcon(new ImageIcon("./icons/Train.png"));
		c.gridx = 1;
		c.gridy = 3;
		Button_list.add(button);
		add(button, c);
		
		for (JButton but :Button_list) {
			but.setEnabled(false);
		}
	}

	public ArrayList<JButton> GetButtonList(){
		return Button_list;
	}
	public JButton GetButtonFromName(String name) {
		for(JButton but : Button_list) {
			if(but.getName()==name) {
				return but;
			}
		}
		return null;
	}
}