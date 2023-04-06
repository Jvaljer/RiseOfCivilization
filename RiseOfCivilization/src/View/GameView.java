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
	private CellInfoView cell_info_view;
	private CityInfoView city_info_view;
	private DashboardView dashboard_view;

	public int win_width;
	public int win_height;
	
	public GameView(GameModel m) {
		model = m;
		map_model = model.GetMapModel();
		
		setTitle("Rise Of Civilizations");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		win_width = this.getWidth();
		win_height = this.getHeight();
		
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
		cell_info_view = new CellInfoView(model, map_model);
		city_info_view = new CityInfoView(map_model, model);
		Action_view = new ActionView(map_model);
		dashboard_view = new DashboardView(this, cell_info_view, city_info_view, Action_view);

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
	
	public CellInfoView getCellInfoView() {
		return cell_info_view;
	}

	public DashboardView GetDashboardView() {
		return dashboard_view;
	}

	public void addWorkerView() {
		int size = this.model.GetWorkerModel().size();
		this.workers_view.add(new WorkerView(this, this.model.GetWorkerModel().get(size)));
	}
	
	public void AddBuildingView(BuildingModel BM) {
		buildings_view.add(new BuildingView(this,BM));
	}

	public ArrayList<WorkerView> getWorkerView() {
		return this.workers_view;
	}
	
	public ArrayList<BuildingView> GetBuildingsView(){
		return buildings_view;
	}

	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		map_view.DrawMap(G);
	}
}
