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
		view.addMouseListener(this);
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
	
	public Point GetCellFromClick(Point pos) {
		Point coord = new Point(0,0);
		int pos_x = pos.x;
		int pos_y = pos.y;
		
		int size = map_model.GetCellSize();
		int w_gap = size + (size/2);
		int h_gap = (int) (Math.sqrt(3) * size);
		int gap;
		
		int x0 = map_model.GetOriginCoord().x;
		int y0 = map_model.GetOriginCoord().y;
		
		int x1 = 0;
		int y1 = 0;
		int x2 = 1;
		int y2 = 1;
		
		int cpt = 0;
		boolean OrdNotFound = true;
		boolean AbsNotFound = true;
		//first we wanna get an estimation of the possible coord of the click :
			// (i,j) is the highest possibility
			// (i, j-1) (i-1, j) are the mid possibilities
			// (i-1, j-1) is the lowest possibility
		
		for(int j=1; j<map_model.GetColumnsAmount(); j++) {
			for(int i=1; i<map_model.GetLinesAmount(); i++) {
				if(i%2==0) {
					gap = h_gap / 2;
				} else {
					gap = 0;
				}
				
				if( (pos_y < y0 + (j * h_gap) - gap) && (pos_y > y0 + ((j-1) * h_gap) - gap) && OrdNotFound) {
					y1 = j-1;
					y2 = j;
					OrdNotFound = false;
				}
				
				if( (pos_x < x0 + (i * w_gap)) && (pos_x > x0 + ((i-1) * w_gap)) && AbsNotFound ) {
					x1 = i-1;
					x2 = i;
					AbsNotFound = false;
				}
			}
		}
		
		//now that we have our 4 possibilities, let's check on them all if the point is in it or not.
				//thanks to the y-axis, we can easily choose 2 of them.
		System.out.println("possibilities : " + x1 + "," + y1 + " & " + x2 + "," + y2);
		return coord;
	}
	
	@Override
	public void run() {
		//must implement
		System.out.println("MapCtrl running");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		//first we wanna get the clicked cell's coordinates
		//Point mouse_pos = e.getLocationOnScreen();
		int mouse_x = e.getX();
		int mouse_y = e.getY();
		Point mouse_pos = new Point(mouse_x, mouse_y);
		System.out.println("clicked on : " + mouse_pos);
		Point cell = GetCellFromClick(mouse_pos);
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