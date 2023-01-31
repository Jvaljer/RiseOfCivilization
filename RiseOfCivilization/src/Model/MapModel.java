package Model;

import java.util.*;

import Types.CellId;

public class MapModel {
	private static final int lines = 15;
	private static final int columns = 15;
	private ArrayList<ArrayList<CellModel>> grid;
	
	public MapModel() {
		//must add calcul of starting point, and all percentages for Cell types
		grid = new ArrayList<ArrayList<CellModel>>();
		for(int j=0; j<lines; j++) {
			grid.add(new ArrayList<CellModel>());
			for(int i=0; i<columns; i++) {
				grid.get(j).add( new CellModel(i,j, CellId.Plain) );
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