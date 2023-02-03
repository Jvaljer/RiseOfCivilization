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
	public MapView(MapModel M) {
		map_model = M;
		
		lines = map_model.GetLinesAmount();
		columns = map_model.GetColumnsAmount();
		
		width = map_model.GetWidth();
		height = map_model.GetHeight();
		setPreferredSize(new Dimension(width,height));
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
		
		int w = (int) (Math.sqrt(3) * s);
		
		int x0 = map_model.GetOriginCoord().x;
		int y0 = map_model.GetOriginCoord().y;
		
		int w_gap = s + s/2;
		int h_gap = (int) (Math.sqrt(3) * s);
		int gap;
		Point[] pts = new Point[7];
		int[] x = new int[7];
		int[] y = new int[7];
		
		for(int j=0; j<columns; j++) {
			for(int i=0; i<lines; i++) {
				if(i%2==0) {
					gap = 0;
				} else {
					gap = w/2;
				}
				int xi = x0 + i*w_gap;
				int yi = y0 + j*h_gap - gap;
				
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
		
	}
}