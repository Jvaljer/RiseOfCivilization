package View;

import Model.GameModel;
import Model.InventoryModel;
import Model.MapModel;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

/**
 * This class is the view of the city's information. It shows the
 * global inventory, the number of available and busy worker.
 * It is represented in a seperate panel in the middle of the dashboard.
 * Its default height is a sixth of the height of the dashboard.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class CityInfoView extends JPanel {
	private MapModel map_model;
	private GameModel game_model;
	
	public CityInfoView(MapModel mm, GameModel gm) {
		map_model = mm;
		game_model = gm;
		InventoryModel global_inventory = game_model.getInventoryModel();
		
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			Resource resource = resource_values[i];
			JLabel label = new JLabel(resource + " : " + global_inventory.getAmmount(resource));
			add(label);
		}

		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/3));
		setBackground(Color.GRAY);
	}
}