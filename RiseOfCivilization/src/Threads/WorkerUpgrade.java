package Threads;

import java.awt.Point;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;

/**
 * This is the Thread that's responsible of the upgrade of a player
 */
public class WorkerUpgrade extends Thread{
	// The GameCtrl that gather all information about all controller
	private GameCtrl ctrl;
	// The mapModel
	private MapModel map;
	// the worker that will be upgrade
	private WorkerModel worker;
	
	/**
	 * Constructor of this class
	 * @param GC GameController
	 * @param WM WorkerModel of the worker we want to upgrade
	 */
	public WorkerUpgrade(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
	}
	
	/**
	 * This is the run Method of this Thread
	 * It make the worker occupied for 3s then upgrade it's level
	 */
	@Override
	public void run() {
		worker.occupied();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(ctrl.GetGameModel().PlayerCanUpgradeWorker(worker)) {
			worker.LevelUp();
		}
		ctrl.GetGameView().getCityInfoView().update();
		worker.Free();
	}
}
