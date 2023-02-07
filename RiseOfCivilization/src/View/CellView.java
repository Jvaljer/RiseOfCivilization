package View;

import Model.*;
import java.awt.*;

public class CellView {
	private CellModel model;
	private int abs;
	private int ord;
	
	public CellView(CellModel M) {
		model = M;
		abs = model.GetX();
		ord = model.GetY();
		//must figure out how to define this view properly.
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
		
		G.drawPolyline(x, y, pts.length);
	}
}