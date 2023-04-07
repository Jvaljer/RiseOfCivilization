package Controler;

import Model.MapModel;
import java.util.*;
import Model.WorkerModel;

public class WorkersCtrl extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	private ArrayList<WorkerModel> workers;
	private int old_len;
	
	public WorkersCtrl(GameCtrl GC) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		workers = ctrl.GetGameModel().GetWorkerModel();
		old_len = workers.size();
		for(int i=0; i<old_len; i++) {
			if(workers.get(i).InventoryIsFull()) {
				(new WorkerDrop(ctrl,workers.get(i))).start();
				(new WorkerRecovery(ctrl,workers.get(i))).start();
				(new WorkerCtrl(ctrl,workers.get(i))).start();
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			int new_len = workers.size();
			if(old_len < new_len) {
				(new WorkerCtrl(ctrl,workers.get(old_len))).start();
			}
		}
	}
}
