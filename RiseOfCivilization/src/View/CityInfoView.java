package View;

import Model.GameModel;
import Model.InventoryModel;
import Model.WorkerModel;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private ArrayList<WorkerModel> workers;
	private GridBagLayout layout;
	private JLabel wood_label;
	private JLabel stone_label;
	private JLabel iron_label;
	private JLabel gold_label;
	private JLabel workers_label;
	private final static int offset = 5;
	
	public CityInfoView(GameModel gm, DashboardView dv) {
		game_model = gm;
		dashboard_view = dv;
		global_inventory = game_model.getInventoryModel();
		labels = new ArrayList<JLabel>();
		workers = game_model.GetWorkerModel();
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints left_c = new GridBagConstraints();
		left_c.anchor = GridBagConstraints.NORTHWEST;
		left_c.fill = GridBagConstraints.HORIZONTAL;
		
		wood_label = new JLabel();
		labels.add(wood_label);
		left_c.weightx = 0.5;
		left_c.insets = new Insets(0, offset, 0, 0);
		left_c.gridx = 0;
		left_c.gridy = 0;
		add(wood_label, left_c);
		
		iron_label = new JLabel();
		labels.add(iron_label);
		left_c.gridy = 1;
		add(iron_label, left_c);
		
		workers_label = new JLabel();
		labels.add(workers_label);
		left_c.gridwidth = 2;
		left_c.gridy = 2;
		add(workers_label, left_c);
		
		GridBagConstraints right_c = new GridBagConstraints();
		right_c.anchor = GridBagConstraints.NORTHWEST;
		right_c.fill = GridBagConstraints.HORIZONTAL;
		
		stone_label = new JLabel();
		labels.add(stone_label);
		right_c.weightx = 0.5;
		right_c.gridx = 1;
		right_c.gridy = 0;
		add(stone_label, right_c);
		
		gold_label= new JLabel();
		labels.add(gold_label);
		right_c.gridy = 1;
		add(gold_label, right_c);
		
		for (JLabel label : labels) {
			label.setFont(label.getFont().deriveFont(15.0f));
		}
		
		update();

		setPreferredSize(new Dimension(dashboard_view.getWidth(), (int) (dashboard_view.getHeight()/3)));
		setBackground(Color.GRAY);
	}
	
	public void update() {
		wood_label.setText("Wood : " + global_inventory.getAmount(Resource.Wood));
		stone_label.setText("Stone : " + global_inventory.getAmount(Resource.Stone));
		iron_label.setText("Iron : " + global_inventory.getAmount(Resource.Iron));
		gold_label.setText("Gold : " + global_inventory.getAmount(Resource.Gold));
		workers_label.setText("Free Workers : " + workers.stream().filter(w -> !w.GetOccupied()).count() + "/" + workers.size());
	}
}