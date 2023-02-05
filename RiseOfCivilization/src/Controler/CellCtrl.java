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
	
	@Override
	public void run() {
		//must implement
		System.out.println("CellCtrl is running");
		while(true) {
			id = cell_model.GetId();
			if(id==CellId.City) {
				//real-time behaviour if City Cell
			} else if(id==CellId.Forest) {
				//real-time behaviour if Forest Cell
			} else if(id==CellId.Plain) {
				//real-time behaviour if Plain Cell
			} else if(id==CellId.None) {
				//real-time behaviour if None id 
			} else {
				//real-time behaviour else
			}
			
			//on click behaviour of the cell
			OnClick();
		}
	}
}