package Threads;

import Model.WorkerModel;
import Types.WorkerRole;
import Controler.GameCtrl;
import Model.MapModel;

public class WorkerCtrl extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	private WorkerRecovery recovery;
	
	public WorkerCtrl(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		worker = WM;
		map = ctrl.GetGameModel().GetMapModel();
		if(worker.GetRole()==WorkerRole.Knight) {
			recovery = (new WorkerRecovery(ctrl,worker));
		}
	}
	
	@Override
	public void run() {
		if(recovery!=null) {
			recovery.start();
		}
		while(!worker.IsDead()) {
			//if the worker is alive then we simply wanna check if it has to drop his inventory
			if(worker.InventoryIsFull() && !worker.GetMoving()) {
				(new WorkerDrop(ctrl,worker)).start();
			}
		}
		if(recovery!=null) {
			recovery.stop();
		}
		//if the worker is dead then delete it from all lists
		ctrl.GetGameView().RemoveWorker(worker);
		ctrl.GetGameModel().RemoveWorker(worker);
	}
}
