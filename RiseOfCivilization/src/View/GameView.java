package View;

import javax.swing.*;
import Model.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * this is the global view of the game this.
 * It represents the main window of the game
 */
@SuppressWarnings("serial")
public class GameView extends JFrame {
	private GameModel model;
	private MapModel map_model;

	private MapView map_view;
	private ArrayList<WorkerView> workers_view;
	private ArrayList<BuildingView> buildings_view;
	private DashboardView dashboard_view;
	private CellInfoView cell_info_view;
	private CityInfoView city_info_view;
	private ActionView action_view;
	private ArrayList<EnnemyView> ennemies_view;

	public int win_width;
	public int win_height;
	/**
	 * constructor of GameView it creates all the necessary secondary views and give them their model
	 * also sets the title of the window
	 */
	public GameView(GameModel m) {
		model = m;
		map_model = model.GetMapModel();
		
		setTitle("Rise Of Civilizations");
		//setExtendedState(MAXIMIZED_BOTH);
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

		dashboard_view = new DashboardView(model, this);
		cell_info_view = dashboard_view.getCellInfoView();
		city_info_view = dashboard_view.getCityInfoView();
		action_view = dashboard_view.GetActionView();

		ennemies_view = new ArrayList<EnnemyView>();
		
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
	
	public CellInfoView getCellInfoView() {
		return cell_info_view;
	}
	
	public CityInfoView getCityInfoView() {
		return city_info_view;
	}
	
	public ActionView getActionViewView() {
		return action_view;
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
	
	public ArrayList<EnnemyView> GetEnnemiesView(){
		return ennemies_view;
	}
	
	public void AddEnnemyView(EnnemyModel ennemy) {
		ennemies_view.add(new EnnemyView(this,ennemy));
	}
	/** removes the given worker */
	public void RemoveWorker(WorkerModel worker) {
		for(int i=0; i<workers_view.size(); i++) {
			WorkerView wv = workers_view.get(i);
			if(wv.GetModel()==worker) {
				workers_view.remove(i);
			}
		}
	}
	/** removes the given enemy */
	public void RemoveEnnemy(EnnemyModel ennemy) {
		for(int i=0; i<ennemies_view.size(); i++) {
			EnnemyView ev = ennemies_view.get(i);
			if(ev.GetModel()==ennemy) {
				ennemies_view.remove(i);
			}
		}
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		map_view.DrawMap(G);
	}
}
