package Model;

import Types.*;

public class CellModel {
	private static int abs;
	private static int ord;
	private static final int width = 20;
	private static final int height = 20;
	private CellId id;
	
	public CellModel(int x, int y, CellId i) {
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