package Model;

import java.util.ArrayList;
import Types.*;
import Types.BuildingId;
import Types.Resource;
import Types.WorkerRole;
import java.awt.*;

/**
 * The GameModel classe is the main Model of this game it gather all the entity on the game
 * 
 * @author all the groupe
 */
public class GameModel {
	// Constante on this game
	private static final int time_unit = 60;
	private static int map_width;
	private static int map_height;
	private static int panel_width;
	private static int panel_height;
	
	//Model of the map
	private MapModel map;
	// Inventory of the player 
	private InventoryModel inventory;
	// List of all the workers currently in the game
	private ArrayList<WorkerModel> workers;
	// List of all the building currently in the game
	private ArrayList<BuildingModel> buildings;
	//List of all the ennemies currently in the game
	private ArrayList<EnnemyModel> ennemies;

	private GoalsModel goals;

	/**
	 * Constructor for the GameModel
	 * 
	 * Initialised a Position for the city 
	 * Initialised 3 worker on of each type
	 * 
	 */
	public GameModel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		map_width = ((int) screenSize.getWidth()) - ((int) (screenSize.getHeight()/3));
		map_height = (int) screenSize.getHeight();
		panel_width = (int) (screenSize.getWidth()) - map_width;
		panel_height = map_height;
		map = new MapModel(this);
		inventory = new InventoryModel();
		
		Point start_coord = map.GetCityOriginCoord();
		
		inventory.add(Resource.Gold, 150);
		
		workers = new ArrayList<WorkerModel>(10);
		workers.add(new WorkerModel(this, WorkerRole.Miner, start_coord));
		workers.add(new WorkerModel(this, WorkerRole.Lumberjack, start_coord));
		workers.add(new WorkerModel(this, WorkerRole.QuarryWorker, start_coord));
		
		buildings = new ArrayList<BuildingModel>();
		buildings.add(new BuildingModel(this, map.GetCityOriginCoord(), BuildingId.CityHall));
		
		Point shop_coord = map.GetCityCells().get(1).GetCoord();
		buildings.add(new BuildingModel(this, shop_coord, BuildingId.Shop));
		
		ennemies = new ArrayList<EnnemyModel>();
		
		goals = new GoalsModel(this);
	}
	
	/**
	 * Getter for the width of the map
	 * @return this.map_width
	 */
	public int GetMapWidth() {
		return map_width;
	}
	
	/**
	 * Getter for the Height of the map
	 * @return this.map_height
	 */
	public int GetMapHeight() {
		return map_height;
	}
	
	/**
	 * Getter for the width of the panel
	 * @return this.panel_width
	 */
	public int GetPanelWidth() {
		return panel_width;
	}
	
	/**
	 * Getter for the height of the panel
	 * @return this.panel_height
	 */
	public int GetPanelheight() {
		return panel_height;
	}
	
	/**
	 * Getter for the map Model
	 * @return this.map
	 */
	public MapModel GetMapModel() {
		return map;
	}
	
	/**
	 * Getter for the inventory of the player
	 * @return this.inventory
	 */
	public InventoryModel getInventoryModel() {
		return inventory;
	}
	
	/**
	 * Getter for the Workers
	 * return an ArrayList of all the worker
	 * @return this.workers
	 */
	public ArrayList<WorkerModel> GetWorkerModel() {
		return workers;
	}
	
	/**
	 * Getter for the buildings
	 * return an arrayList of all the building
	 * @return this.building
	 */
	public ArrayList<BuildingModel> GetBuildingList(){
		return buildings;
	}
	
	/**
	 * Getter for the timeUnit
	 * @return this.time_unit
	 */
	public int TimeUnit() {
		return time_unit;
	}
	
	/**
	 * The PlayerHasEnoughToBuild function return a boolean if the player have enough Ressources to build 
	 * The building he want
	 * @param bid ID of the building the player want to build
	 * @return if we can build 
	 */
	public boolean PlayerHasEnoughToBuild(BuildingId bid) {
		int wood = inventory.getAmount(Resource.Wood);
		int stone = inventory.getAmount(Resource.Stone);
		int iron = inventory.getAmount(Resource.Iron);
		int gold = inventory.getAmount(Resource.Gold);
		boolean cond;
		switch (bid) {
			case SawMill:
				cond = (wood>=200) && (stone>=120) && (iron>=50) && (gold>=30);
				return cond;
				
			case Mine:
				cond = (wood>=120) && (stone>=200) && (iron>=50) && (gold>=30);
				break;
				
			case Quarry:
				cond = (wood>=150) && (stone>=150) && (iron>=120) && (gold>=30);
				break;
				
			case LumberCamp:
				cond = (wood>=300) && (stone>=200) && (iron>=80) && (gold>=50);
				break;
				
			case MinerCamp:
				cond = (wood>=200) && (stone>=300) && (iron>=80) && (gold>=50);
				break;
				
			case QuarryWorkerCamp:
				cond = (wood>=200) && (stone>=200) && (iron>=150) && (gold>=50);
				break;
				
			default:
				return false;
		}
		return cond;
	}
	
	/**
	 * AddBuilding Method 
	 * Add a new building to the list
	 * @param bid the ID of the Building we add
	 * @param pts The position where it's build
	 */
	public void AddBuilding(BuildingId bid, Point pts) {
		buildings.add(new BuildingModel(this,pts,bid));
	}
	
	/**
	 * Harvest Method
	 * Make a worker harvest the ressources on a Cell
	 * The quantity harvested depend 
	 * on the size of the Inventory of the player
	 * on the quantity of ressources on the cell
	 * @param worker worker that want to harvest
	 * @param cell the cell were we harvest
	 */
	public void Harvest(WorkerModel worker, CellModel cell) {
		InventoryModel cell_inventory = cell.getInventory();
		InventoryModel worker_inventory = worker.getInventory();
		
		if(worker_inventory.getAmount(cell.getResource()) < worker_inventory.GetMaxAmount()
				&& cell_inventory.getAmount(cell.getResource()) > 0) {
			int available = cell_inventory.getAmount(cell.getResource());
			if(available >= worker.GetHarvestCapacity()) {
				worker.harvest(cell.getResource());
				cell.collectResource(worker.GetHarvestCapacity());
				goals.IncrementGoal(Goals.CollectResources, 3);
			} else {
				int amount = cell.getResourceAmount();
				worker.harvest(cell.getResource(),cell.getResourceAmount());
				cell.collectResource(cell.getResourceAmount());
				if(amount<30) {
					goals.IncrementGoal(Goals.CollectResources);
				} else {
					goals.IncrementGoal(Goals.CollectResources, Math.round(amount/30));
				}
			}
		}
	}
	
	/**
	 * The BuildOnResourceCell Method creat a building on a cell
	 * But withdraw ressources needed for the construction
	 * @param cell the cell where we build
	 */
	public void BuildOnResourceCell(CellModel cell) {
		BuildingId bid;
    	CellId cid = cell.GetId();
    	switch (cid) {
    		case Mountain:
    			bid = BuildingId.Mine;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    				inventory.remove(Resource.Wood, 200);
    				inventory.remove(Resource.Stone, 120);
    				inventory.remove(Resource.Iron, 50);
    				inventory.remove(Resource.Gold, 30);
    			}
    			break;
    		case Forest:
    			bid = BuildingId.SawMill;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    				inventory.remove(Resource.Wood, 120);
    				inventory.remove(Resource.Stone, 200);
    				inventory.remove(Resource.Iron, 50);
    				inventory.remove(Resource.Gold, 30);
    			}
    			break;
    		case Iron_Deposit:
    			bid = BuildingId.Quarry;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    				inventory.remove(Resource.Wood, 150);
    				inventory.remove(Resource.Stone, 150);
    				inventory.remove(Resource.Iron, 120);
    				inventory.remove(Resource.Gold, 30);
    			}
    			break;
    		default:
    			break;
    	}
	}
	
	/**
	 * The BuildOnCityCell creat a build on a Cell
	 * @param bid ID of the building we are trying to build
	 * @param cell The cell where we want to build
	 */
	public void BuildOnCityCell(BuildingId bid, CellModel cell) {
		AddBuilding(bid,cell.GetCoord());
	}
	
	/**
	 * The PlayerCanUpgradeWorker Function 
	 * return if the player have enough gold to upgrade a worker
	 * @param worker worker that we want to upgrade
	 * @return if we can upgrade the worker
	 */
	public boolean PlayerCanUpgradeWorker(WorkerModel worker) {
		int gold = inventory.getAmount(Resource.Gold);
		
		switch (worker.GetLevel()){
			case 1 :
				return gold>=50;
			case 2 :
				return gold>=100;
			case 3 :
				return gold>=175;
			case 4 :
				return gold>=250;
			default :
				return false;
		}
	}
	
	/**
	 * the WorkerDropsInventory Method 
	 * Make all the ressource from a worker a global Ressources
	 * (It move the Ressources from the workerInventory to the PlayerInventory)
	 * @param worker
	 */
	public void WorkerDropsInventory(WorkerModel worker) {
		Resource res_type;
		int max_amount = worker.getInventory().GetMaxAmount();
		int amount;
		
		switch (worker.GetRole()) {
			case Worker:
				int wood_ = inventory.getAmount(Resource.Wood);
				int stone_ = inventory.getAmount(Resource.Stone);
				int iron_ = inventory.getAmount(Resource.Iron);
				int wood = worker.getInventory().getAmount(Resource.Wood);
				int stone = worker.getInventory().getAmount(Resource.Stone);
				int iron = worker.getInventory().getAmount(Resource.Iron);
				
				worker.dropOff(Resource.Wood, wood);
				inventory.add(Resource.Wood, wood);
				worker.dropOff(Resource.Stone, stone);
				inventory.add(Resource.Stone, stone);
				worker.dropOff(Resource.Iron, iron);
				inventory.add(Resource.Iron, iron);
				
				wood_ = inventory.getAmount(Resource.Wood);
				stone_ = inventory.getAmount(Resource.Stone);
				iron_ = inventory.getAmount(Resource.Iron);
				wood = worker.getInventory().getAmount(Resource.Wood);
				stone = worker.getInventory().getAmount(Resource.Stone);
				iron = worker.getInventory().getAmount(Resource.Iron);
				break;
				
			case Lumberjack:
				res_type = Resource.Wood;
				amount = worker.getInventory().getAmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			case Miner:
				res_type = Resource.Stone;
				amount = worker.getInventory().getAmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			case QuarryWorker:
				res_type = Resource.Iron;
				amount = worker.getInventory().getAmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * The Collect Method
	 * Let a worker collect as much as possible of the ressource on a building
	 * @param worker worker that collect
	 * @param building building that is collected
	 */
	public void Collect(WorkerModel worker, BuildingModel building) {
		InventoryModel building_inventory = building.GetInventory();
		
		Resource res_type = building.GetProducedResource();
		int available = building_inventory.getAmount(res_type);
		int amount;
		if(worker.GetHarvestCapacity()>=available) {
			amount = available;
			worker.harvest(res_type, amount);
		} else {
			amount = worker.GetHarvestCapacity();
			worker.harvest(res_type);
		}
		
		building_inventory.remove(res_type, amount);
		return;
	}
	
	/**
	 * The PlayerCanUpgradeBuilding Function 
	 * return if the player have enough ressources to upgrade a building
	 * @param building the building we want to upgrade
	 * @return if we can upgrade it
	 */
	public boolean PlayerCanUpgradeBuilding(BuildingModel building) {
		int wood = inventory.getAmount(Resource.Wood);
		int stone = inventory.getAmount(Resource.Stone);
		int iron = inventory.getAmount(Resource.Iron);
		int gold = inventory.getAmount(Resource.Gold);
		
		int needed_wood;
		int needed_stone;
		int needed_iron;
		int needed_gold;
		
		BuildingId bid = building.getId();
		int level = building.GetLevel();
		
		if(bid==BuildingId.Mine || bid==BuildingId.SawMill || bid==BuildingId.Quarry) {
			needed_wood = level*150;
			needed_stone = level*125;
			needed_iron = level*100;
			needed_gold = level*75;
			
		} else if(bid==BuildingId.LumberCamp || bid==BuildingId.MinerCamp || bid==BuildingId.QuarryWorkerCamp) {
			needed_wood = level*100;
			needed_stone = level*100;
			needed_iron = level*75;
			needed_gold = level*150;
			
		} else {
			//shop & cityhall 
			needed_wood = level*250;
			needed_stone = level*225;
			needed_iron = level*200;
			needed_gold = level*200;
		}
		
		return (wood>=needed_wood) && (stone>=needed_stone) && (iron>=needed_iron) && (gold>=needed_gold);
	}
	
	/**
	 * The GetRoleFromBuilding Function 
	 * Return the associated WorkerRole of the building
	 * @param bid ID of the building
	 * @return
	 */
	public WorkerRole GetRoleFromBuilding(BuildingId bid) {
		switch (bid) {
			case Barrack:
				return WorkerRole.Knight;
			case LumberCamp:
				return WorkerRole.Lumberjack;
			case MinerCamp:
				return WorkerRole.Miner;
			case QuarryWorkerCamp:
				return WorkerRole.QuarryWorker;
			default:
				return null;
		}
	}
	
	/**
	 * The CanCreateNewWorker Function
	 * Return true if there is enough building to create a new worker
	 * @param building
	 * @return
	 */
	public boolean CanCreateNewWorker(BuildingModel building) {
		int limit = 1;
		int current = 0;
		WorkerRole role = GetRoleFromBuilding(building.getId());
		for(BuildingModel b : buildings) {
			if(b.getId()==building.getId()) {
				switch (building.GetLevel()) {
					case 1:
						limit+=2;
						break;
					case 2:
						limit+=4;
						break;
					case 3:
						limit+=6;
						break;
						
					default:
						break;
				}
			}
		}
		
		for(WorkerModel worker : workers) {
			if(worker.GetRole()==role) {
				current++;
			}
		}
		return (current<limit);
	}
	
	/**
	 * The AddWorker Method 
	 * Add a worker on the workers List
	 * @param wr role of the new worker
	 * @param pos Position on the map of the new worker
	 */
	public void AddWorker(WorkerRole wr, Point pos) {
		workers.add(new WorkerModel(this,wr,pos));
	}
	
	/**
	 * The PlayerCanTrain Function
	 * Return true if the player have enough gold to produce a new worker 
	 * with role associated to the building
	 * LumberCamp -> 75
	 * MinerCamp -> 75
	 * QuarryWorkerCamp -> 100
	 * Barrack -> 150
	 * @param building
	 * @return
	 */
	public boolean PlayerCanTrain(BuildingModel building) {
		int gold = inventory.getAmount(Resource.Gold);
		switch (building.getId()) {
			case LumberCamp:
				return gold>=75;
				
			case MinerCamp:
				return gold>=75;
				
			case QuarryWorkerCamp:
				return gold>=100;
				
			case Barrack:
				return gold>=150;
			
			default:
				return false;
		}
	}
	
	/**
	 * The PlayerHasEnoughToExpand Function
	 * 
	 * return true if the player have enough wood and stone stone to expand the city
	 * 			   else false
	 * @param cell The cell where we want to expand the city
	 * @return
	 */
	public boolean PlayerHasEnoughToExpand(CellModel cell) {
		int wood = inventory.getAmount(Resource.Wood);
		int stone = inventory.getAmount(Resource.Stone);
		System.out.println("player has "+wood+" woods & "+stone+" stones");
		switch (cell.GetId()) {
			case Plain:
				return (wood>=50) && (stone>=50);
			case Forest:
				return (wood>=100) && (stone>=100);
				
			case Mountain:
				return (wood>=150) && (stone>=150);
				
			case Iron_Deposit:
				return (wood>=175) && (stone>=175);
				
			default:
				return false;
		}
	}
	
	/**
	 * The Expand Method 
	 * Remove all The ressources from the selected Cell
	 * Then turn the Cell type as a city
	 * @param coord coordonate of the cell we transform
	 */
	public void Expand(Point coord) {
		CellModel cell = map.GetCellFromCoord(coord.x, coord.y);
		switch (cell.GetId()) {
			case Plain:
				inventory.remove(Resource.Wood, 50);
				inventory.remove(Resource.Stone, 50);
				break;
				
			case Forest:
				inventory.remove(Resource.Wood, 100);
				inventory.remove(Resource.Stone, 100);
				break;
			
			case Mountain:
				inventory.remove(Resource.Wood, 150);
				inventory.remove(Resource.Stone, 150);
				break;
			
			case Iron_Deposit:
				inventory.remove(Resource.Wood, 175);
				inventory.remove(Resource.Stone, 175);
				break;
			
			default:
				break;
		}
		cell.TurnToCity();
	}
	
	/**
	 * The AddEnnemy Method
	 * add a new ennemy in the ennemies List
	 * @param cell Cell where the ennemy will spawn
	 * @param role Role (type) of the ennemy
	 */
	public void AddEnnemy(CellModel cell, EnnemyRole role) {
		ennemies.add(new EnnemyModel(this, role, cell.GetCoord()));
	}
	
	/**
	 * Getter for the ennemies
	 * @return an ArrayList of all the ennemy currently in the game
	 */
	public ArrayList<EnnemyModel> GetEnnemiesList(){
		return ennemies;
	}
	
	/**
	 * The EnnemyAttacksWorker Method
	 * This method decrease the HP of the worker if he's not knight
	 * if the worker is a knight then it's the ennemy that loose some HP
	 * @param ennemy the ennemy that attack
	 * @param worker the worker that is attacked
	 */
	public void EnnemyAttacksWorker(EnnemyModel ennemy, WorkerModel worker) {
		worker.TakeDamage();
		if(worker.GetRole()==WorkerRole.Knight) {
			//if the worker is a knight then it will fight back
			ennemy.TakeDamage();
		}
	}
	
	/**
	 * The removeWorker Method
	 * Remove a worker from the workers List in the game
	 * @param worker worker that is removed
	 */
	public void RemoveWorker(WorkerModel worker) {
		for(int i=0; i<workers.size(); i++) {
			WorkerModel wm = workers.get(i);
			if(wm==worker) {
				workers.remove(i);
			}
		}
	}
	
	/**
	 * The RemoveEnnemy Method
	 * Remove an ennemy from the ennemies List in the game
	 * @param ennemy ennemy that is removed
	 */
	public void RemoveEnnemy(EnnemyModel ennemy) {
		for(int i=0; i<ennemies.size(); i++) {
			EnnemyModel em = ennemies.get(i);
			if(em==ennemy) {
				ennemies.remove(i);
			}
		}
	}
	
	/**
	 * Getter for the goals
	 * @return this.goals
	 */
	public GoalsModel GetGoals() {
		return goals;
	}
}
