package Model;

public class GameModel {
	private static final int map_width = 675;
	private static final int map_height = 650;
	private static final int panel_width = 300;
	private static final int panel_height = 650;
	
	private MapModel map;
	private InventoryModel inventory;

	public GameModel() {
		map = new MapModel(this);
		inventory = new InventoryModel();
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
}
