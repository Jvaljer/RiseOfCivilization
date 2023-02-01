package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
	private MapModel model;
	private int width;
	private int height;
	public MapView(MapModel M) {
		model = M;
		width = model.GetColumnsAmount();
		height = model.GetLinesAmount();
		//must implement JPanel's instantiation
	}
	
	public MapModel GetMapModel() {
		return model;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
	
	public void DrawMap(Graphics G) {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				//must draw the [i][j] Cell as an hexagon
			}
		}
	}
}