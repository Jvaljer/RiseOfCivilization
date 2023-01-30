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
	
	@Override
	public void run() {
		//must implement
	}
}