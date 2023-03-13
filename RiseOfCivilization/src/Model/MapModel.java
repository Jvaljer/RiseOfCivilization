package Model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;

import Types.CellId;

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
				int rand_id = ThreadLocalRandom.current().nextInt(0,3);
				if(rand_id<2) {
					id = CellId.Plain;
				} else if(rand_id>=2) {
					id= CellId.Forest;
				} else {
					id = CellId.None;
				}
				grid[i][j] = new CellModel(i,j,id);
			}
		}
		
		int city_x = ThreadLocalRandom.current().nextInt(1,20);
		int city_y = ThreadLocalRandom.current().nextInt(1,17);
		city_origin = new Point(city_x, city_y);
		
		grid[city_x][city_y].TurnToCity();
		ArrayList<Point> city_neigh = GetNeighbours(city_x, city_y);
		int i1 = ThreadLocalRandom.current().nextInt(0,city_neigh.size());
		Point city = city_neigh.get(i1);
		grid[city.x][city.y].TurnToCity();
		int i2;
		do {
			i2 = ThreadLocalRandom.current().nextInt(0,city_neigh.size());
		} while (i2==i1);
		city = city_neigh.get(i2);
		grid[city.x][city.y].TurnToCity();
		
		
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
		//System.out.printf("pos from coord : x->[%d] y->[%d] \n",pos.x, pos.y);
		return pos;
	}
	
	public Point GetCoordFromClick(int x_, int y_) {
		int s = Cell_size;
		double angle = 60. * Math.PI / 180. ;
		double h = s * Math.sin(angle) * 2. ;
		double w = s * 2. - (1. - Math.cos(angle)) * s;
		
		double i = Math.floor(x_/w) ;
		double x = (x_ - (i - w));
		
		double j;
		double y;
		
		if(i%2==0) {
			j = Math.floor(y_ / h); 
			y = (y_ - (j * h) - (h/2));
		} else {
			j = Math.floor((y_ - h / 2.)/h);
			y = (y_ - (j * h) - h);
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
		if(j%2==0) {
			directions[0] = new Point(0,-1);
			directions[1] = new Point(1,0);
			directions[2] = new Point(1,1);
			directions[3] = new Point(0,1);
			directions[4] = new Point(-1,1);
			directions[5] = new Point(-1,0);
			
			for(Point dir : directions) {
				Point pts = new Point(i+dir.x, j+dir.y);
				if(CellIsValid(pts)) {
					neighbors.add(pts);
				}
			}
		} else {
			directions[0] = new Point(0,-1);
			directions[1] = new Point(1,0);
			directions[2] = new Point(1,1);
			directions[3] = new Point(0,1);
			directions[4] = new Point(-1,1);
			directions[5] = new Point(-1,0);
			for(Point dir : directions) {
				Point pts = new Point(i+dir.x, j+dir.y);
				if(CellIsValid(pts)) {
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
		for(WorkerModel worker : workers) {
			if(!worker.GetOccupied()) {
				ArrayList<Point> worker_path = GetShortestPath(worker.getPos(),coord);
				dist = worker_path.size();
				if(dist <= shortest_dist) {
					dist = shortest_dist;
					nearest = worker;
				}
			}
		}
		return nearest;
	}
}