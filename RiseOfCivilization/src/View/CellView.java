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
}