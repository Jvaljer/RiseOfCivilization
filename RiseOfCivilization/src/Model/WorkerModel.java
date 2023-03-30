package Model;

import java.awt.*;
import Types.*;
import Types.WorkerRole;

/**
 * This class is responsible for all information and action of a worker
 */
public class WorkerModel {
	private GameModel model;
	private MapModel map_model;
	private CellModel cell;
	private Point next_coord;
	private static final int width = 10;
	private static final int height = 10;
	private InventoryModel inventory;
	private boolean moving;
	private boolean occupied;
	private WorkerRole role;
	private int harvest_capacity;
	private int level;
	
	/**
	 * Constructor for a worker taking a GameModel, a workerRole and a position
	 * 
	 * @param M The gameModel object that represent the game
	 * @param R The role of a worker that represente what kind of ressource he can collect
	 * @param pts position on the map of the worker
	 */
	public WorkerModel(GameModel M, WorkerRole R, Point pts) {
		model = M;
		map_model = model.GetMapModel();
		cell = map_model.GetCellFromCoord(pts.x,pts.y);
		harvest_capacity = 100;
		inventory = new InventoryModel(harvest_capacity*3);
		moving = false;
		occupied = false;
		role = R;
		level = 1;
		
		model.worker_amount++;
	}

	/**
	 * Getter for the position on the map of the worker
	 * 
	 * @return the Coord of the cell where the worker is
	 */
	public Point getPos()
	{
		return this.cell.GetCoord();
	}
	
	/**
	 * Getter for the X position on the map of the worker
	 * 
	 * @return the X_Coord of the cell where the worker is
	 */
	public int getcoordX()
	{
		return this.cell.GetX();
	}
	
	/**
	 * Getter for the Y position on the map of the worker
	 * 
	 * @return the Y_Coord of the cell where the worker is
	 */
	public int getcoordY()
	{
		return this.cell.GetY();
	}
	
	/**
	 * Getter for the width of the worker
	 * 
	 * @return this.width
	 */
	public int getWidth() {
		return WorkerModel.width;
	}
	
	/**
	 * Getter for the height of the worker
	 * 
	 * @return this.height
	 */
	public int getHeight() {
		return WorkerModel.height;
	}

	/**
	 * Getter for the boolean occupied
	 * 
	 * @return this.occupied
	 */
	public boolean GetOccupied()
	{
		return this.occupied;
	}
	
	/**
	 * The MoveTo function change the cell where the player is, to the cell that have have the (i, j) coordonate on the map
	 * 
	 * @param i X position on the map for the new position
	 * @param j Y position on the map for the new position
	 */
	public void MoveTo(int i, int j) {
		this.cell = this.model.GetMapModel().GetCellFromCoord(i, j);
	}
	
	/**
	 * Setter occcupied, set the occupied boolean to True
	 */
	public void occupied() {
		this.occupied = true;
	}

	/**
	 * Setter moving, set the moving boolean to True
	 */
	public void moving()
	{
		this.moving = true;
	}

	/**
	 * Setter stopMoving, set the moving boolean to False 
	 */		
	public void stopMoving()
	{
		this.moving = false;
	}
	
	/**
	 * Setter Free, set the free boolean to false
	 */
	public void Free() {
		this.occupied = false;
	}
	
	/**
	 * Getter getMoving, return True if the player is moving else false
	 * @return this.moving
	 */
	public boolean GetMoving() {
		return this.moving;
	}

	/**
	 *  Setter setNextcoord, set the next_coord variable that indicate when in movement the next Cell where the player will be
	 * @param p position of the next cell where the player will be
	 */
	public void setNextcoord(Point p)
	{
		this.next_coord = p;
	}

	/**
	 *  Getter getNextcoord, return the position of the next cell where the player will be
	 * @return this.next_coords
	 */
	public Point getNextcoord()
	{
		return this.next_coord;
	}
	
	/**
	 * 
	 * @param r
	 */
	public void harvest(Resource r) {
		inventory.add(r, harvest_capacity);
	}

	/**
	 * 
	 * @param r
	 * @param n
	 */
	public void harvest(Resource r, int n) {
		inventory.add(r, n);
	}
	
	/**
	 * 
	 * @param r
	 * @param ammount
	 */
	public void dropOff(Resource r, int ammount) {
		inventory.remove(r, ammount);
	}
	
	/**
	 * 
	 * @return
	 */
	public int GetHarvestCapacity() {
		return harvest_capacity;
	}

	/**
	 * 
	 * @return
	 */
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
				int wood = getInventory().getAmmount(Resource.Wood);
				int stone = getInventory().getAmmount(Resource.Stone);
				int iron = getInventory().getAmmount(Resource.Iron);
				int max = inventory.GetMaxAmount();
				return (wood==max) || (stone==max) || (iron==max);
				
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

	public WorkerRole GetRole() {
		return this.role;
	}
}
