package Controler;

import Model.CellModel;
import Model.MapModel;
import Model.WorkerModel;
import java.awt.*;
import java.util.ArrayList;

public class WorkerDrop extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	
	public WorkerDrop(GameCtrl GC, WorkerModel WM) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
	}
	
	@Override
	public void run() {
		Point town_hall = map.GetCityOriginCoord();
		worker.occupied();
		worker.moving();
		//first we want the worker to move to the town hall.
		while (this.worker.getcoordX() != town_hall.x || this.worker.getcoordY() != town_hall.y){
			try {
				ArrayList<Point> path = map.GetShortestPath(this.worker.getPos(),town_hall);
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
		
		worker.stopMoving();
		try {
			Thread.sleep(1250);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("player drops inventory");
		ctrl.GetGameModel().WorkerDropsInventory(worker);
		worker.Free();
	}
}
