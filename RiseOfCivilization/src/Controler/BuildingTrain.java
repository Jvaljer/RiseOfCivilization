package Controler;

import Model.BuildingModel;
import Model.MapModel;
import Model.WorkerModel;

public class BuildingTrain extends Thread {
	private static GameCtrl ctrl;
	private static MapModel map;
	private static BuildingModel building;
	
	public BuildingTrain(GameCtrl GC, BuildingModel BM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		building = BM;
	}
	
	@Override
	public void run() {
		try {
			sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		building.Train();
	}
}
