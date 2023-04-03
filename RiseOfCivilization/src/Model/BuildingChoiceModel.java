package Model;

public class BuildingChoiceModel {
	private GameModel game;
	
	private final static int width = 500;
	private final static int height = 200;
	
	public BuildingChoiceModel(GameModel GM) {
		game = GM;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
}
