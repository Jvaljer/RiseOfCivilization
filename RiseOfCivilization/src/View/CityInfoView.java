package View;

import Model.GameModel;
import Model.InventoryModel;
import Model.WorkerModel;
import Types.Resource;
import Types.WorkerRole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class is the view of the city's information. It shows the city's
 * stash (inventory), the number of total worker, and the number of
 * workers per role.
 * It is represented in its own panel in the middle of the dashboard.
 * Its default height is a third of the height of the dashboard.
 *
 * @author Martin
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
	private JLabel total_workers_label;
	private JLabel lumberjack_label;
	private JLabel quarry_worker_label;
	private JLabel miner_label;
	private JLabel knight_label;
	private final static int offset = 10;
	
	/**
     * This is the constructor of the CityInfoView. It uses a
	 * GridBagLayout and creates all of its labels and their
	 * constraints before adding them. The labels aren't
	 * initialized with text and only get one after update()
	 * is called at the end of the constructor.
     *
     * @param gm the game model
	 * @param dv the dashboard view
     */
	public CityInfoView(GameModel gm, DashboardView dv) {
		game_model = gm;
		dashboard_view = dv;
		global_inventory = game_model.getInventoryModel();
		labels = new ArrayList<JLabel>();
		workers = game_model.GetWorkerModel();
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		JLabel title = new JLabel("<HTML><U>City Info :</U></HTML>");
		title.setFont(title.getFont().deriveFont(18.0f));
		GridBagConstraints title_c = new GridBagConstraints();
		title_c.anchor = GridBagConstraints.NORTHWEST;
		title_c.fill = GridBagConstraints.HORIZONTAL;
		title_c.weightx = 1;
		title_c.weighty = 0;
		title_c.gridwidth = 2;
		title_c.insets = new Insets(0, (int) (offset/2), 0, 0);
		title_c.gridx = 0;
		title_c.gridy = 0;
		add(title, title_c);
		
		GridBagConstraints left_c = new GridBagConstraints();
		left_c.anchor = GridBagConstraints.NORTHWEST;
		left_c.fill = GridBagConstraints.HORIZONTAL;
		left_c.weightx = 0.5;
		left_c.weighty = 0;
		left_c.insets = new Insets(0, offset, 0, 0);
		left_c.gridx = 0;
		
		wood_label = new JLabel();
		labels.add(wood_label);
		left_c.gridy = 2;
		add(wood_label, left_c);
		
		iron_label = new JLabel();
		labels.add(iron_label);
		left_c.gridy = 3;
		add(iron_label, left_c);
		
		lumberjack_label = new JLabel();
		labels.add(lumberjack_label);
		left_c.gridy = 5;
		add(lumberjack_label, left_c);
		
		quarry_worker_label = new JLabel();
		labels.add(quarry_worker_label);
		left_c.gridy = 6;
		add(quarry_worker_label, left_c);
		
		JLabel stash = new JLabel("<HTML><U>Stash :</U></HTML>");
		stash.setFont(stash.getFont().deriveFont(15.0f));
		left_c.insets = new Insets(15, offset, 0, 0);
		left_c.gridwidth = 2;
		left_c.gridy = 1;
		add(stash, left_c);
		
		total_workers_label = new JLabel();
		total_workers_label.setFont(total_workers_label.getFont().deriveFont(15.0f));
		left_c.gridwidth = 2;
		left_c.gridy = 4;
		add(total_workers_label, left_c);
		
		GridBagConstraints right_c = new GridBagConstraints();
		right_c.anchor = GridBagConstraints.NORTHWEST;
		right_c.fill = GridBagConstraints.HORIZONTAL;
		right_c.weightx = 1;
		right_c.weighty = 0;
		right_c.gridx = 1;
		
		stone_label = new JLabel();
		labels.add(stone_label);
		right_c.gridy = 2;
		add(stone_label, right_c);
		
		gold_label= new JLabel();
		labels.add(gold_label);
		right_c.gridy = 3;
		add(gold_label, right_c);
		
		miner_label = new JLabel();
		labels.add(miner_label);
		right_c.gridy = 5;
		add(miner_label, right_c);
		
		knight_label = new JLabel();
		labels.add(knight_label);
		right_c.gridy = 6;
		right_c.weighty = 1;
		add(knight_label, right_c);
		
		for (JLabel label : labels) {
			label.setFont(label.getFont().deriveFont(13.0f));
		}
		
		update();

		setPreferredSize(new Dimension(dashboard_view.getWidth(), (int) (dashboard_view.getHeight()/3)));
		setBackground(Color.GRAY);
	}
	
	/**
	 * Updates all the labels of the CityInfoView. Resources are fetched
	 * from the game's inventory (the city's stash), while worker counts
	 * are fetched from the game's list of all the workers.
	 * This method is called whenever an action would cause to modify the
	 * content of the previously mentioned objects.
	 */
	public void update() {
		wood_label.setText("Wood : " + global_inventory.getAmount(Resource.Wood));
		stone_label.setText("Stone : " + global_inventory.getAmount(Resource.Stone));
		iron_label.setText("Iron : " + global_inventory.getAmount(Resource.Iron));
		gold_label.setText("Gold : " + global_inventory.getAmount(Resource.Gold));
		total_workers_label.setText("<HTML><U>Total Workers : " + workers.size() + "</U></HTML>");
		lumberjack_label.setText("Lumberjacks : " + workers.stream().filter(w -> w.GetRole() == WorkerRole.Lumberjack).count());
		quarry_worker_label.setText("Quarry Workers : " + workers.stream().filter(w -> w.GetRole() == WorkerRole.QuarryWorker).count());
		miner_label.setText("Miners : " + workers.stream().filter(w -> w.GetRole() == WorkerRole.Miner).count());
		knight_label.setText("Knights : " + workers.stream().filter(w -> w.GetRole() == WorkerRole.Knight).count());
	}
}