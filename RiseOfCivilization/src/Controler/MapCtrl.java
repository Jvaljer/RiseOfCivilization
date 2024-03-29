package Controler;

import Model.*;
import View.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/** 
 * This class implements MouseListener in order to collect clics & act depending of their position on the map
 *   it also extends Thread to create one which will dict real-time behaviours of it.
 * @author Abel
 */
public class MapCtrl implements MouseListener {
	private GameCtrl ctrl;
	private GameModel model;
	private GameView view;
	private MapModel map_model;
	private MapView map_view;
	private CellCtrl[][] ctrl_grid;
	
	private CellCtrl new_click;
	private CellCtrl old_click;
	
	private CellModel current_cell;
	
	public MapCtrl(GameCtrl C,GameView V) {
		view = V;
		ctrl = C;
		view.addMouseListener(this);
		model = view.GetGameModel();
		map_view = view.GetMapView();
		map_model = map_view.GetMapModel();
		ctrl_grid = new CellCtrl[map_model.GetLinesAmount()][map_model.GetColumnsAmount()];
		for(int j=0; j<map_model.GetColumnsAmount(); j++) {
			for(int i=0; i<map_model.GetLinesAmount(); i++) {
				ctrl_grid[i][j] = new CellCtrl(ctrl,map_view.GetGrid()[i][j]);
				ctrl_grid[i][j].start();
			}
		}
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	
	public GameView GetGameView() {
		return view;
	}
	
	public MapModel GetMapModel() {
		return map_model;
	}
	
	public MapView GetMapView() {
		return map_view;
	}
	
	public Point GetCellFromClick(Point pos) {
		Point coord;
		//click position
		int pos_x = pos.x;
		int pos_y = pos.y;
		
		coord = map_model.GetCoordFromClick(pos_x, pos_y);
		
		return coord;
	}
	
	public CellModel GetClickedCell() {
		return current_cell;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		//first we wanna get the clicked cell's coordinates
		//Point mouse_pos = e.getLocationOnScreen();
		int mouse_x = e.getX();
		int mouse_y = e.getY();
		if(mouse_x > map_model.GetWidth()) {
			return;
		}
		Point mouse_pos = new Point(mouse_x, mouse_y);
		
		Point cell = GetCellFromClick(mouse_pos);
		
		if(map_model.CellIsValid(cell)) {
			if(old_click != null) {
				old_click.UnClick();
			}
			new_click = ctrl_grid[cell.x][cell.y];
			new_click.OnClick();
			old_click = new_click;
			
			current_cell = new_click.GetCellModel();
			map_model.SetCurrentCell(current_cell);
		}
		
		Point pos = map_model.GetPosFromCoord(cell.x,cell.y);
		
		CellModel clicked_cell = map_model.GetCellFromCoord(cell.x, cell.y);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { }
	
	@Override
	public void mouseReleased(MouseEvent e) { }
	
	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }
}