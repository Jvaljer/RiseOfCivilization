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
		System.out.println("Worker is dropping his inventory");
		//first we want the worker to move to the town hall.
		while(town_hall.x != worker.getcoordX() || town_hall.y != worker.getcoordY()) {
			try {
				worker.occupied();
				worker.moving();
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(), town_hall);
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
			Thread.sleep(1250);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("player drops inventory");
		ctrl.GetGameModel().WorkerDropsInventory(worker);
		worker.Free();
	}
}
