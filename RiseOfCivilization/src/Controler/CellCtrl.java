package Controler;

import Model.*;
import Types.CellId;
import View.*;

public class CellCtrl extends Thread {
	private CellModel cell_model;
	private CellView cell_view;
	private CellId id;
	
	public CellCtrl(CellView V) {
		cell_view = V;
		cell_model = cell_view.GetCellModel();
		id = cell_model.GetId();
	}
	
	public CellModel GetCellModel() {
		return cell_model;
	}
	
	public CellView GetCellView() {
		return cell_view;
	}
	
	public void OnClick() {
		switch (id) {
			case City :
				
			case Forest :
				
			case Plain :
				
			case None :
				
			default:
				
		}
	}
	
	public void CellBehaviour(CellId id) {
		switch (id) {
			case City :
			case Forest :
			case Plain :
			case None :
			default:
		}
	}
	
	@Override
	public void run() {
		//must implement
		System.out.println("CellCtrl is running");
		while(true) {
			//passive behaviours
			CellBehaviour(cell_model.GetId());
			
			//on click behaviour of the cell
			OnClick();
			
			try {
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}