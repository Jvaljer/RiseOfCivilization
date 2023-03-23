package Controler;

import Model.MapModel;
import java.util.*;
import Model.WorkerModel;

public class WorkersCtrl extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	private ArrayList<WorkerModel> workers;
	
	public WorkersCtrl(GameCtrl GC) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void run() {
		workers = ctrl.GetGameModel().GetWorkerModel();
		
		for(int i=0; i<workers.size(); i++) {
			if(workers.get(i).InventoryIsFull()) {
				(new WorkerDrop(ctrl,workers.get(i))).start();
			}
		}
	}
}
