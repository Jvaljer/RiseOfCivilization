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
	private static final Point OriginPoint = new Point(40,80);
	private CellModel[][] grid;
	private Point[] direction = new Point[6];
	private Point city_origin;
	
	public MapModel(GameModel M) {
		game = M;
		width = game.GetMapWidth();
		height = game.GetMapHeight();
		
		direction[0] = new Point(0,-1);
		direction[1] = new Point(1,-1);
		direction[2] = new Point(1,0);
		direction[3] = new Point(0,1);
		direction[4] = new Point(-1,0);
		direction[5] = new Point(-1,-1);
		
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
		
		double i = (x_/w) ; //Math.floor() here ? 
		double x = (x_ - (i - w));
		
		double j;
		double y;
		
		if(i%2==0) {
			j = (y_ / h); //here
			y = (y_ - (j * h) - (h/2));
		} else {
			j = ((y_ - h / 2.)/h); //& here
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
		ArrayList<Point > neighbours = new ArrayList<Point>();
		
		for(Point pts : direction) {
			int x = pts.x;
			int y = pts.y;
			if(i+x > 0 && i+x < 21 && j+y > 0 && j+y < 18) {
				neighbours.add(new Point(i+x,j+y));
			}
		}
		
		return neighbours;
	}
	
	public boolean CellIsValid(Point cell) {
		int i = cell.x;
		int j = cell.y;
		return (i<21 && i>=0 && j<18 && j>=0);
	}
	
	public ArrayList<Point> GetShortestPath(Point s, Point e){
		Node[][] Graph = new Node[lines][columns];
		Node start = new Node(s.x,s.y);
		Node end = new Node(e.x,e.y);
		for(CellModel[] line : grid) {
			for(CellModel cell : line) {
				Graph[cell.GetX()][cell.GetY()] = new Node(cell.GetX(),cell.GetY());
			}
		}
		
		Map<Node, Integer> distances = new HashMap<>();
		Map<Node, Node> prev_nodes = new HashMap<>();
		
		for(Node[] line : Graph) {
			for(Node node : line) {
				distances.put(node, Integer.MAX_VALUE);
				prev_nodes.put(node, null);
			}
		}
		distances.put(start, 0);
		
		PriorityQueue<Node> unvisited = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		unvisited.add(start);
		
		while(!unvisited.isEmpty()) {
			Node current = unvisited.poll();
			
			if(current == end) {
				break;
			}
			
			for(Node neighbor : current.GetNeighbors(Graph)) {
				int dist = distances.get(current) + 1;
				
				if(dist < distances.get(neighbor)) {
					distances.put(neighbor, dist);
					prev_nodes.put(neighbor, current);
					unvisited.add(neighbor);
				}
			}
		}
		
		if(distances.get(end) == Integer.MAX_VALUE) {
			return null;
		}
		
		ArrayList<Node> path = new ArrayList<Node>();
		Node current = end;
		while(current != null) {
			path.add(current);
			current = prev_nodes.get(current);
		}
		Collections.reverse(path);
		
		ArrayList<Point> pts_path = new ArrayList<Point>();
		for(Node node : path) {
			pts_path.add(new Point(node.x, node.y));
		}
		
		return pts_path;
	}
}