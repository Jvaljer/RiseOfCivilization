package Controler;

import Model.WorkerModel;
import Model.MapModel;

public class WorkerCtrl extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	
	public WorkerCtrl(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		worker = WM;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void run() {
		System.out.println("starting a worker control");
		while(!worker.IsDead()) {
			//if the worker is alive then we simply wanna check if it has to drop his inventory
			if(worker.InventoryIsFull()) {
				System.out.println("INVENTORY IS FULL");
				(new WorkerDrop(ctrl,worker)).start();
			}
		}
		System.out.println("we have a dead worker");
		//if the worker is dead then delete it from all lists
		ctrl.GetGameView().RemoveWorker(worker);
		ctrl.GetGameModel().RemoveWorker(worker);
	}
}
