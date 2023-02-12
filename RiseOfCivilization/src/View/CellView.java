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
	
	public CellView(CellModel M) {
		model = M;
		abs = model.GetX();
		ord = model.GetY();
		id = model.GetId();
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
			case City : 
				return (new Color(184, 184, 148));
			case None : 
				return (new Color(255, 255, 255));
			default :
				return (new Color(0,0,0));
		}
	}
	
	public void DrawCell(Graphics G, int s, int x0, int y0) {
		
		int w = (int) (Math.sqrt(3) * s);
		
		int w_gap = s + s/2;
		int h_gap = (int) (Math.sqrt(3) * s);
		
		int gap;
		
		Point[] pts = new Point[7];
		int[] x = new int[7];
		int[] y = new int[7];
		
		if(abs%2==0) {
			gap = 0;
		} else {
			gap = w/2;
		}
		int xi = x0 + abs*w_gap;
		int yi = y0 + ord*h_gap - gap;
		
		pts[0] = new Point(xi - s, yi);
		pts[1] = new Point(xi - (s/2), yi - (w/2));
		pts[2] = new Point(xi + (s/2), yi - (w/2));
		pts[3] = new Point(xi + s, yi);
		pts[4] = new Point(xi + (s/2), yi + (w/2));
		pts[5] = new Point(xi - (s/2), yi + (w/2));
		pts[6] = new Point(xi - s, yi);
		
		for(int n=0; n<pts.length; n++) {
			x[n] = pts[n].x;
			y[n] = pts[n].y;
		}
		
		G.setColor(GetColorFromId());
		G.fillPolygon(x, y, pts.length);
		G.setColor(new Color(0,0,0));
		G.drawPolyline(x, y, pts.length);
	}
}