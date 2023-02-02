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
		
		setPreferredSize(new Dimension(width,height));
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
	public void DrawMap(Graphics G) {
		int size = map_model.GetCellSize();
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				//must draw the [i][j] Cell as an hexagon
				int[] pts_x = new int[6];
				int[] pts_y = new int[6];
				
				//calculating the point's coordinates
				int originX = map_model.GetOriginCoord().x;
				int originY = map_model.GetOriginCoord().y;
				
				int cell_gap; 
				if(i%2!=0) {
					cell_gap = 1;
				} else {
					cell_gap = 0;
				}
				int cell_width_dist = 3/4 * 3/2 * size;
				int cell_height_dist = (int) Math.sqrt(3.) * size;
				
				
				G.drawPolyline(pts_x, pts_y, 6);
			}
		}
	}
}