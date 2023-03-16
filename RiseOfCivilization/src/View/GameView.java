package View;

import javax.swing.*;
import Model.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	private GameModel model;
	private MapModel map_model;

	private MapView map_view;
	private ArrayList<WorkerView> workers_view;
	private ArrayList<BuildingView> buildings_view;
	private ActionView Action_view;
	private MinimapView minimap_view;
	private CityInfoView city_info_view;
	private DashboardView dashboard_view;

	public GameView(GameModel m) {
		model = m;
		map_model = model.GetMapModel();

		map_view = new MapView(this,map_model);
		workers_view = new ArrayList<WorkerView>();
		for(int i = 0; i < this.model.GetWorkerModel().size(); i++)
		{
			workers_view.add(new WorkerView(this, this.model.GetWorkerModel().get(i)));
		}
		buildings_view = new ArrayList<BuildingView>();
		for(int i=0; i<model.GetBuildingList().size(); i++) {
			buildings_view.add(new BuildingView(this,model.GetBuildingList().get(i)));
		}
		minimap_view = new MinimapView(map_model);
		city_info_view = new CityInfoView(map_model, model);
		Action_view = new ActionView(map_model);
		dashboard_view = new DashboardView(map_model, minimap_view, city_info_view, Action_view);

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
	}
}
