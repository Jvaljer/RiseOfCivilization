package Threads;

import java.awt.Point;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerUpgrade extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	
	public WorkerUpgrade(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
	}
	
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
