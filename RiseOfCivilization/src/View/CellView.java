package View;

import Model.*;
import java.awt.*;
import Types.*;

/**
 * View class of a Cell, containing and defining all the needed informations to 
 * draw properly the cell as a regular Hexagon, who's color gonna evolve throughout
 * the game.
 * @author abel
 */
public class CellView {
	private CellModel model;
	private int abs;
	private int ord;
	private CellId id;
	private boolean clicked;
	private Point[] border_pts;
	
	public CellView(CellModel M) {
		model = M;
		abs = model.GetX();
		ord = model.GetY();
		id = model.GetId();
		clicked = false;
	}
	
	public int GetAbs() {
		return abs;
	}
	
	public int GetOrd() {
		return ord;
	}
	
	public CellModel GetCellModel() {
		return model;
	}
	
	public Color GetColorFromId() {
		switch (id) {
			case Plain :
				return (new Color(255, 204, 102));
			case Forest :
				return (new Color(0, 153, 0));
			case Mountain :
				return (new Color(150, 150, 150));
			case Iron_Deposit :
				return (new Color(169, 84, 69));
			case City : 
				return (new Color(165, 110, 20));
			default :
				return (new Color(0,0,0));
		}
	}
	
	public void Click() {
		clicked = !clicked;
	}
	
	public void DrawClick(Graphics G) {
		int len = border_pts.length;
		int[] pts_x = new int[len];
		int[] pts_y = new int[len];
		
		for(int n=0; n<len; n++) {
			pts_x[n] = border_pts[n].x;
			pts_y[n] = border_pts[n].y;
		}
		
		if(clicked) {
			G.setColor(new Color(200,0,0));
			G.fillPolygon(pts_x, pts_y, len);
		} else {
			G.setColor(GetColorFromId());
			G.fillPolygon(pts_x, pts_y, len);
		}
	}
	
	public void DrawCell(Graphics G, int s, int x0, int y0) {
		
		int w = (int) (Math.sqrt(3) * s);
		
		int w_gap = s + s/2;
		int h_gap = (int) (Math.sqrt(3) * s);
		
		int gap;
		
		border_pts = new Point[7];
		int[] x = new int[7];
		int[] y = new int[7];
		
		if(abs%2==0) {
			gap = 0;
		} else {
			gap = w/2;
		}
		int xi = x0 + abs*w_gap;
		int yi = y0 + ord*h_gap - gap;
		
		border_pts[0] = new Point(xi - s, yi);
		border_pts[1] = new Point(xi - (s/2), yi - (w/2));
		border_pts[2] = new Point(xi + (s/2), yi - (w/2));
		border_pts[3] = new Point(xi + s, yi);
		border_pts[4] = new Point(xi + (s/2), yi + (w/2));
		border_pts[5] = new Point(xi - (s/2), yi + (w/2));
		border_pts[6] = new Point(xi - s, yi);
		
		for(int n=0; n<border_pts.length; n++) {
			x[n] = border_pts[n].x;
			y[n] = border_pts[n].y;
		}
		
		DrawClick(G);
		
		G.setColor(new Color(0,0,0));
		G.drawPolyline(x, y, border_pts.length);
	}
}