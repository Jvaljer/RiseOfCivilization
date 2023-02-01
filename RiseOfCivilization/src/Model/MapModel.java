package Model;

import java.util.*;

import Types.CellId;

public class MapModel {
	private static final int lines = 15;
	private static final int columns = 15;
	private CellModel[][] grid;
	
	public MapModel() {
		//must add calcul of starting point, and all percentages for Cell types
		grid = new CellModel[lines][columns];
		for(int i=0; i<lines; i++) {
			for(int j=0; j<columns; j++) {
				grid[i][j] = new CellModel(i,j,CellId.None);
			}
		}
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