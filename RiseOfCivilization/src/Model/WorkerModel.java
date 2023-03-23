package Model;

import java.awt.*;
import Types.*;

public class WorkerModel {
	private GameModel model;
	private MapModel map_model;
	private CellModel[][] model_grid;
	private CellModel cell;
	private Point next_cord;
	private static final int width = 10;
	private static final int height = 10;
	private InventoryModel inventory;
	private boolean moving;
	private boolean occupied;
	private WorkerRole role;
	private int harvest_capacity;
	private int level;
	
	public WorkerModel(GameModel M, WorkerRole R, Point pts) {
		model = M;
		map_model = model.GetMapModel();
		model_grid = map_model.GetGrid();
		cell = map_model.GetCellFromCoord(pts.x,pts.y);
		harvest_capacity = 100;
		inventory = new InventoryModel(harvest_capacity*3);
		moving = false;
		occupied = false;
		role = R;
		level = 1;
	}

	public WorkerRole GetRole() {
		return role;
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
		System.out.println(this.cell.GetCoord());
	}
	
	public void occupied() {
		this.occupied = true;
	}

	public void moving()
	{
		this.moving = true;
	}

	public void stopMoving()
	{
		this.moving = false;
	}
	
	public void Free() {
		this.occupied = false;
	}
	
	public boolean GetMoving() {
		return moving;
	}

	public void setNextCord(Point p)
	{
		this.next_cord = p;
	}

	public Point getNextCord()
	{
		return this.next_cord;
	}
	
	public void harvest(Resource r) {
		inventory.add(r, harvest_capacity);
	}
	public void harvest(Resource r, int n) {
		inventory.add(r, n);
	}
	
	public void dropOff(Resource r, int ammount) {
		inventory.remove(r, ammount);
	}
	
	public int GetHarvestCapacity() {
		return harvest_capacity;
	}
	public InventoryModel getInventory() {
		return inventory;
	}
	public int GetLevel() {
		return level;
	}
	
	public void LevelUp() {
		level += 1;
		switch(level) {
			case 2:
				harvest_capacity+=100;
				break;
			case 3:
				harvest_capacity+=80;
				break;
			case 4:
				harvest_capacity+=60;
				break;
			case 5:
				harvest_capacity+=60;
				break;
			default:
				break;
		}
	}
	
	public boolean InventoryIsFull() {
		Resource res_type;
		switch (role) {
			case Worker:
				
				return true;
				
			case Miner:
				res_type = Resource.Stone;
				break;
				
			case LumberJack:
				res_type = Resource.Wood;
				break;
				
			case QuarryMan:
				res_type = Resource.Iron;
				break;
				
			default:
				res_type = null;
				break;
		}
		
		int res_amount = inventory.getAmmount(res_type);
		return (res_amount==inventory.GetMaxAmount());
	}
}
