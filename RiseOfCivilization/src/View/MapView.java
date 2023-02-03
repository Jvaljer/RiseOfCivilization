package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
	private MapModel map_model;
	private int width;
	private int height;
	public MapView(MapModel M) {
		map_model = M;
		
		width = map_model.GetWidth();
		height = map_model.GetHeight();
		
		setPreferredSize(new Dimension(height,width));
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
		int size = map_model.GetCellSize();
		//getting the point's coordinates
		int originX = map_model.GetOriginCoord().x;
		int originY = map_model.GetOriginCoord().y;
		
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				//must draw the [i][j] Cell as an hexagon
				int[] pts_x = new int[6];
				int[] pts_y = new int[6];
				
				int cell_gap; 
				if(i%2!=0) {
					cell_gap = 1;
				} else {
					cell_gap = 0;
				}
				int cell_width_dist = 3/4 * 3/2 * size; 
				int cell_height = (int) Math.sqrt(3.) * size; // sqrt(3) * size
				
				int pos_x = originX + i*cell_width_dist;
				int pos_y = originY + j*cell_height - (cell_gap*cell_height/2);
				
				Point[] pts = new Point[6];
				pts[0] = new Point(pos_x - size, pos_y);
				pts[1] = new Point(pos_x - (size/2), pos_y - (cell_height/2));
				pts[2] = new Point(pos_x + (size/2), pos_y - (cell_height/2));
				pts[3] = new Point(pos_x + size, pos_y);
				pts[4] = new Point(pos_x + (size/2), pos_y + (cell_height/2));
				pts[5] = new Point(pos_x - (size/2), pos_y + (cell_height/2));
				
				for(int n=0; n<pts.length; n++) {
					pts_x[n] = pts[n].x;
					pts_y[n] = pts[n].y;
				}
				
				G.drawPolyline(pts_x, pts_y, 6);
			}
		}
	}
}