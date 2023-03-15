package Model;

import java.util.ArrayList;

import Types.BuildingId;
import Types.WorkerRole;

public class GameModel {
	private static final int map_width = 675;
	private static final int map_height = 650;
	private static final int panel_width = 300;
	private static final int panel_height = 650;
	
	private MapModel map;
	private InventoryModel inventory;
	private ArrayList<WorkerModel> workers;
	private ArrayList<BuildingModel> buildings;

	public GameModel() {
		map = new MapModel(this);
		inventory = new InventoryModel();
		
		workers = new ArrayList<WorkerModel>(10);
		workers.add(new WorkerModel(this,WorkerRole.Worker));
		
		buildings = new ArrayList<BuildingModel>();
		buildings.add(new BuildingModel(this,map.GetCityOriginCoord(),BuildingId.CityHall));
	}
	
	public int GetMapWidth() {
		return map_width;
	}
	
	public int GetMapHeight() {
		return map_height;
	}
	
	public int GetPanelWidth() {
		return panel_width;
	}
	
	public int GetPanelheight() {
		return panel_height;
	}
	
	public MapModel GetMapModel() {
		return map;
	}
	
	public InventoryModel getInventoryModel() {
		return inventory;
	}
	
	public ArrayList<WorkerModel> GetWorkerModel() {
		return workers;
	}
	
	public ArrayList<BuildingModel> GetBuildingList(){
		return buildings;
	}
}
