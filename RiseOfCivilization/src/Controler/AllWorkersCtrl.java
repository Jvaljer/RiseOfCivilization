package Controler;

import Model.MapModel;
import java.util.*;
import Model.WorkerModel;
import Threads.WorkerCtrl;

public class AllWorkersCtrl extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	private ArrayList<WorkerModel> workers;
	private int old_len;
	
	public AllWorkersCtrl(GameCtrl GC) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		workers = ctrl.GetGameModel().GetWorkerModel();
		int len = workers.size();
		for(int i=0; i<len; i++) {
			(new WorkerCtrl(ctrl,workers.get(i))).start();
		}
		old_len = workers.size();
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
