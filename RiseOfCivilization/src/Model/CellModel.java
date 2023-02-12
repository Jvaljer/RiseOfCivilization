package Model;

import Types.*;

/**
 * Model of a cell, containing all position & Id values of a cell. Ans the 
 * needed methods to act on it properly.
 * @author abel
 */
public class CellModel {
	private int X;
	private int Y;
	private CellId id;
	
	public CellModel(int x, int y, CellId i) {
		X = x;
		Y = y;
		id = i;
	}
	
	public int GetX() {
		return X;
	}
	
	public int GetY() {
		return Y;
	}
	
	public CellId GetId() {
		return id;
	}
	
	public void TurnToCity() {
		id = CellId.City;
	}
	
}