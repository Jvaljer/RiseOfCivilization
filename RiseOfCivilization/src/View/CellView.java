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
	
	public void DrawCell(Graphics G) {
		//idea : 
			//gonna calculated the 6 peaks of the cell
			//and then draw the polygon's line corresponding to these
		
		//do we really need to draw each cell ? can't we just draw all in 'MapView' ???
	}
}