package Controler;

import Model.MapModel;
import java.awt.*;
import java.util.ArrayList;

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
		Point dst = building.GetPos();
		while(worker.getcoordX()!=dst.x && worker.getcoordY()!=dst.y) {
			try {
				worker.occupied();
				worker.moving();
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(), dst);
				Point nxt = path.get(1);
				worker.setNextcoord(nxt);
				sleep(480);
				worker.MoveTo(nxt.x, nxt.y);
				worker.stopMoving();
				worker.Free();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		worker.occupied();
		try {
			sleep(2500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(ctrl.GetGameModel().PlayerCanUpgradeBuilding(building)) {
			building.LevelUp();
		}
		worker.Free();
		
	}
}
