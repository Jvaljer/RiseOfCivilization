package View;

import javax.swing.*;
import Model.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	private GameModel model;
	private MapModel map_model;
	private InventoryModel inventory_model;
	
	private MapView map_view;
	private ArrayList<WorkerView> workers_view;
	private InfobarView infobar_view;
	private MinimapView minimap_view;
	private InventoryView inventory_view;
	private DashboardView dashboard_view;
	
	public GameView(GameModel m) {
		model = m;
		map_model = model.GetMapModel();
		inventory_model = model.getInventoryModel();
		
		map_view = new MapView(this,map_model);
		workers_view = new ArrayList<WorkerView>(10);		
		for(int i = 0; i < this.model.GetWorkerModel().size(); i++)
		{
			workers_view.add(new WorkerView(this, this.model.GetWorkerModel().get(i)));
		}
		minimap_view = new MinimapView(map_model);
		inventory_view = new InventoryView(map_model, inventory_model);
		infobar_view = new InfobarView(map_model);
		dashboard_view = new DashboardView(map_model, minimap_view, inventory_view, infobar_view);
		
		setTitle("Rise Of Civilizations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		add(map_view, BorderLayout.CENTER);
		add(dashboard_view, BorderLayout.EAST);

		pack();
		setVisible(true);
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	
	public MapView GetMapView() {
		return map_view;
	}

	public DashboardView GetDashboardView() {
		return dashboard_view;
	}

	
	public ArrayList<WorkerView> getWorkerView() {
		return this.workers_view;
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		map_view.DrawMap(G);
		//inventory_view.DrawInventory(G);
	}
}
