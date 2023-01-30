package Model;

public class CellModel {
	private static int abs;
	private static int ord;
	private static final int width = 20;
	private static final int height = 20;
	
	public CellModel(int x, int y) {
		abs = x;
		ord = y;
	}
	
	public int GetAbs() {
		return abs;
	}
	
	public int GetOrd() {
		return ord;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
}