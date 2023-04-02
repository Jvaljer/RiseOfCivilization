package View;

import Model.*;
import java.util.*;
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
	private int width;
	private int height;
	private int lines;
	private int columns;
	private CellView[][] grid_view;
	private ArrayList<CellView> city_cells;
	
	public MapView(GameView V, MapModel M) {
		view = V;
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
		
		city_cells = new ArrayList<CellView>();
		for(CellView[] cells : grid_view) {
			for(CellView cell_v : cells) {
				for(CellModel cell_m : map_model.GetCityCells()) {
					if(cell_v.GetAbs()==cell_m.GetX() && cell_v.GetOrd()==cell_m.GetY()) {
						city_cells.add(cell_v);
					}
				}
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
	
	public void DrawCurrentCell(Graphics G) {
		Point coord = map_model.GetCurrentCell().GetCoord();
		CellView cell = grid_view[coord.x][coord.y];
		cell.DrawContour(G, map_model.GetCellSize(), map_model.GetOriginCoord().x, map_model.GetOriginCoord().y);
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		DrawMap(G);
		if(map_model.GetCurrentCell()!=null) {
			DrawCurrentCell(G);
		}
		
		for(BuildingView b_view : view.GetBuildingsView()) {
			b_view.DrawBuilding(G);
		}
		
		for(int i = 0; i < this.view.GetGameModel().GetWorkerModel().size(); i++)
		{
			if(this.view.GetGameModel().GetWorkerModel().get(i).GetMoving())
			{
				this.view.getWorkerView().get(i).drawMovingPlayer(G, this.map_model.GetGameModel().GetWorkerModel().get(i).getNextcoord());
			} else {
				this.view.getWorkerView().get(i).DrawWorker(G);
			}
		}
	}
}