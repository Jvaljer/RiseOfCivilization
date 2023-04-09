package Model;

import Types.*;
import java.awt.*;

/**
 * BuildingModel est la classe responsable des différents batiments posable sur les Cellules de la map
 * 
 * @author Abel Martin
 */
public class BuildingModel {
	// Id of the building on the Cell
	private BuildingId id;
	// Main model of the game that gather all information of the game
	private GameModel game;
	// Model of the map
	private MapModel map;
	// Double Array of CellModel
	private CellModel[][] model_grid;
	// Model of the cell
	private CellModel cell;
	// Inventory of the Building
	private InventoryModel inventory;
	//Type of ressource that the building produce
	private Resource produced_resource;
	// Level of the building
	private int level;
	// Const that use to draw the building
	private static final int rec_width = 20;
	private static final int rec_height = 15;
	private static final int triangle_dist = 10;
	private static final int poly_dist = 5;
	// Boolean that describe if there is a player on the cell
	private boolean occupied;
	
	/**
	 * Constructor for the Building Model
	 * @param G The GameModel
	 * @param C Position on the map
	 * @param bid Id of the building we want to build
	 */
	public BuildingModel(GameModel G, Point C, BuildingId bid) {
		game = G;
		map = game.GetMapModel();
		model_grid = map.GetGrid();
		cell = model_grid[C.x][C.y];
		cell.build(this);
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
	
	/**
	 * Getter for the ID of the building
	 * @return this.id
	 */
	public BuildingId getId() {
		return id;
	}
	
	/**
	 * Getter for the position of the building
	 * @return this.cell.GetCoord()
	 */
	public Point GetPos() {
		return cell.GetCoord();
	}

	/**
	 * Getter for the width of the building
	 * @return this.rec_width
	 */
	public int GetRecWidth() {
		return rec_width;
	}

	/**
	 * Getter for the height of the building
	 * @return this.rec_height
	 */
	public int GetRecHeight() {
		return rec_height;
	}

	/**
	 * Getter for the TriangleDist
	 * @return this.triangle_dist
	 */
	public int GetTriangleDist() {
		return triangle_dist;
	}

	/**
	 * Getter for the poly_dist
	 * @return this.poly_dist
	 */
	public int GetPolyDist() {
		return poly_dist;
	}
	
	/**
	 * Getter for the Inventory of the building
	 * @return this.inventory
	 */
	public InventoryModel GetInventory() {
		return inventory;
	}
	
	/**
	 * Getter for the level of the building
	 * @return this.level
	 */
	public int GetLevel() {
		return level;
	}
	
	/**
	 * Getter for the type of Ressource that the building produce
	 * @return this.produced_resource
	 */
	public Resource GetProducedResource() {
		return produced_resource;
	}
	
	/**
	 * LevelUp Method 
	 * increase the level of the building
	 * increase the capacity of the inventory associated with the building
	 */
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
	
	/**
	 * The Train Method
	 * Create a new worker, the Role of the worker depend on the type of the building
	 * Barrack -> Knight
	 * LumberCamp -> LumberJack
	 * MinerCamp -> Miner
	 * QuarryWorkerCamp -> QuarryWorker
	 */
	public void Train() {
		if(!game.CanCreateNewWorker(this)) {
			return;
		}
		switch (id) {
			case Barrack:
				game.AddWorker(WorkerRole.Knight,this.cell.GetCoord());
				break;
				
			case LumberCamp:
				game.AddWorker(WorkerRole.Lumberjack,this.cell.GetCoord());
				break;
				
			case MinerCamp:
				game.AddWorker(WorkerRole.Miner,this.cell.GetCoord());
				break;
				
			case QuarryWorkerCamp:
				game.AddWorker(WorkerRole.QuarryWorker,this.cell.GetCoord());
				break;
			
			default:
				break;
		}
	}
	
	/**
	 * The Occupy Method 
	 * set the occupied variable to True
	 */
	public void Occupy() {
		occupied = true;
	}
	
	/**
	 * The Free Method set the variable occupied to False
	 */
	public void Free() {
		occupied = false;
	}
	
	/**
	 * Getter for the occupied variable
	 * @return this.occupied
	 */
	public boolean GetOccupied() {
		return occupied;
	}
}
