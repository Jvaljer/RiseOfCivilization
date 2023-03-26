package Controler;

import Model.MapModel;
import Model.WorkerModel;
import Model.BuildingModel;

public class BuildingUpgrade extends Thread{
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static BuildingModel building;
	
	public BuildingUpgrade(GameCtrl GC, WorkerModel WM, BuildingModel BM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
		building = BM;
	}
	
	@Override
	public void run() {
		//must implement
	}
}
