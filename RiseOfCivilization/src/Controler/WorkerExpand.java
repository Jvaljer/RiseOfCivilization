package Controler;

import Model.CellModel;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerExpand extends Thread {
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static CellModel dst_cell;
	
	public WorkerExpand(GameCtrl GC, WorkerModel WM, CellModel CM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
		dst_cell = CM;
	}
	
	@Override
	public void run() {
		
	}
}
