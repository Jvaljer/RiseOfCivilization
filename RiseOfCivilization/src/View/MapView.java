package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

/**
 * View class of the overall map, as a grid of regular hexagon, placed in an
 * even-q disposition, using a vertical layout. And drawing each cell usign its own
 * DrawCell method.
 * @author abel
 * @SuppressWarnings("serial")
 */
public class MapView extends JPanel {
	private GameView view;
	private MapModel map_model;
	private WorkerView player_view;
	private int width;
	private int height;
	private int lines;
	private int columns;
	private CellView[][] grid_view;
	
	public MapView(GameView V, MapModel M) {
		view = V;
		map_model = M;
		
		player_view = new WorkerView(view);
		
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
	
	public CellView[][] GetGrid(){
		return grid_view;
	}
	
	public void DrawMap(Graphics G) {
		int s = map_model.GetCellSize();
		
		int x0 = map_model.GetOriginCoord().x;
		int y0 = map_model.GetOriginCoord().y;
		
		for(int j=0; j<columns; j++) {
			for(int i=0; i<lines; i++) {
				grid_view[i][j].DrawCell(G, s, x0, y0);
			}
		}
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		DrawMap(G);
		player_view.DrawPlayer(G);
	}
}