package Model;

import Types.CellId;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import Types.*;

/**
 * Model for the overall map, containing all the grid informations, and the needed
 * methods to manipulate it as we want. As the lines & columns amount, or even the 
 * directions & positions.
 * @author abel
 */
public class MapModel {
	// The main Model of the Game it gather all the information about the current game
	private GameModel game;

	// Constante about the map
	private static int width;
	private static int height;
	private static int lines;
	private static int columns;
	private static final int Cell_size = 20;
	private static final Point OriginPoint = new Point(2*Cell_size-10,2*Cell_size-5);

	// Double  ArrayList of CellModel that compose the map
	private CellModel[][] grid;
	// Position on the map of the cityHall 
	private Point city_origin;
	// ArrayList of cell where we have some cityCell
	private ArrayList<CellModel> city_cells;
	// ArrayList of cell where ennemy can spawn
	private ArrayList<CellModel> spawner_cells;

	private CellModel current_cell;
	
	/**
	 * Constructor for the mapModel
	 * Initialised the position of the cityHall and all the cityCell
	 * Also Initialised the spawner for the ennemy
	 * @param M The gameModel
	 */
	public MapModel(GameModel M) {
		game = M;
		width = game.GetMapWidth();
		height = game.GetMapHeight();
		lines = (width) / (Cell_size + Cell_size/2) - 3;
		columns = (height - 10) / ((int) (Math.sqrt(3.) * Cell_size)) - 2;
		//must add calcul of starting point, and all percentages for Cell types
		grid = new CellModel[lines][columns];
		for(int j=0; j<columns; j++) {
			for(int i=0; i<lines; i++) {
				//must add the cell's Id random association
				CellId id;
				int rand_id = ThreadLocalRandom.current().nextInt(0,24);
				if(rand_id == 0) {
					id = CellId.Iron_Deposit;
				} else if(1 <= rand_id && rand_id < 5) {
					id = CellId.Mountain;
				} else if(5 <= rand_id && rand_id < 12) {
					id = CellId.Forest;
				} else if(12 <= rand_id) {
					id = CellId.Plain;
				} else {
					id = null;
				}
				grid[i][j] = new CellModel(i,j,id);
			}
		}
		city_cells = new ArrayList<CellModel>();
		int city_origin_x = ThreadLocalRandom.current().nextInt(1, 20);
		int city_origin_y = ThreadLocalRandom.current().nextInt(1, 17);
		city_origin = new Point(city_origin_x, city_origin_y);
		CellModel city_origin = grid[city_origin_x][city_origin_y];
		city_origin.TurnToCity();
		current_cell = city_origin;
		city_cells.add(city_origin);
		
		ArrayList<Point> city_neighbours = GetNeighbours(city_origin_x, city_origin_y);
		int random_id_1 = ThreadLocalRandom.current().nextInt(0, city_neighbours.size());
		Point p1 = city_neighbours.get(random_id_1);
		CellModel city_cell_1 = grid[p1.x][p1.y];
		city_cell_1.TurnToCity();
		city_cells.add(city_cell_1);
		city_neighbours.remove(p1);
		
		int random_id_2 = ThreadLocalRandom.current().nextInt(0, city_neighbours.size());
		Point p2 = city_neighbours.get(random_id_2);
		CellModel city_cell_2 = grid[p2.x][p2.y];
		city_cell_2.TurnToCity();
		city_cells.add(city_cell_2);
		
		spawner_cells = new ArrayList<CellModel>();
		int rand_x;
		int rand_y;
		do {
			rand_x = ThreadLocalRandom.current().nextInt(0, lines);
			rand_y = ThreadLocalRandom.current().nextInt(0, columns);
			if(grid[rand_x][rand_y].GetId()==CellId.Forest) {
				spawner_cells.add(grid[rand_x][rand_y]);
			}
		} while (spawner_cells.size()<10);
	}
	
	/**
	 * Getter for the GameModel
	 * @return this.game
	 */
	public GameModel GetGameModel() {
		return game;
	}
	
	/**
	 * Getter for the number of line in the map
	 * @return this.lines
	 */
	public int GetLinesAmount() {
		return lines;
	}
	
	/**
	 * Getter for the number of columns in the map
	 * @return this.comlumns
	 */
	public int GetColumnsAmount() {
		return columns;
	}
	
	/**
	 * Getter for size of a cell
	 * @return this.Cell_size
	 */
	public int GetCellSize() {
		return Cell_size;
	}
	
	/**
	 * Getter for the grid of cell that represent this map
	 * @return this.grid
	 */
	public CellModel[][] GetGrid(){
		return grid;
	}
	
	/**
	 * Getter for cell
	 * @param x X coordonate on the grid
	 * @param y Y coordonate on the grid
	 * @return this.grid[x][y]
	 */
	public CellModel GetCellFromCoord(int x, int y) {
		return grid[x][y];
	}
	
	/**
	 * Getter for a cell
	 * @param p position on the grid
	 * @return this.grid[p.x][p.y]
	 */
	public CellModel getCellFromPoint(Point p) {
		return grid[p.x][p.y];
	}
	
	/**
	 * Getter for the width of the map
	 * @return this.width
	 */
	public int GetWidth() {
		return width;
	}
	
	/**
	 * Getter for the Height of the map
	 * @return this.height
	 */
	public int GetHeight() {
		return height;
	}
	
	/**
	 * Getter for the Origin point (0,0)
	 * @return this.OriginPoint
	 */
	public Point GetOriginCoord() {
		return OriginPoint;
	}
	
	/**
	 * Getter for the CityHall position
	 * @return this.city_origin
	 */
	public Point GetCityOriginCoord() {
		return city_origin;
	}
	
	/**
	 * Getter for all the city Cells
	 * @return this.city_cells
	 */
	public ArrayList<CellModel> getCityCells() {
		return city_cells;
	}
	
	/**
	 *  Getter for position on the screen from Coordonate on the map
	 * @param i X coordonate on the map
	 * @param j y coordonate on the map
	 * @return 
	 */
	public Point GetPosFromCoord(int i, int j) {
		Point pos = new Point(0,0);
		int x0 = OriginPoint.x;
		int y0 = OriginPoint.y;
		int s = Cell_size;
		int w_gap = s + s/2;
		int h = (int) (Math.sqrt(3.) * s);
		int h_gap = h;
		int gap;
		if(i%2==0) {
			gap = 0;
		} else {
			gap = h/2;
		}
		pos.x = (x0 + i*w_gap);
		pos.y = (y0 + j*h_gap - gap);
		return pos;
	}
	
	/**
	 * Getter for a position on the map depending on coordonate on the screen
	 * @param x_ X coordonate on the screen
	 * @param y_ Y coordonate on the screen
	 * @return
	 */
	public Point GetCoordFromClick(int x_, int y_) {
		int s = Cell_size;
		double angle = 60. * Math.PI / 180. ;
		double h = s * Math.sin(angle) * 2. ;
		double w = s * 2. - (1. - Math.cos(angle)) * s;
		
		double i = Math.floor(x_/w) ;
		double x = Math.floor(x_ - (i - w));
		
		double j;
		double y;
		
		if(i%2==0) {
			j = Math.floor(y_ / h);
			y = Math.floor(y_ - (j * h) - (h/2));
		} else {
			j = Math.floor((y_ - h / 2.)/h);
			y = Math.floor(y_ - (j * h) - h);
		}
		
		double f1 = Math.sin(angle) / Math.cos(angle) * x;
		double f2 = - (Math.sin(angle) / Math.cos(angle)) * x;
		
		if(y > 0 && f1 < y) {
			i = i - 1;
		} else if(f2 > y) {
			i = i - 1;
			j = j - 1;
		}
		return (new Point((int) i-1,(int) j-1)); 
	}
	
	/**
	 * The GetNeighbours Function
	 * return an ArrayList<Point> of all the Cell that suround a Cell on the map
	 * @param i X coordonate of the cell on the map
	 * @param j Y coordonate of the cell on the map
	 * @return
	 */
	public ArrayList<Point> GetNeighbours(int i, int j) {
		ArrayList<Point > neighbors = new ArrayList<Point>();
		Point[] directions = new Point[6];
		
		if(i%2==0) {
			directions[0] = new Point(0,-1);
			directions[1] = new Point(1,0);
			directions[2] = new Point(1,1);
			directions[3] = new Point(0,1);
			directions[4] = new Point(-1,1);
			directions[5] = new Point(-1,0);
			
			for(Point dir : directions) {
				Point pts = new Point(i+dir.x,j+dir.y);
				if(CellIsValid(pts)) {
					neighbors.add(pts);
				}
			}
		} else {
			directions[0] = new Point(0,-1);
			directions[1] = new Point(1,-1);
			directions[2] = new Point(1,0);
			directions[3] = new Point(0,1);
			directions[4] = new Point(-1,0);
			directions[5] = new Point(-1,-1);
			
			for(Point dir : directions) {
				Point pts = new Point(i+dir.x,j+dir.y);
				if(CellIsValid(pts)) {
					neighbors.add(pts);
				}
			}
		}
		
		return neighbors;
	}
	
	/**
	 * This Function return a boolean if there is no worker or building on the cell
	 * @param pts position of the cell
	 * @return
	 */
	public boolean CellIsFree(Point pts) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(pts==worker.getPos()) {
				return false;
			}
		}
		for(BuildingModel building : game.GetBuildingList()) {
			if(pts==building.GetPos()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Return a boolean that said if the coordonate givent to the function can be a cell of the map
	 * @param cell coordonate of a cell
	 * @return
	 */
	public boolean CellIsValid(Point cell) {
		int i = cell.x;
		int j = cell.y;
		return (i<lines && i>=0 && j<columns && j>=0);
	}
	
	/**
	 * The GetShortestPath Function
	 * Calcul the shortest path between to point on the map
	 * The path given by the function will avoid other player and cell that have a building on it
	 * @param start cell from where we start
	 * @param end destiantion cell
	 * @return a path between those two cell
	 */
	public ArrayList<Point> GetShortestPath(Point start, Point end){
		Point[][] graph;
		graph = new Point[lines][columns];
		HashMap<Point, Integer> distances= new HashMap<Point,Integer>();
		HashMap<Point, Point> prev_nodes = new HashMap<Point,Point>();
		for(CellModel[] line : grid){
			for(CellModel cell : line){
				graph[cell.GetX()][cell.GetY()] = new Point(cell.GetX(),cell.GetY());
			}
		}
		for(Point[] pts : graph){
			for(Point p : pts){
				distances.put(p,Integer.MAX_VALUE);
				prev_nodes.put(p,null);
			}
		}
		distances.put(start,0);
		PriorityQueue<Point> unvisited = new PriorityQueue<Point>(Comparator.comparingInt(distances::get));
		unvisited.add(start);
		while(!unvisited.isEmpty()){
			Point current = unvisited.poll();
			if(current==end){
				break;	
			}
			ArrayList<Point> neighbors = GetNeighbours(current.x, current.y);
			for(Point neigh : neighbors){
				int dist = distances.get(current) + 1;
				if(dist < distances.get(neigh)){
					distances.put(neigh, dist);
					prev_nodes.put(neigh, current);
					unvisited.add(neigh);
				}
			}
		}
		if(distances.get(end) == Integer.MAX_VALUE){
			return null;
		}
		ArrayList<Point> path = new ArrayList<Point>();
		Point cur = end;
		while(cur != null){
			path.add(cur);
			cur = prev_nodes.get(cur);
		}
		Collections.reverse(path);
		return path;
	}
	
	/**
	 * Convert two int as a Point
	 * @param x
	 * @param y
	 * @return
	 */
	public Point GetPointFromCoord(int x, int y) {
		CellModel cell = GetCellFromCoord(x,y);
		Point pts = new Point(cell.GetX(),cell.GetY());
		return pts;
	}
	
	/**
	 *  Getter for the nearest worker that's free on the map
	 * @param coord coordonate from where we want the nearest worker
	 * @return the nearest worker
	 */
	public WorkerModel GetNearestWorker(Point coord) {
		WorkerModel nearest = null;
		int dist;
		int shortest_dist = Integer.MAX_VALUE;
		ArrayList<WorkerModel> workers = game.GetWorkerModel();
		for(WorkerModel worker : workers) {
			if(!worker.GetOccupied()) {
				ArrayList<Point> worker_path = GetShortestPath(worker.getPos(),coord);
				dist = worker_path.size();
				if(dist <= shortest_dist) {
					shortest_dist = dist;
					nearest = worker;
				}
			}
		}
		return nearest;
	}
	
	/**
	 * Getter for the nearest worker that's free on the map that can execute a certain task
	 * @param cell Cell from where we want the nearest worker
	 * @param task the task the worker have to be able to execute
	 * @return
	 */
	public WorkerModel GetNearestWorker(CellModel cell, Actions task) {
		WorkerModel nearest = null;
		WorkerRole needed_worker = WorkerRole.Worker;
		
		if(task==Actions.Build_Production || task==Actions.Collect || task==Actions.LevelUp) {
			if(CellIsOccupiedByBuilding(cell)) {
				BuildingModel building = GetBuildingFromCoord(cell.GetCoord());
				switch (building.getId()) {
					case SawMill:
						needed_worker = WorkerRole.Lumberjack;
						break;
						
					case Mine:
						needed_worker = WorkerRole.Miner;
						break;
						
					case Quarry:
						needed_worker = WorkerRole.QuarryWorker;
						break;
						
					default:
						needed_worker = WorkerRole.Worker;
						break;
				}
			} else {
				switch (cell.GetId()) {
					case Forest:
						needed_worker = WorkerRole.Lumberjack;
						break;
						
					case Mountain:
						needed_worker = WorkerRole.Miner;
						break;
						
					case Iron_Deposit:
						needed_worker = WorkerRole.QuarryWorker;
						break;
						
					default:
						needed_worker = WorkerRole.Worker;
						break;
				}
			}
		} else if(task==Actions.Move) {
			needed_worker = WorkerRole.Knight;
		}
		
		int dist;
		int shortest = Integer.MAX_VALUE;
		
		for(WorkerModel worker : game.GetWorkerModel()) {
			if((worker.GetRole()==needed_worker) && !worker.GetOccupied()) {
				dist = GetShortestPath(cell.GetCoord(),worker.getPos()).size();
				if(dist<=shortest) {
					shortest = dist;
					nearest = worker;
				}
			} else if(needed_worker == WorkerRole.Worker && !worker.GetOccupied()) {
				dist = GetShortestPath(cell.GetCoord(),worker.getPos()).size();
				if(dist<=shortest) {
					shortest = dist;
					nearest = worker;
				}
			}
		}
		
		return nearest;
	}
	
	/**
	 * The CellIsOccupiedByBuilding function
	 * return a boolean that describe if the cell already have a building on it
	 * @param cell
	 * @return
	 */
	public boolean CellIsOccupiedByBuilding(CellModel cell) {
		for(BuildingModel building : game.GetBuildingList()) {
			if(building.GetPos().x==cell.GetX() && building.GetPos().y==cell.GetY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The CellIsOccupiedByWorker function
	 * return a boolean that describe if the cell already have a worker on it
	 * @param cell
	 * @return
	 */
	public boolean CellIsOccupiedByWorker(CellModel cell) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(worker.getcoordX()==cell.GetX() && worker.getcoordY()==cell.GetY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The CanExpand function
	 * Return a boolean that describe if we can expand the city to this cell
	 * The cell must not already be a CityCell
	 * And on of it neighbor must be a cityCell
	 * @param cell
	 * @return
	 */
	public boolean CanExpand(CellModel cell) {
		if(cell.GetId()==CellId.City) {
			return false;
		}
		for(Point neigh : GetNeighbours(cell.GetX(), cell.GetY())) {
			if(grid[neigh.x][neigh.y].GetId()==CellId.City) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function return a boolean that describe if we can build any building on a cell
	 * @param cell
	 * @return
	 */
	public boolean CanBuild(CellModel cell) {
		return cell.GetId()==CellId.Iron_Deposit || cell.GetId()==CellId.Forest || cell.GetId()==CellId.Mountain || cell.GetId()==CellId.City;
	}


	/**
	 * This function return a boolean that describe if we can harvest ressources from a cell
	 * @param cell
	 * @return
	 */
	public boolean CanCollect(CellModel cell) {
		return cell.GetId()==CellId.Iron_Deposit || cell.GetId()==CellId.Forest || cell.GetId()==CellId.Mountain || (CellIsOccupiedByBuilding(cell) && BuildingIsCollectable(cell));
	}
	
	/**
	 * This function return a boolean that describe if there is a building on the cell and if we can collect ressources from this building
	 * @param cell
	 * @return
	 */
	public boolean BuildingIsCollectable(CellModel cell) {
		BuildingId bid = GetBuildingFromCoord(cell.GetCoord()).getId();
		return (bid==BuildingId.Mine) || (bid==BuildingId.SawMill) || (bid==BuildingId.Quarry);
	}

	/**
	 * Return the building on a cell from the given Coordonate if there is one else it return the null pointer
	 * @param coord
	 * @return
	 */
	public BuildingModel GetBuildingFromCoord(Point coord) {
		for(BuildingModel building : game.GetBuildingList()) {
			if(building.GetPos().x==coord.x && building.GetPos().y==coord.y) {
				return building;
			}
		}
		return null;
	}

	/**
	 * Return the worker on a cell from the given Coordonate if there is one else it return the null pointer
	 * @param coord
	 * @return
	 */
	public WorkerModel GetWorkerFromCoord(Point coord) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(worker.getcoordX()==coord.x && worker.getcoordY()==coord.y) {
				return worker;
			}
		}
		return null;
	}
	
	/**
	 * add a building on the game
	 * @param bid the ID of the building we want to build
	 * @param pts position on the map of this new building
	 */
	public void Build(BuildingId bid, Point pts) {
		game.AddBuilding(bid, pts);
	}
	
	/**
	 * Return all the cityCell on the map
	 * @return this.city_cells
	 */
	public ArrayList<CellModel> GetCityCells(){
		return city_cells;
	}
	
	/**
	 * Return if there is a shop the cell
	 * @param cell
	 * @return
	 */
	public boolean CellHasShop(CellModel cell) {
		BuildingModel shop = GetBuildingFromCoord(cell.GetCoord());
		if(shop!=null) {
			return shop.getId()==BuildingId.Shop;
		} else {
			return false;
		}
	}
	
	/**
	 * Return  a boolean that describe if the player given in argument has a level under 5
	 * @param cell
	 * @param worker
	 * @return
	 */
	public boolean CanUpgradeWorker(CellModel cell, WorkerModel worker) {
		return worker.GetLevel()<5;
	}
	
	/**
	 * Return  a boolean that describe if the building given in argument has a level under 3
	 * @param cell
	 * @param building
	 * @return
	 */
	public boolean CanUpgradeBuilding(CellModel cell, BuildingModel building) {
		return building.GetLevel()<3;
	}
	
	/**
	 * Setter for the variable current_sell
	 * @param cell
	 */
	public void SetCurrentCell(CellModel cell) {
		current_cell = cell;
	}
	
	/**
	 * Getter for the current_cell
	 * @return this.current_cell
	 */
	public CellModel GetCurrentCell() {
		return current_cell;
	}
	
	/**
	 * Return a boolean that describe if there is a worker and a building on a cell
	 * @param cell
	 * @return
	 */
	public boolean CanDrop(CellModel cell) {
		return (CellIsOccupiedByBuilding(cell) && GetBuildingFromCoord(cell.GetCoord()).getId()==BuildingId.CityHall) || CellIsOccupiedByWorker(cell);
	}
	
	/**
	 * Return a boolean that describe if there is a building on a cell and we can create a new worker
	 * @param cell
	 * @return
	 */
	public boolean CanTrain(CellModel cell) {
		BuildingId bid = GetBuildingFromCoord(cell.GetCoord()).getId();
		return ((bid==BuildingId.Barrack) || (bid==BuildingId.LumberCamp) || (bid==BuildingId.MinerCamp) || (bid==BuildingId.QuarryWorkerCamp)) && game.CanCreateNewWorker(GetBuildingFromCoord(cell.GetCoord()));
	}
	
	/**
	 * Getter for the nearest worker that's free on the map that have a certain role
	 * @param cell
	 * @param role
	 * @return
	 */
	public WorkerModel GetNearestWorkerFromRole(CellModel cell, WorkerRole role) {
		int dist;
		int shortest = Integer.MAX_VALUE;
		WorkerModel nearest = null;
		
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(role==WorkerRole.Worker && !worker.GetOccupied()) {
				dist = GetShortestPath(worker.getPos(),cell.GetCoord()).size();
				if(dist <= shortest) {
					shortest = dist;
					nearest = worker;
				}
			} else if(worker.GetRole()==role && !worker.GetOccupied()) {
				dist = GetShortestPath(worker.getPos(),cell.GetCoord()).size();
				if(dist <= shortest) {
					shortest = dist;
					nearest = worker;
				}
			}
		}
		
		return nearest;
	}
	
	/**
	 * Getter for all the spawner cell on the map
	 * @return this.spawner_cells
	 */
	public ArrayList<CellModel> GetSpawnerCells(){
		return spawner_cells;
	}
	

	/**
	 * Return a boolean that describe if there is a worker on a certain coordonate on the map
	 * @param coord
	 * @return
	 */
	public boolean WorkerOnCoord(Point coord) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(worker.getcoordX()==coord.x && worker.getcoordY()==coord.y) {
				return true;
			}
		}
		return false;
	}
	
	public Pair<Boolean,Point> EnnemySearchForCitizen(ArrayList<Point> vision) {
		int len = vision.size();
		
		for(int i=0; i<len; i++) {
			Point pts = vision.get(i);
			if(WorkerOnCoord(pts)) {
				return new Pair(true,pts);
			}
			for(Point neigh : GetNeighbours(pts.x, pts.y)) {
				if(WorkerOnCoord(neigh)) {
					return new Pair(true,neigh);
				}
			}
		}
		
		return new Pair(false,null);
	}
	
	/**
	 * The KnightsAreOnMap Function 
	 * Return True if on of the worker is a knight
	 * else return false
	 * @return
	 */
	public boolean KnightsAreOnMap() {
		for(WorkerModel wm : game.GetWorkerModel()) {
			if(wm.GetRole()==WorkerRole.Knight) {
				return true;
			}
		}
		return false;
	}
}