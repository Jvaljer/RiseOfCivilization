package Model;

import Types.*;
import java.awt.*;

/**
 * This class is for the model of a cell. A cell has a position and an id
 * corresponding to the type of terrain of the cell. It also has an inventory
 * that holds resources. Each cell that isn't a city cell has a specific
 * natural resource that corresponds to its id as following :
 * Wheat for Plains, Wood for Forests, Stone for Mountains & Iron for Veins.
 * Each cell has a maximum ammount of resource it can have.
 * 
 * @author abel
 * @author martin
 */
public class CellModel {
	private int X;
	private int Y;
	private CellId id;
	private Resource natural_resource;
	public static final int MAX_RESOURCE = 100;
	private InventoryModel inventory;
	//private BuildingModel building;
	
	
	public CellModel(int x, int y, CellId i) {
		X = x;
		Y = y;
		id = i;
		
		if(id == CellId.Forest) {
			natural_resource = Resource.Wood;
		} else if(id == CellId.Mountain) {
			natural_resource = Resource.Stone;
		} else if(id == CellId.Iron_Deposit) {
			natural_resource = Resource.Iron;
		} else {
			natural_resource = Resource.None;
		}
		
		inventory = new InventoryModel();
		inventory.add(natural_resource, MAX_RESOURCE);
	}
	
	public int GetX() {
		return X;
	}
	
	public int GetY() {
		return Y;
	}
	
	public Point GetCoord() {
		return (new Point(X,Y));
	}
	
	public CellId GetId() {
		return id;
	}
	
	public Resource getResource() {
		return natural_resource;
	}
	
	public InventoryModel getInventory() {
		return inventory;
	}
	
	public int getResourceAmount() {
		return inventory.getAmmount(natural_resource);
	}
	
	public void TurnToCity() {
		id = CellId.City;
		inventory.remove(natural_resource, MAX_RESOURCE);
	}
	
}