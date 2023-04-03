package View;

import Model.CellModel;
import Model.GameModel;
import Model.MapModel;
import Types.CellId;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is the view of the minimap. The minimap is represented in
 * a seperate panel at the top of the infobar panel.
 * Its default height is a third of the height of the infobar.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class CellInfoView extends JPanel {
	private GameModel game_model;
	private MapModel map_model;
	private JLabel cell_type;
	private JLabel cell_inventory;
	private JLabel building_label;
	private JLabel worker_label;
	
	public CellInfoView(GameModel gm, MapModel mm) {
		game_model = gm;
		map_model = mm;
	
		cell_type = new JLabel();
		add(cell_type);
		cell_inventory = new JLabel();
		add(cell_inventory);
		building_label = new JLabel();
		add(building_label);
		worker_label= new JLabel();
		add(worker_label);
		
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/3));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void update(CellModel cell_model) {
		cell_type.setText("Cell type : " + cell_model.GetId());
		if(cell_model.GetId() != CellId.Plain) {
			cell_inventory.setText("Cell Resources : " + cell_model.getResourceAmount());
		} else {
			cell_inventory.setText("Cell Resources : None");
		}
		building_label.setText("Building : ");
		worker_label.setText("Worker");
	}
}