package View;

import Model.GameModel;
import Model.InventoryModel;
import Model.WorkerModel;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

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
	private DashboardView dashboard_view;
	private InventoryModel global_inventory;
	private ArrayList<JLabel> labels;
	private JLabel wood_label;
	private JLabel stone_label;
	private JLabel iron_label;
	private JLabel gold_label;
	
	public CityInfoView(GameModel gm, DashboardView dv) {
		game_model = gm;
		dashboard_view = dv;
		global_inventory = game_model.getInventoryModel();
		labels = new ArrayList<JLabel>();
		ArrayList<WorkerModel> workers = game_model.GetWorkerModel();
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		wood_label = new JLabel();
		labels.add(wood_label);
		c.gridx = 0;
		c.gridy = 0;
		add(wood_label, c);
		stone_label = new JLabel();
		labels.add(stone_label);
		c.gridx = 1;
		c.gridy = 0;
		add(stone_label, c);
		iron_label = new JLabel();
		labels.add(iron_label);
		c.gridx = 0;
		c.gridy = 1;
		add(iron_label, c);
		gold_label= new JLabel();
		labels.add(gold_label);
		c.gridx = 1;
		c.gridy = 1;
		add(gold_label, c);
		
		for (JLabel label : labels) {
			label.setFont(label.getFont().deriveFont(15.0f));
		}
		
		update();

		setPreferredSize(new Dimension(dashboard_view.getWidth(), (int) (dashboard_view.getHeight()/6)));
		setBackground(Color.GRAY);
	}
	
	public void update() {
		wood_label.setText("Wood : " + global_inventory.getAmount(Resource.Wood));
		stone_label.setText("Stone : " + global_inventory.getAmount(Resource.Stone));
		iron_label.setText("Iron : " + global_inventory.getAmount(Resource.Iron));
		gold_label.setText("Gold : " + global_inventory.getAmount(Resource.Gold));
	}
}