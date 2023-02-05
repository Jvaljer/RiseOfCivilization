package Controler;

import Model.*;
import View.*;
import java.awt.event.*;
import java.awt.*;

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
	
	public Point GetCellFromClick(int X, int Y) {
		Point origin = map_model.GetOriginCoord();
		Point cell = new Point(0,0);
		
		int size = map_model.GetCellSize();
		int w_gap = size + (size/2);
		int h_gap = (int) (Math.sqrt(3) * size);
		int gap;
		
		int originX = origin.x;
		int originY = origin.y;
		
		int fstX = 0;
		int sndX = 1;
		int fstY = 0;
		int sndY = 1;
		
		//first we wanna get the maximum
		for(int j=0; j<map_model.GetColumnsAmount(); j++) {
			for(int i=0; i<map_model.GetLinesAmount(); i++) {
				if(i%2==0) {
					gap = 0;
				} else {
					gap = w_gap / 2;
				}
				
				if(Y < originY + (j* h_gap) - gap) {
					fstY = j-1;
					sndY = j;
				}
				
				if(X < originX + (i* w_gap) ) {
					fstX = i-1;
					sndX = i;
				}
			}
		}
		//now we wanna test if the Y is closer to fstY or sndY 
		int finalX;
		int finalY;
		
		int MidY;
		int MidX;
		
		if(fstX%2!=0) {
			gap = w_gap / 2;
		} else {
			gap = 0;
		}
		
		MidY = ( (originY + (sndY* h_gap) - gap) - (originY + (sndY* h_gap) - gap) )/ 2;
		if((originY + (sndY* h_gap) - gap) - Y > MidY) {
			cell.y = fstY;
		} else {
			cell.y = sndY;
		}

		//then we wanna test if th X is closer to fstX or sndX
		MidX = ( (originX + (sndX* w_gap)) - (originX + (fstX* w_gap)) ) / 2;
		
		return cell;
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
		
		Point cell = GetCellFromClick(mouseX,mouseY);
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