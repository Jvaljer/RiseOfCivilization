package Model;

import Types.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class is for the model of a cell. A cell has a position and an id
 * corresponding to the type of terrain of the cell. It also has an inventory
 * that holds resources. Each cell that isn't a plain nor a city cell has a
 * specific natural resource that corresponds to its id as following :
 * Wood for Forest, Stone for Mountain & Iron for Iron_Deposit.
 * Each cell has a maximum ammount of resource it can have.
 * 
 * @author Abel
 * @author Martin
 */
public class CellModel {
	private int X;
	private int Y;
	private CellId id;
	private Resource natural_resource;
	public static final int MAX_RESOURCE = 200;
	private InventoryModel inventory;
	private BuildingModel building;
	
	/**
     * This is the constructor of the CellModel. Each resource cell
	 * is created with a random amout of resource between 10 and 100.
     *
     * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
     * @param i the id of the cell
     */
	public CellModel(int x, int y, CellId i) {
		X = x;
		Y = y;
		id = i;
		
		inventory = new InventoryModel(MAX_RESOURCE);
		int starting_amount;
		switch (id) {
			case Forest :
				natural_resource = Resource.Wood;
				starting_amount = ThreadLocalRandom.current().nextInt(10,100);
				inventory.add(natural_resource, starting_amount);
				break;
			case Mountain :
				natural_resource = Resource.Stone;
				starting_amount = ThreadLocalRandom.current().nextInt(0,40);
				inventory.add(natural_resource, starting_amount);
				break;
			case Iron_Deposit :
				natural_resource = Resource.Iron;
				starting_amount = ThreadLocalRandom.current().nextInt(10,100);
				inventory.add(natural_resource, starting_amount);
				break;
			default:
				break;
		}
	}
	
	/**
    * Returns the x coordinate of the cell
    */
	public int GetX() {
		return X;
	}
	
	/**
    * Returns the y coordinate of the cell
    */
	public int GetY() {
		return Y;
	}
	
	/**
    * Returns the coordinates of the cell
	* 
	* @return a new Point containing the coordinates of the cell
    */
	public Point GetCoord() {
		return (new Point(X,Y));
	}
	
	/**
    * Returns the id of the cell
    */
	public CellId GetId() {
		return id;
	}
	
	/**
    * Returns the natural resource that the cell produces
    */
	public Resource getResource() {
		return natural_resource;
	}
	
	/**
    * Returns the inventory of the cell
    */
	public InventoryModel getInventory() {
		return inventory;
	}
	
	/**
    * Returns the amount of resource the cell currently has
    */
	public int getResourceAmount() {
		return inventory.getAmount(natural_resource);
	}
	
	/**
    * Returns the amount of resource the cell currently has
	* 
	* @param other the other cell to compare
	* @return      if other has the same coordinates
    */
	public boolean hasSameCoord(CellModel other) {
		return (X == other.GetX() && Y == other.GetY());
	}
	
	/**
    * Returns the building of the cell or null if the cell has none
    */
	public BuildingModel getBuilding() {
		return building;
	}
	
	/**
    * Sets the building of the cell to the already built building
	* 
	* @param bm the building to be built on the cell
    */
	public void build(BuildingModel bm) {
		building = bm;
	}
	
	/**
    * Returns if the cell has a building
    */
	public boolean hasBuilding() {
		return building != null;
	}
	
	/**
    * Returns if the cell has a resource producing building
	* 
	* @return true if a SawMill, Mine or Quarry has been built on the cell
    */
	public boolean hasProdBuilding() {
		BuildingId id = building.getId();
		return (id == BuildingId.SawMill || id == BuildingId.Mine || id == BuildingId.Quarry);
	}
	
	/**
    * Turns the cell to be a city cell. This methods first removes of all
	* the previous resource it could have had before changing its id and
	* removing its previous natural resource.
	* @param has_building indicates if the cell must keep it's resource type or not
    */
	public void TurnToCity(boolean has_building) {
		if(id != CellId.Plain) {
			inventory.remove(natural_resource, MAX_RESOURCE);
		}
		id = CellId.City;
		if(!has_building) {
			natural_resource = null;
		}
	}
	
	/**
    * Removes resource from the cell's inventoryy
	* 
	* @param n the amount to be removed
    */
	public void collectResource(int n) {
		inventory.remove(natural_resource, n);
	}
}
