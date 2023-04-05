package Model;

import Types.*;
import java.awt.*;

public class BuildingModel {
	private GameModel game;
	private MapModel map;
	private CellModel[][] model_grid;
	private CellModel cell;
	private BuildingId id;
	private InventoryModel inventory;
	private Resource produced_resource;
	private int level;
	private static final int rec_width = 20;
	private static final int rec_height = 15;
	private static final int triangle_dist = 10;
	private static final int poly_dist = 5;
	private boolean occupied;
	
	public BuildingModel(GameModel G, Point C, BuildingId bid) {
		game = G;
		map = game.GetMapModel();
		model_grid = map.GetGrid();
		cell = model_grid[C.x][C.y];
		id = bid;
		
		switch(id) {
			case SawMill:
				produced_resource = Resource.Wood;
				break;
			case Mine:
				produced_resource = Resource.Stone;
				break;
			case Quarry:
				produced_resource = Resource.Iron;
				break;
			default:
				produced_resource = null;
				break;
		}
		
		inventory = new InventoryModel(200);
		level = 1;
		
		occupied = false;
	}
	
	public BuildingId GetId() {
		return id;
	}
	public Point GetPos() {
		return cell.GetCoord();
	}
	public int GetRecWidth() {
		return rec_width;
	}
	public int GetRecHeight() {
		return rec_height;
	}
	public int GetTriangleDist() {
		return triangle_dist;
	}
	public int GetPolyDist() {
		return poly_dist;
	}
	public InventoryModel GetInventory() {
		return inventory;
	}
	public int GetLevel() {
		return level;
	}
	public Resource GetProducedResource() {
		return produced_resource;
	}
	public void LevelUp() {
		level += 1;
		switch(level) {
		case 2:
			inventory.IncreaseMaxAmount(150);
			break;
		case 3:
			inventory.IncreaseMaxAmount(150);
			break;
		default:
			break;
		}
	}
	
	public void Train() {
		if(!game.CanCreateNewWorker(this)) {
			return;
		}
		switch (id) {
			case Barrack:
				game.AddWorker(WorkerRole.Knight,this.cell.GetCoord());
				break;
				
			case LumberCamp:
				game.AddWorker(WorkerRole.LumberJack,this.cell.GetCoord());
				break;
				
			case MinerCamp:
				game.AddWorker(WorkerRole.Miner,this.cell.GetCoord());
				break;
				
			case QuarrymanCamp:
				game.AddWorker(WorkerRole.QuarryMan,this.cell.GetCoord());
				break;
			
			default:
				break;
		}
	}
	
	public void Occupy() {
		occupied = true;
	}
	
	public void Free() {
		occupied = false;
	}
	
	public boolean GetOccupied() {
		return occupied;
	}
}
