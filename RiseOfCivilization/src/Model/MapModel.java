package Model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;

import Types.CellId;

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
				int rand_id = ThreadLocalRandom.current().nextInt(0,1);
				if(rand_id==0) {
					id = CellId.Plain;
				} else if(rand_id==1) {
					id= CellId.Forest;
				} else {
					id = CellId.None;
				}
				grid[i][j] = new CellModel(i,j,id);
			}
		}
		
		int city_x = ThreadLocalRandom.current().nextInt(1,20);
		int city_y = ThreadLocalRandom.current().nextInt(1,17);
		
		grid[city_x][city_y].TurnToCity();
		grid[city_x+1][city_y].TurnToCity();
		grid[city_x][city_y+1].TurnToCity();
		
		direction[0] = new Point(0,-1);
		direction[1] = new Point(1,0);
		direction[2] = new Point(1,1);
		direction[3] = new Point(0,1);
		direction[4] = new Point(-1,0);
		direction[5] = new Point(-1,-1);
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
	
	public Point GetCoordFromClick_will(int x_, int y_) {
		Point coord = new Point(0,0);
		int x0 = OriginPoint.x; 
		int y0 = OriginPoint.y;
		
		int s = Cell_size;
		int h = (int) (Math.sqrt(3.) * s);
		int w = 2*s;
		
		
		int x = x_ + x0 - s;
		int y = y_ +y0 - (h/2);
		
		double half_h = h/2.;
		double quart_w = w/4.;
		
		double pos_x = x/(quart_w*7);
		double pos_y = y/(half_h*3);
		System.out.printf("pos_x : %f | pos_y : %f \n", pos_x, pos_y);
		int mod_x = (int) pos_x%7;
		int mod_y = (int) pos_y%3;
		System.out.printf("mod_x : %d | mod_y : %d \n", mod_x, mod_y);
		
		switch (mod_y) {
			case 0 :
				if(mod_x > 2) {
					System.out.printf("coord : %f %f \n", (pos_x/4) + 1, pos_y/2);
				} 
			case 1 : 
				if(mod_x < 3) {
					System.out.printf("coord : %f %f \n", pos_x/4, pos_y/2);
				} else if(mod_x > 3) {
					System.out.printf("coord : %f %f \n", (pos_x/4) + 1, pos_y/2);
				} else {
					//function GetPos(Point(i,j)) -> return sa position à l'ecran
					//GetPos((pos_x/4) + 1, pos_y/2) & GetPos(pos_x/4, pos_y/2) 
					//distance euclidienne entre les deux -> la plus proche est la case voulu
					
					//version 2 -> couper le rectangle en 2 et juste dire haut -> +1 & bas -> 0
				}
			case 2 :
				if(mod_x < 4) {
					System.out.printf("coord : %f %f \n", pos_x/4, pos_y/2);
				}
		}
		
		return coord;
	}
	
	public Point GetCoordFromClick_abel(int x_, int y_) {
		int s = Cell_size;
		double angle = 60. * Math.PI / 180. ;
		double h = s * Math.sin(angle) * 2. ;
		double w = s * 2. - (1. - Math.cos(angle)) * s;
		
		int i = (int) Math.floor( x_ / w );
		int x = (int) (x_ - (i - w));
		
		int j;
		int y;
		
		if(i%2==0) {
			j = (int) Math.floor( y_ / h);
			y = (int) (y_ - (j * h) - (h/2));
		} else {
			j = (int) Math.floor( (y_ - h / 2.) / h);
			y = (int) (y_ - (j * h) - h);
		}
		
		double f1 = Math.sin(angle) / Math.cos(angle) * x;
		double f2 = - (Math.sin(angle) / Math.cos(angle)) * x;
		
		if(y > 0 && f1 < y) {
			i = i - 1;
		} else if(f2 > y) {
			i = i - 1;
			j = j - 1;
		}
		System.out.printf("Clicked coords are : (%d,%d)\n", i-1, j-1);
		return (new Point(i-1,j-1));
	}
	
	public ArrayList<Point> GetNeighbours(int i, int j) {
		ArrayList<Point > neighbours = new ArrayList<Point>();
		
		for(int n=0; n<6; n++) {
			int x = direction[n].x;
			int y = direction[n].y;
			if(i+x > 0 && i+x < 8 && j+y > 0 && j+y < 8) {
				neighbours.add(new Point(i+x,j+y));
			}
		}
		
		return neighbours;
	}
	
}