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
		while (this.worker.getcoordX() != dst.x || this.worker.getcoordY() != dst.y){
			try {
				ArrayList<Point> path = map.GetShortestPath(this.worker.getPos(),dst);
 				Point nxt_coord = path.get(1);
				this.worker.setNextcoord(nxt_coord);
				Point cord_src = map.GetPosFromCoord(worker.getcoordX(), worker.getcoordY());
		        Point cord_dst = map.GetPosFromCoord(nxt_coord.x, nxt_coord.y);
		        int x_src = cord_src.x - worker.getWidth()/2;
		        int y_src = cord_src.y - worker.getHeight()/2;
		        int x_dst = cord_dst.x - worker.getWidth()/2;
		        int y_dst = cord_dst.y - worker.getHeight()/2;
			    for(int i = 1; i <= 24; i++){
			        int x = (int) ((x_dst - x_src) * i/ 24) + x_src;
			        int y = (int) ((y_dst - y_src) * i/ 24) + y_src;
			        this.worker.setPosWhileMoving(x, y);
					Thread.sleep(1000/48);
			    }
				this.worker.MoveTo(nxt_coord.x, nxt_coord.y);
			} catch (Exception e) {
				System.out.println("Error in Move Worker");
				e.printStackTrace();
			}
		}
		
		worker.occupied();
		if(ctrl.GetGameModel().PlayerCanUpgradeBuilding(building)) {
			try {
				sleep(2500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			building.LevelUp();
		}
		ctrl.GetGameView().getCityInfoView().update();
		worker.Free();
		
	}
}
