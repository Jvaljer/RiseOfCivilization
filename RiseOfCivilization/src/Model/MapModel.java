package Model;

import java.util.*;

public class MapModel {
	private static final int lines = 15;
	private static final int columns = 15;
	private ArrayList<ArrayList<CellModel>> grid;
	
	public MapModel() {
		//must implement
	}
	
	public int GetLinesAmount() {
		return lines;
	}
	
	public int GetColumnsAmount() {
		return columns;
	}
	
	public ArrayList<ArrayList<CellModel>> GetGrid(){
		return grid;
	}
	
	public CellModel GetCellFromCoord(int x, int y) {
		return grid.get(x).get(y);
	}
}