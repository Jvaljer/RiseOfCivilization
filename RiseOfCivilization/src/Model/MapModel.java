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
	private GameModel game;
	private static int width;
	private static int height;
	private static final int lines = 21;
	private static final int columns = 18;
	private static final int Cell_size = 20;
	private static final Point OriginPoint = new Point(2*Cell_size,2*Cell_size);
	private CellModel[][] grid;
	private Point city_origin;
	private ArrayList<CellModel> city_cells;
	private CellModel current_cell;
	
	public MapModel(GameModel M) {
		game = M;
		width = game.GetMapWidth();
		height = game.GetMapHeight();
		
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
	}
	
	public GameModel GetGameModel() {
		return game;
	}
	
	public int GetLinesAmount() {
		return lines;
	}
	
	public int GetColumnsAmount() {
		return columns;
	}
	
	public int GetCellSize() {
		return Cell_size;
	}
	
	public CellModel[][] GetGrid(){
		return grid;
	}
	
	public CellModel GetCellFromCoord(int x, int y) {
		return grid[x][y];
	}
	
	public CellModel getCellFromPoint(Point p) {
		return grid[p.x][p.y];
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
	public Point GetOriginCoord() {
		return OriginPoint;
	}
	
	public Point GetCityOriginCoord() {
		return city_origin;
	}
	
	public ArrayList<CellModel> getCityCells() {
		return city_cells;
	}
	
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
		System.out.printf("Clicked coords are : (%d,%d)\n",(int) i-1,(int) j-1);
		return (new Point((int) i-1,(int) j-1));
	}
	
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
	
	public boolean CellIsFree(Point pts) {
		ArrayList<Point> workers_pos = new ArrayList<Point>();
		ArrayList<Point> buildings_pos = new ArrayList<Point>();
		
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
	
	public ArrayList<Point> GetValidNeighbors(int i, int j){
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
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
				if(CellIsFree(pts)) {
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
				if(CellIsFree(pts)) {
					neighbors.add(pts);
				}
			}
		}
		
		return neighbors;
	}
	
	public boolean CellIsValid(Point cell) {
		int i = cell.x;
		int j = cell.y;
		return (i<21 && i>=0 && j<18 && j>=0);
	}
	
	public ArrayList<Point> GetShortestPath(Point start, Point end){
		Point[][] graph;
		graph = new Point[21][18];
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
			//ArrayList<Point> neighbors = GetValidNeighbors(current.x,current.y);
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
	
	public Point GetPointFromCoord(int x, int y) {
		CellModel cell = GetCellFromCoord(x,y);
		Point pts = new Point(cell.GetX(),cell.GetY());
		return pts;
	}
	
	public WorkerModel GetNearestWorker(Point coord) {
		WorkerModel nearest = null;
		int dist;
		int shortest_dist = Integer.MAX_VALUE;
		ArrayList<WorkerModel> workers = game.GetWorkerModel();
		int cpt=0;
		int took=-1;
		for(WorkerModel worker : workers) {
			if(!worker.GetOccupied()) {
				ArrayList<Point> worker_path = GetShortestPath(worker.getPos(),coord);
				dist = worker_path.size();
				if(dist <= shortest_dist) {
					shortest_dist = dist;
					nearest = worker;
					took = cpt;
				}
			}
			cpt++;
		}
		return nearest;
	}
	
	public WorkerModel GetNearestWorker(CellModel cell, String task) {
		WorkerModel nearest = null;
		WorkerRole needed_worker;
		//here we wanna return the nearest CORRESPONDING WORKER 
			//if the task is moving -> nearest is okay
			//if the task is harvesting/collecting -> nearest corresponding role
			//if the task is expanding -> nearest is okay
			//if the task is building -> nearest corresponding role
		if(task=="build" || task=="harvest" || task=="collect") {
			if(CellIsOccupiedByBuilding(cell)) {
				BuildingModel building = GetBuildingFromCoord(cell.GetCoord());
				switch (building.GetId()) {
					case SawMill:
						needed_worker = WorkerRole.LumberJack;
						break;
						
					case Mine:
						needed_worker = WorkerRole.Miner;
						break;
						
					case Quarry:
						needed_worker = WorkerRole.QuarryMan;
						break;
						
					default:
						needed_worker = WorkerRole.Worker;
						break;
				}
			} else {
				switch (cell.GetId()) {
					case Forest:
						needed_worker = WorkerRole.LumberJack;
						break;
						
					case Mountain:
						needed_worker = WorkerRole.Miner;
						break;
						
					case Iron_Deposit:
						needed_worker = WorkerRole.QuarryMan;
						break;
						
					default:
						needed_worker = WorkerRole.Worker;
						break;
				}
			}
		} else if(task=="move" || task=="expand") {
			needed_worker = null;
		}
		
		//must finish implementation
		return nearest;
	}
	
	public boolean CellIsOccupiedByBuilding(CellModel cell) {
		for(BuildingModel building : game.GetBuildingList()) {
			if(building.GetPos().x==cell.GetX() && building.GetPos().y==cell.GetY()) {
				return true;
			}
		}
		return false;
	}
	public boolean CellIsOccupiedByWorker(CellModel cell) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(worker.getcoordX()==cell.GetX() && worker.getcoordY()==cell.GetY()) {
				return true;
			}
		}
		return false;
	}
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
	public boolean CanBuild(CellModel cell) {
		return cell.GetId()==CellId.Iron_Deposit || cell.GetId()==CellId.Forest || cell.GetId()==CellId.Mountain;
	}
	public boolean CanCollect(CellModel cell) {
		return cell.GetId()==CellId.Iron_Deposit || cell.GetId()==CellId.Forest || cell.GetId()==CellId.Mountain || CellIsOccupiedByBuilding(cell);
	}
	
	public BuildingModel GetBuildingFromCoord(Point coord) {
		for(BuildingModel building : game.GetBuildingList()) {
			if(building.GetPos().x==coord.x && building.GetPos().y==coord.y) {
				return building;
			}
		}
		return null;
	}
	public WorkerModel GetWorkerFromCoord(Point coord) {
		for(WorkerModel worker : game.GetWorkerModel()) {
			if(worker.getcoordX()==coord.x && worker.getcoordY()==coord.y) {
				return worker;
			}
		}
		return null;
	}
	
	public void Build(BuildingId bid, Point pts) {
		game.AddBuilding(bid, pts);
	}
	
	public ArrayList<CellModel> GetCityCells(){
		return city_cells;
	}
	
	public boolean CellHasShop(CellModel cell) {
		BuildingModel shop = GetBuildingFromCoord(cell.GetCoord());
		if(shop!=null) {
			return shop.GetId()==BuildingId.Shop;
		} else {
			return false;
		}
	}
	
	public boolean CanUpgradeWorker(CellModel cell, WorkerModel worker) {
		return worker.GetLevel()<5;
	}
	
	public boolean CanUpgradeBuilding(CellModel cell, BuildingModel building) {
		return building.GetLevel()<3;
	}
	
	public void SetCurrentCell(CellModel cell) {
		current_cell = cell;
	}
	
	public CellModel GetCurrentCell() {
		return current_cell;
	}
}