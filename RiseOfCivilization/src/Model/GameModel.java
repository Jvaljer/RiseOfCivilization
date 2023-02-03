package Model;

public class GameModel {
	private static final int map_width = 725;
	private static final int map_height = 700;
	private static final int panel_width = 300;
	private static final int panel_height = 700;
	
	private MapModel map;

	public GameModel() {
		map = new MapModel(this);
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
}
