package Controler;

import Model.*;
import View.*;
import java.awt.event.*;

/** 
 * This class implements MouseListener in order to collect clics & act depending of their position on the map
 *   it also extends Thread to create one which will dict real-time behaviours of it.
 * @author Abel
 */
public class MapCtrl extends Thread implements MouseListener {
	private GameModel model;
	private GameView view;
	private MapModel map_model;
	private MapView map_view;
	private CellCtrl[][] ctrl_grid;
	
	public MapCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
		map_view = view.GetMapView();
		map_model = map_view.GetMapModel();
		ctrl_grid = new CellCtrl[map_model.GetColumnsAmount()][map_model.GetLinesAmount()];
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
	
	@Override
	public void run() {
		//must implement
		System.out.println("MapCtrl running");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		//first we wanna get the clicked cell's coordinates
		int mouseX = e.getX();
		int mouseY = e.getY();
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