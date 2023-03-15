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
		Icon wrench = new ImageIcon("./img/Wrench_v2.jpg");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets=new Insets(5,10,5,10);
		c.ipady=50-button.getMinimumSize().height;
		c.ipadx=50-button.getMinimumSize().width;

		button.setName("Build");
		button.setIcon(wrench);
		c.gridx = 0;
		c.gridy = 0;
		button.setIcon(wrench);
		Button_list.add(button);
		add(button, c);
		
		button = new JButton("");
		button.setName("LevelUp");
		c.gridx = 1;
		c.gridy = 0;
		button.setIcon(wrench);
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Move");
		c.gridx = 2;
		c.gridy = 0;
		button.setIcon(wrench);
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Collect");
		c.gridx = 0;
		c.gridy = 1;
		button.setIcon(wrench);
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Train");
		c.gridx = 1;
		c.gridy = 1;
		button.setIcon(wrench);
		Button_list.add(button);
		add(button, c);

		button = new JButton("");
		button.setName("Expand");
		c.gridx = 2;
		c.gridy = 1;
		button.setIcon(wrench);
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