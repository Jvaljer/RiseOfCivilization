package Controler;

import Model.BuildingModel;
import Model.MapModel;
import Model.WorkerModel;
import View.WorkerView;

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
		int old_len = ctrl.GetGameModel().GetWorkerModel().size();
		building.Train();
		int new_len = ctrl.GetGameModel().GetWorkerModel().size();
		
		if(old_len < new_len) {
			ctrl.GetGameView().getWorkerView().add(new WorkerView(ctrl.GetGameView(),ctrl.GetGameModel().GetWorkerModel().get(old_len)));
		}
	}
}
