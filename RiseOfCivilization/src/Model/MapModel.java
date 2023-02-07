package Model;

import java.util.*;
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
				grid[i][j] = new CellModel(i,j,CellId.None);
			}
		}
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
	
	public Point GetCoordFromClick(int x_, int y_) {
		Point coord = new Point(0,0);
		//must implement properly
		return coord;
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