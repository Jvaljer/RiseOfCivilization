package Threads;

import Controler.GameCtrl;
import Model.BuildingModel;
import Model.InventoryModel;
import Model.MapModel;
import Model.WorkerModel;
import View.WorkerView;
import Types.Resource;

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
		building.Occupy();
		if(ctrl.GetGameModel().PlayerCanTrain(building)) {
			InventoryModel inv = ctrl.GetGameModel().getInventoryModel();
			switch (building.getId()) {
				case LumberCamp:
					inv.remove(Resource.Gold, 75);
					break;
				case MinerCamp:
					inv.remove(Resource.Gold, 75);
					break;
				case QuarrymanCamp:
					inv.remove(Resource.Gold, 100);
					break;
				case Barrack:
					inv.remove(Resource.Gold, 150);
					break;
				default:
					break;
			}
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
		ctrl.GetGameView().getCityInfoView().update();
		building.Free();
	}
}
