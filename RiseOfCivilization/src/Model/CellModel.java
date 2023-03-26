package Model;

import Types.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

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
	public static final int MAX_RESOURCE = 500;
	private InventoryModel inventory;
	private Color color;
	
	
	public CellModel(int x, int y, CellId i) {
		X = x;
		Y = y;
		id = i;
		
		inventory = new InventoryModel(200);
		int amount;
		switch (id) {
			case Forest :
				natural_resource = Resource.Wood;
				color = new Color(0, 153, 0);
				amount = ThreadLocalRandom.current().nextInt(10,100);
				inventory.add(natural_resource, amount);
				break;
			case Mountain :
				natural_resource = Resource.Stone;
				color = new Color(150, 150, 150);
				amount = ThreadLocalRandom.current().nextInt(0,40);
				inventory.add(natural_resource, amount);
				break;
			case Iron_Deposit :
				natural_resource = Resource.Iron;
				color = new Color(169,84,69);
				amount = ThreadLocalRandom.current().nextInt(10,100);
				inventory.add(natural_resource, amount);
				break;
			case City:
				color = new Color(165,110,20);
			case Plain:
				color = new Color(225,205,102);
			default:
				break;
		}
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
		if(id!=CellId.Plain) {
			inventory.remove(natural_resource, MAX_RESOURCE);
		}
		id = null;
		id = CellId.City;
	}
	
	public Color GetColor() {
		return color;
	}
	
	public void SetColor() {
		//must implement
		return;
	}
	
	public void collectResource(int n) {
		inventory.remove(natural_resource, n);
	}
}
