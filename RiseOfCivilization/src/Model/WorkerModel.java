package Model;

import java.awt.*;
import Types.*;
import Types.WorkerRole;

/**
 * This class is responsible for all information and action of a worker
 */
public class WorkerModel {
	// The main Model (Model of the game)
	private GameModel model;
	// Model of the map of the game
	private MapModel map_model;
	// Model of the cell where the player is currently on
	private CellModel cell;
	// Position of the next cell if the player is mooving ? (Probably useless and not use)
	private Point next_coord;
	// Coordonate of the player on the screen
	private Point coordOnScreen;
	// Width of the circle that represent the player on the screen
	private static final int width = 10;
	// Height of the circle that represent the player on the screen
	private static final int height = 10;
	// Inventory of the worker
	private InventoryModel inventory;
	// Boolean that indicate if the player is currently mooving
	private boolean moving;
	// Boolean that indicate if the player is currently doing a task
	private boolean occupied;
	// Role of the player (indicate if he is a knight or a worker)
	private WorkerRole role;
	// Int that represent how much the worker can harvest
	private int harvest_capacity;
	// Int that represent the level of the worker
	private int level;
	// Int that represent the Health of the player at it's creation
	private int init_health_bar;
	// Current HP level of the worker
	private int current_health_bar;
	
	/**
	 * Constructor for a worker taking a GameModel, a workerRole and a position
	 * 
	 * Initialized harvest capacity to 100
	 * Initialized HP to 1 or 2 depend on if the worker is a knight or not
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
		
		if(role==WorkerRole.Knight) {
			init_health_bar = 2;
		} else {
			init_health_bar = 1;
		}
		current_health_bar = init_health_bar;
	}
	
	/**
	 * Getter for the current HP level of the player
	 * 
	 * @return this.current_health_bar
	 */
	public int GetCurrentHealth() {
		return current_health_bar;
	}
	
	/**
	 * Getter for the initial HP level of the player
	 * 
	 * @return this.init_health_bar
	 */
	public int GetInitHealth() {
		return init_health_bar;
	}
	
	/**
	 * The recovery Method increase the life of a worker by one
	 */
	public void Recovery() {
		current_health_bar++;
	}
	
	/**
	 * The TakeDamage Method decrease the life of a worker by one
	 */
	public void TakeDamage() {
		current_health_bar--;
	}
	
	/**
	 * IsDead function return if the player have no more life point
	 * @return if the player still have HP
	 */
	public boolean IsDead() {
		return current_health_bar==0;
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
	 * The MoveTo Method change the cell where the player is, to the cell that have have the (i, j) coordonate on the map
	 * 
	 * @param i X position on the map for the new position
	 * @param j Y position on the map for the new position
	 */
	public void MoveTo(int i, int j) {
		this.cell = this.model.GetMapModel().GetCellFromCoord(i, j);
	}
	
	/**
	 * 
	 */
	public void setPosWhileMoving(int i, int j)
	{
		this.coordOnScreen = new Point(i,j);
	}
	
	public Point getPosWhileMoving()
	{
		return this.coordOnScreen;
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
	 * The harvest Method add in the inventory of the worker n ressources r (n the harvest_capacity, r the ressource)
	 * @param r
	 */
	public void harvest(Resource r) {
		inventory.add(r, harvest_capacity);
	}

	/**
	 * The harvest Method add in the inventory of the worker n ressources r (n an int, r the ressource)
	 * @param r
	 * @param n
	 */
	public void harvest(Resource r, int n) {
		inventory.add(r, n);
	}
	
	/**
	 * The DropOff method remove from the inventory of the worker the ressources r by the ammount
	 * @param r
	 * @param ammount
	 */
	public void dropOff(Resource r, int ammount) {
		inventory.remove(r, ammount);
	}
	
	/**
	 *  Getter for the HarvestCapacity
	 * @return this.harvest_capacity
	 */
	public int GetHarvestCapacity() {
		return harvest_capacity;
	}

	/**
	 *  Getter for the worker inventory
	 * @return this.inventory
	 */
	public InventoryModel getInventory() {
		return inventory;
	}

	/**
	 *  Getter for the level of the worker
	 * @return this.level
	 */
	public int GetLevel() {
		return level;
	}
	
	/**
	 * The LevelUp Method increase the level of the worker and increase the harvest_capacity 
	 */
	public void LevelUp() {
		level += 1;
		switch(level) {
			case 2:
				harvest_capacity+=100;
				inventory.IncreaseMaxAmount(200);
				break;
			case 3:
				harvest_capacity+=80;
				inventory.IncreaseMaxAmount(200);
				break;
			case 4:
				harvest_capacity+=60;
				inventory.IncreaseMaxAmount(150);
				break;
			case 5:
				harvest_capacity+=60;
				inventory.IncreaseMaxAmount(150);
				break;
			default:
				break;
		}
	}
	

	/**
	 * The InventoryIsFull Function return if the inventory of the player have reach it's limit
	 * @return
	 */
	public boolean InventoryIsFull() {
		Resource res_type;
		switch (role) {
			case Worker:
				int wood = getInventory().getAmount(Resource.Wood);
				int stone = getInventory().getAmount(Resource.Stone);
				int iron = getInventory().getAmount(Resource.Iron);
				int max = inventory.GetMaxAmount();
				return (wood==max) || (stone==max) || (iron==max);
				
			case Miner:
				res_type = Resource.Stone;
				break;
				
			case Lumberjack:
				res_type = Resource.Wood;
				break;
				
			case QuarryWorker:
				res_type = Resource.Iron;
				break;
				
			default:
				res_type = null;
				break;
		}
		
		int res_amount = inventory.getAmount(res_type);
		return (res_amount==inventory.GetMaxAmount());
	}


	/**
	 *  Getter for the Role of the worker
	 * @return this.role
	 */
	public WorkerRole GetRole() {
		return this.role;
	}
}
