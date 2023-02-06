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
	
	public MapModel(GameModel M) {
		game = M;
		width = game.GetMapWidth();
		height = game.GetMapHeight();
		//must add calcul of starting point, and all percentages for Cell types
		grid = new CellModel[lines][columns];
		for(int i=0; i<lines; i++) {
			for(int j=0; j<columns; j++) {
				//must add the cell's Id random association
				grid[i][j] = new CellModel(i,j,CellId.None);
			}
		}
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
		
		//calculating important dimensions
		int size = Cell_size;
		int w = 2*size;
		int h = (int) (Math.sqrt(3.) * size);
		
		//getting screen's spaces
		int gap_x = OriginPoint.x - Cell_size;
		int gap_y = OriginPoint.y - h;
		
		//scaling mouse's coord
		int x = x_ + gap_x;
		int y = y_ + gap_y;
		
		//interpolating coordinates values
		int pos_x = x / ( (w/4) * 7 ); 
		int pos_y = y / ( (h/2) * 3 );
		
		int mod_x = pos_x%7;
		int mod_y = pos_y%3;
		
		switch (mod_y) {
			case 0 :
				//on first Y demi
				if(mod_x < 4) {
					//we're out
					coord.x = -1;
					coord.y = -1;
				} else if(mod_x == 5 || mod_x == 6) {
					coord.x = (pos_x / 4) + 1;
					coord.y = (pos_y / 2);
				} else {
					//must get 4 & 7
				}
			case 1 : 
				//on second Y demi
				if(mod_x == 2 || mod_x == 3) {
					coord.x = (pos_x / 4);
					coord.y = (pos_y / 2);
				} else if(mod_x == 5 || mod_x == 6) {
					coord.x = (pos_x / 4);
					coord.y = (pos_y / 2) + 1;
				} else if(mod_x == 4) {
					// betwenn 2 cells
				}else {
					//must do 1 & 7
				}
			case 2 :
				//on third Y demi
				
			default:
				break;
		}
		
		return coord;
	}
	
}