package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
	private MapModel map_model;
	private int width;
	private int height;
	private int lines;
	private int columns;
	private CellView[][] grid_view;
	public MapView(MapModel M) {
		map_model = M;
		
		lines = map_model.GetLinesAmount();
		columns = map_model.GetColumnsAmount();
		
		width = map_model.GetWidth();
		height = map_model.GetHeight();
		setPreferredSize(new Dimension(width,height));
		
		grid_view = new CellView[lines][columns];
		for(int j=0; j<columns; j++) {
			for(int i=0; i<lines; i++) {
				grid_view[i][j] = new CellView(map_model.GetCellFromCoord(i, j));
			}
		}
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
	public MapModel GetMapModel() {
		return map_model;
	}
	
	public void DrawMap(Graphics G) {
		int s = map_model.GetCellSize();
		
		int x0 = map_model.GetOriginCoord().x;
		int y0 = map_model.GetOriginCoord().y;
		
		for(int j=0; j<columns; j++) {
			for(int i=0; i<lines; i++) {
				
				//DrawCell(i,j,G, s, x0, y0);
				
				grid_view[i][j].DrawCell(G, s, x0, y0);
			}
		}
		
	}
}