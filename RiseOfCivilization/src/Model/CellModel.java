package Model;

import Types.*;

public class CellModel {
	private static int X;
	private static int Y;
	private static int Z;
	private CellId id;
	
	public CellModel(int x, int y, CellId i) {
		X = x;
		Y = y;
		Z = -X -Y;
		id = i;
	}
	
	public int GetX() {
		return X;
	}
	
	public int GetY() {
		return Y;
	}
	
	public int GetZ() {
		return Z;
	}
	
	public CellId GetId() {
		return id;
	}
	
}