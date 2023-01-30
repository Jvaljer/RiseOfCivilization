package View;

import Model.*;

public class CellView {
	private CellModel model;
	private int abs;
	private int ord;
	
	public CellView(CellModel M) {
		model = M;
		abs = model.GetAbs();
		ord = model.GetOrd();
		//must figure out how to define this view properly.
	}
	
	public int GetAbs() {
		return abs;
	}
	
	public int GetOrd() {
		return ord;
	}
	
	public void DrawCell() {
		//mustImplement
	}
}