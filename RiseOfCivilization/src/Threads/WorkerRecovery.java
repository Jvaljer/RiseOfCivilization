package Threads;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerRecovery extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	
	public WorkerRecovery(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		worker = WM;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void run() {
		int recovers = 0;
		while(!worker.IsDead()) {
			//if the worker is alive we simply wanna make him regain the life he has lost
			if(worker.GetCurrentHealth()<worker.GetInitHealth()) {
				recovers = 1;
				try {
					this.sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				worker.Recovery();
			} else {
				recovers = 0;
			}
			
			try {
				this.sleep(1000 - (recovers*1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
