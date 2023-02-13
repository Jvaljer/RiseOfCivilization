package Model;

import java.awt.*;
import Types.*;

public class WorkerModel {
	private GameModel model;
	private MapModel map_model;
	private CellModel[][] model_grid;
	private Point pos;
	private static final int width = 10;
	private static final int height = 10;
	private boolean occupied;
	private WorkerRole role;
	
	public WorkerModel(GameModel M, WorkerModel R) {
		model = M;
		map_model = model.GetMapModel();
		model_grid = map_model.GetGrid();
		pos = map_model.GetCityOriginCoord();
		occupied = false;
		
	}
	
	public Point GetPos() {
		return pos;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
	public void NewPos(int i, int j) {
		pos.x = i;
		pos.y = j;
	}
	
	public boolean GetOccupied() {
		return occupied;
	}
}
