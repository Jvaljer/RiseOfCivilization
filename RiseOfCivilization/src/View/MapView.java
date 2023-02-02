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
		int s = map_model.GetCellSize();
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				//must draw the [i][j] Cell as an hexagon
				int[] pts_x = new int[6];
				int[] pts_y = new int[6];
				pts_x[0] = -s;
				pts_y[0] = 0;
				
				pts_x[1] = -(s/2);
				pts_y[1] = (int) ((Math.sqrt(3)*s) /2);
				
				pts_x[2] = s/2;
				pts_y[2] = (int) ((Math.sqrt(3)*s) /2);
				
				pts_x[3] = s;
				pts_y[3] = 0;
				
				pts_x[4] = s/2;
				pts_y[4] = -(int) ((Math.sqrt(3)*s) /2);
				
				pts_x[5] = -(s/2);
				pts_y[5] = -(int) ((Math.sqrt(3)*s) /2);
				G.drawPolyline(pts_x, pts_y, 6);
			}
		}
	}
}