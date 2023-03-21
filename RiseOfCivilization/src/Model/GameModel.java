package Model;

import java.util.ArrayList;
import Types.*;
import Types.BuildingId;
import Types.Resource;
import Types.WorkerRole;
import java.awt.*;

public class GameModel {
	private static final int time_unit = 60;
	private static final int map_width = 675;
	private static final int map_height = 650;
	private static final int panel_width = 300;
	private static final int panel_height = 650;
	
	private MapModel map;
	private InventoryModel inventory;
	private ArrayList<WorkerModel> workers;
	private ArrayList<BuildingModel> buildings;

	public GameModel() {
		map = new MapModel(this);
		inventory = new InventoryModel();
		
		workers = new ArrayList<WorkerModel>(10);
		workers.add(new WorkerModel(this,WorkerRole.Worker, new Point(1,1)));
		
		buildings = new ArrayList<BuildingModel>();
		buildings.add(new BuildingModel(this,map.GetCityOriginCoord(),BuildingId.CityHall));
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
		/*
		switch (bid) {
			case SawMill:
				return (wood>=200) && (stone>=120) && (iron>=50);
			case Mine:
				return (wood>=120) && (stone>=200) && (iron>=50);
			case Quarry:
				return (wood>=150) && (stone>=150) && (iron>=120);
			case LumberCamp:
				return (wood>=300) && (stone>=200) && (iron>=80);
			case MinerCamp:
				return (wood>=200) && (stone>=300) && (iron>=80);
			case QuarrymanCamp:
				return (wood>=200) && (stone>=200) && (iron>=150);
			default:
				return false;
		}*/
		return true;
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
			} else {
				worker.harvest(cell.getResource(),cell.getResourceAmount());
				cell.collectResource(cell.getResourceAmount());
			}
		}
	}
	
	public void Build(CellModel cell) {
		BuildingId bid;
    	CellId cid = cell.GetId();
    	switch (cid) {
    		case Mountain:
    			System.out.println("building a Mine");
    			bid = BuildingId.Mine;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    			}
    			break;
    		case Forest:
    			System.out.println("building a SawMill");
    			bid = BuildingId.SawMill;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    			}
    			break;
    		case Iron_Deposit:
    			System.out.println("building a Quarry");
    			bid = BuildingId.Quarry;
    			if(PlayerHasEnoughToBuild(bid)) {
    				AddBuilding(bid,cell.GetCoord());
    			}
    			break;
    		case City:
    			System.out.println("must choose MinerCamp || Barrack || QuarrymanCamp || LumberCamp");
    			break;
    		default:
    			break;
    	}
	}
}
