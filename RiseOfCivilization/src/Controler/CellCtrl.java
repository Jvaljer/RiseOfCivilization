package Controler;

import Model.*;
import View.*;

public class CellCtrl extends Thread {
	private CellModel model;
	private CellView view;
	
	public CellCtrl(CellView V) {
		view = V;
		model = view.GetCellModel();
	}
	
	public CellModel GetCellModel() {
		return model;
	}
	
	public CellView GetCellView() {
		return view;
	}
	
	@Override
	public void run() {
		//must implement
	}
}