package Model;

import java.util.ArrayList;
import Types.*;
import Types.BuildingId;
import Types.Resource;
import Types.WorkerRole;
import java.awt.*;

public class GameModel {
	private static final int time_unit = 60;
	private static int map_width;
	private static int map_height;
	private static final int panel_width = 250;
	private static final int panel_height = 650;
	
	private MapModel map;
	private InventoryModel inventory;
	private ArrayList<WorkerModel> workers;
	private ArrayList<BuildingModel> buildings;
	private ArrayList<EnnemyModel> ennemies;
	
	public int worker_amount;

	public GameModel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		map_width = (int) screenSize.getWidth() - panel_width;
		map_height = (int) screenSize.getHeight();
		
		map = new MapModel(this);
		inventory = new InventoryModel();
		
		worker_amount = 0;
		
		Point start_coord = map.GetCityOriginCoord();
		
		inventory.add(Resource.Gold, 150);
		
		workers = new ArrayList<WorkerModel>(10);
		workers.add(new WorkerModel(this, WorkerRole.Miner, start_coord));
		workers.add(new WorkerModel(this, WorkerRole.LumberJack, start_coord));
		workers.add(new WorkerModel(this, WorkerRole.QuarryMan, start_coord));
		
		buildings = new ArrayList<BuildingModel>();
		buildings.add(new BuildingModel(this, map.GetCityOriginCoord(), BuildingId.CityHall));
		
		Point shop_coord = map.GetCityCells().get(1).GetCoord();
		buildings.add(new BuildingModel(this, shop_coord, BuildingId.Shop));
		
		ennemies = new ArrayList<EnnemyModel>();
	}
	
	public int GetMapWidth() {
		return map_width;
	}
	
	public int GetMapHeight() {
		return map_height;
	}
	
	public int GetPanelWidth() {
		return panel_width;
	}
	
	public int GetPanelheight() {
		return panel_height;
	}
	
	public MapModel GetMapModel() {
		return map;
	}
	
	public InventoryModel getInventoryModel() {
		return inventory;
	}
	
	public ArrayList<WorkerModel> GetWorkerModel() {
		return workers;
	}
	
	public ArrayList<BuildingModel> GetBuildingList(){
		return buildings;
	}
	
	public int TimeUnit() {
		return time_unit;
	}
	
	public boolean PlayerHasEnoughToBuild(BuildingId bid) {
		int wood = inventory.getAmmount(Resource.Wood);
		int stone = inventory.getAmmount(Resource.Stone);
		int iron = inventory.getAmmount(Resource.Iron);
		int gold = inventory.getAmmount(Resource.Gold);
		boolean cond;
		switch (bid) {
			case SawMill:
				cond = (wood>=200) && (stone>=120) && (iron>=50) && (gold>=30);
				System.out.println("enough to build SawMill : " + cond); 
				return cond;
				
			case Mine:
				cond = (wood>=120) && (stone>=200) && (iron>=50) && (gold>=30);
				System.out.println("enough to build Mine : " + cond);
				break;
				
			case Quarry:
				cond = (wood>=150) && (stone>=150) && (iron>=120) && (gold>=30);
				System.out.println("enough to build Quarry : " + cond); 
				break;
				
			case LumberCamp:
				cond = (wood>=300) && (stone>=200) && (iron>=80) && (gold>=50);
				System.out.println("enough to build LumberCamp : " + cond); 
				break;
				
			case MinerCamp:
				cond = (wood>=200) && (stone>=300) && (iron>=80) && (gold>=50);
				System.out.println("enough to build MinerCamp : " + cond); 
				break;
				
			case QuarrymanCamp:
				cond = (wood>=200) && (stone>=200) && (iron>=150) && (gold>=50);
				System.out.println("enough to build QuarrymanCamp : " + cond); 
				break;
				
			default:
				return false;
		}
		return cond;
	}
	
	public void AddBuilding(BuildingId bid, Point pts) {
		buildings.add(new BuildingModel(this,pts,bid));
	}
	
	public void Harvest(WorkerModel worker, CellModel cell) {
		InventoryModel cell_inventory = cell.getInventory();
		InventoryModel worker_inventory = worker.getInventory();
		
		if(worker_inventory.getAmmount(cell.getResource()) < worker_inventory.GetMaxAmount()
				&& cell_inventory.getAmmount(cell.getResource()) > 0) {
			int available = cell_inventory.getAmmount(cell.getResource());
			if(available >= worker.GetHarvestCapacity()) {
				worker.harvest(cell.getResource());
				cell.collectResource(worker.GetHarvestCapacity());
				System.out.println("worker harvested "+worker.GetHarvestCapacity()+" on the "+available+" available resources");
			} else {
				int amount = cell.getResourceAmount();
				worker.harvest(cell.getResource(),cell.getResourceAmount());
				cell.collectResource(cell.getResourceAmount());
				System.out.println("worker harvested "+amount+" the total amount of cell's resources");
			}
		}
	}
	
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
	
	public void BuildOnCityCell(BuildingId bid, CellModel cell) {
		AddBuilding(bid,cell.GetCoord());
	}
	
	public boolean PlayerCanUpgradeWorker(WorkerModel worker) {
		int gold = inventory.getAmmount(Resource.Gold);
		
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
	
	public void WorkerDropsInventory(WorkerModel worker) {
		Resource res_type;
		int max_amount = worker.getInventory().GetMaxAmount();
		int amount;
		
		switch (worker.GetRole()) {
			case Worker:
				int wood_ = inventory.getAmmount(Resource.Wood);
				int stone_ = inventory.getAmmount(Resource.Stone);
				int iron_ = inventory.getAmmount(Resource.Iron);
				System.out.println("inventory has : wood("+wood_+"), stone("+stone_+"), iron("+iron_+")");
				int wood = worker.getInventory().getAmmount(Resource.Wood);
				int stone = worker.getInventory().getAmmount(Resource.Stone);
				int iron = worker.getInventory().getAmmount(Resource.Iron);
				
				System.out.println("workers has : wood("+wood+"), stone("+stone+"), iron("+iron+")");
				worker.dropOff(Resource.Wood, wood);
				inventory.add(Resource.Wood, wood);
				worker.dropOff(Resource.Stone, stone);
				inventory.add(Resource.Stone, stone);
				worker.dropOff(Resource.Iron, iron);
				inventory.add(Resource.Iron, iron);
				
				wood_ = inventory.getAmmount(Resource.Wood);
				stone_ = inventory.getAmmount(Resource.Stone);
				iron_ = inventory.getAmmount(Resource.Iron);
				wood = worker.getInventory().getAmmount(Resource.Wood);
				stone = worker.getInventory().getAmmount(Resource.Stone);
				iron = worker.getInventory().getAmmount(Resource.Iron);
				System.out.println("inventory has : wood("+wood_+"), stone("+stone_+"), iron("+iron_+")");
				System.out.println("workers has : wood("+wood+"), stone("+stone+"), iron("+iron+")");
				break;
				
			case LumberJack:
				res_type = Resource.Wood;
				amount = worker.getInventory().getAmmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			case Miner:
				res_type = Resource.Stone;
				amount = worker.getInventory().getAmmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			case QuarryMan:
				res_type = Resource.Iron;
				amount = worker.getInventory().getAmmount(res_type);
				worker.dropOff(res_type, amount);
				inventory.add(res_type, amount);
				break;
				
			default:
				break;
		}
	}
	
	public void Collect(WorkerModel worker, BuildingModel building) {
		InventoryModel building_inventory = building.GetInventory();
		
		Resource res_type = building.GetProducedResource();
		int available = building_inventory.getAmmount(res_type);
		System.out.println("building has "+available+" available resources");
		int amount;
		if(worker.GetHarvestCapacity()>=available) {
			amount = available;
			worker.harvest(res_type, amount);
			System.out.println("worker has collected the "+amount+" of building's resources");
		} else {
			amount = worker.GetHarvestCapacity();
			worker.harvest(res_type);
			System.out.println("worker has collected "+amount+" /200 resources of this building");
		}
		
		building_inventory.remove(res_type, amount);
		return;
	}
	
	public boolean PlayerCanUpgradeBuilding(BuildingModel building) {
		int wood = inventory.getAmmount(Resource.Wood);
		int stone = inventory.getAmmount(Resource.Stone);
		int iron = inventory.getAmmount(Resource.Iron);
		int gold = inventory.getAmmount(Resource.Gold);
		
		int needed_wood;
		int needed_stone;
		int needed_iron;
		int needed_gold;
		
		BuildingId bid = building.GetId();
		int level = building.GetLevel();
		
		if(bid==BuildingId.Mine || bid==BuildingId.SawMill || bid==BuildingId.Quarry) {
			needed_wood = level*150;
			needed_stone = level*125;
			needed_iron = level*100;
			needed_gold = level*75;
			
		} else if(bid==BuildingId.LumberCamp || bid==BuildingId.MinerCamp || bid==BuildingId.QuarrymanCamp) {
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
	
	public WorkerRole GetRoleFromBuilding(BuildingId bid) {
		switch (bid) {
			case Barrack:
				return WorkerRole.Knight;
			case LumberCamp:
				return WorkerRole.LumberJack;
			case MinerCamp:
				return WorkerRole.Miner;
			case QuarrymanCamp:
				return WorkerRole.QuarryMan;
			default:
				return null;
		}
	}
	
	public boolean CanCreateNewWorker(BuildingModel building) {
		int limit = 1;
		int current = 0;
		WorkerRole role = GetRoleFromBuilding(building.GetId());
		for(BuildingModel b : buildings) {
			if(b.GetId()==building.GetId()) {
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
	
	public void AddWorker(WorkerRole wr, Point pos) {
		workers.add(new WorkerModel(this,wr,pos));
	}
	
	public boolean PlayerCanTrain(BuildingModel building) {
		int gold = inventory.getAmmount(Resource.Gold);
		switch (building.GetId()) {
			case LumberCamp:
				return gold>=75;
				
			case MinerCamp:
				return gold>=75;
				
			case QuarrymanCamp:
				return gold>=100;
				
			case Barrack:
				return gold>=150;
			
			default:
				return false;
		}
	}
	
	public boolean PlayerHasEnoughToExpand(CellModel cell) {
		int wood = inventory.getAmmount(Resource.Wood);
		int stone = inventory.getAmmount(Resource.Stone);
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
	
	public void AddEnnemy(CellModel cell, EnnemyRole role) {
		ennemies.add(new EnnemyModel(this, role, cell.GetCoord()));
	}
	
	public ArrayList<EnnemyModel> GetEnnemiesList(){
		return ennemies;
	}
}
