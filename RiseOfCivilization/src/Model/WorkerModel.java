package Model;

import java.awt.*;
import Types.*;

public class WorkerModel {
	private GameModel model;
	private MapModel map_model;
	private CellModel[][] model_grid;
	private CellModel cell;
	private static final int width = 10;
	private static final int height = 10;
	private boolean moving;
	private boolean occupied;
	private WorkerRole role;
	
	public WorkerModel(GameModel M, WorkerRole R) {
		model = M;
		map_model = model.GetMapModel();
		model_grid = map_model.GetGrid();
		this.cell = this.map_model.GetCellFromCoord(0, 0);
		moving = false;
		role = R;
	}

	public Point getPos()
	{
		return this.cell.GetCoord();
	}
	
	public int getCordX()
	{
		return this.cell.GetX();
	}
	
	public int getCordY()
	{
		return this.cell.GetY();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public boolean GetOccupied()
	{
		return this.occupied;
	}
	
	public void MoveTo(int i, int j) {
		this.cell = this.model.GetMapModel().GetCellFromCoord(i, j);
	}
	
	public void isOccupied() {
		this.occupied = true;
	}

	public void isMoving()
	{
		this.moving = true;
	}

	public void isNotMoving()
	{
		this.moving = false;
	}
	
	public void isFree() {
		this.occupied = false;
	}
	
	public boolean GetMoving() {
		return moving;
	}
}
