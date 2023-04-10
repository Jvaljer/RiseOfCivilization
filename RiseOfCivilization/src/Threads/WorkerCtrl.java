package Threads;

import Model.WorkerModel;
import Types.WorkerRole;
import Controler.GameCtrl;
import Model.MapModel;

/**
 * This classe is responsible for all the action of a worker
 * It extend Thread and will lunch 
 * recovery thread
 * WorkerDrop Thread
 */
public class WorkerCtrl extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	private WorkerRecovery recovery;
	
	/**
	 * Constructor for this class
	 * @param GC The GameCtrl that gather all the controller
	 * @param WM The worker that this Thread controlle
	 */
	public WorkerCtrl(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		worker = WM;
		map = ctrl.GetGameModel().GetMapModel();
		if(worker.GetRole()==WorkerRole.Knight) {
			recovery = (new WorkerRecovery(ctrl,worker));
		}
	}
	
	/**
	* This is the run method that run this thread.
	* It will control the recovery of life of the worker
	* It also make the worker goes back to the city and drop it's inventory if the worker inventory is full
	*/
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
		ctrl.GetGameView().getCityInfoView().update();
	}
}
