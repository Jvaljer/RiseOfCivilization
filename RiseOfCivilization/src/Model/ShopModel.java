package Model;

public class ShopModel {
	private GameModel game;
	private final static int width = 400;
	private final static int height = 300;
	
	public ShopModel(GameModel GM) {
		game = GM;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
}
