package Controler;

import java.awt.Point;

import Model.MapModel;
import Model.WorkerModel;

public class WorkerUpgrade extends Thread{
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	
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
		worker.Free();
	}
}
