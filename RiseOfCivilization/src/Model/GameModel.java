package Model;

import Types.WorkerRole;

public class GameModel {
	private static final int map_width = 675;
	private static final int map_height = 650;
	private static final int panel_width = 300;
	private static final int panel_height = 650;
	
	private MapModel map;
	private InventoryModel inventory;
	private WorkerModel player;

	public GameModel() {
		map = new MapModel(this);
		inventory = new InventoryModel();
		player = new WorkerModel(this,WorkerRole.Worker);
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
	
	public WorkerModel GetWorkerModel() {
		return player;
	}
}
