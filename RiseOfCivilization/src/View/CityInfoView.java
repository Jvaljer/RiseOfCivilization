package View;

import Model.GameModel;
import Model.InventoryModel;
import Model.MapModel;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
	private GameModel game_model;
	private MapModel map_model;
	private InventoryModel global_inventory;
	private JLabel wood_label;
	private JLabel stone_label;
	private JLabel iron_label;
	private JLabel gold_label;
	
	public CityInfoView(GameModel gm, MapModel mm) {
		game_model = gm;
		map_model = mm;
		global_inventory = game_model.getInventoryModel();
		
		wood_label = new JLabel(); add(wood_label);
		stone_label = new JLabel(); add(stone_label);
		iron_label = new JLabel(); add(iron_label);
		gold_label= new JLabel(); add(gold_label);
		
		update();

		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/3));
		setBackground(Color.GRAY);
	}
	
	public void update() {
		wood_label.setText("Wood : " + global_inventory.getAmmount(Resource.Wood));
		stone_label.setText("Stone : " + global_inventory.getAmmount(Resource.Stone));
		iron_label.setText("Iron : " + global_inventory.getAmmount(Resource.Iron));
		gold_label.setText("Gold : " + global_inventory.getAmmount(Resource.Gold));
	}
}