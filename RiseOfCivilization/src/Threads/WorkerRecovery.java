package Threads;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;

/**
 * This is the Recovery Thread it make worker recovering their life 
 */
public class WorkerRecovery extends Thread {
	// The GameCtrl that gather all information about all controller
	private GameCtrl ctrl;
	// The mapModel
	private MapModel map;
	// the worker that will be heal
	private WorkerModel worker;
	
	/**
	 * this is the constructor of this class
	 * @param GC Gamecontroller
	 * @param WM The worker that will be healed
	 */
	public WorkerRecovery(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		worker = WM;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * This is the run method that run this thread.
	 * It's running until the player die
	 * if he don't have all his HP once every 10s he will recover 1 hp
	 */
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
