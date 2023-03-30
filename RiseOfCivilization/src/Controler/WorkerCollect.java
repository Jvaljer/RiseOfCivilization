package Controler;

import java.awt.Point;
import Types.*;
import Model.*;
import java.util.*;

public class WorkerCollect extends Thread{
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static Point dst_coord;
	private int worker_id;
	private int th_id;
	
	public WorkerCollect(GameCtrl GC,WorkerModel W, Point pts) {
		if(worker==null) {
			System.out.println("worker is well null yet");
		} else {
			System.out.println("creation of THread, worker shall be null : "+worker.ID);
		}
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
		System.out.println("now worker isn't null anymore ? : "+worker.ID);
		dst_coord = pts;
		worker.setNextcoord(dst_coord);
		worker_id = worker.ID;
	}
	
	@Override
	public void run() {
		ctrl.nb_th++;
		th_id = ctrl.nb_th;
		worker.occupied();
		worker.moving();
		while(dst_coord.x != worker.getcoordX() || dst_coord.y != worker.getcoordY()) {
			try {
				System.out.println("for thread n°"+th_id+" worker moving is n°"+worker.ID);
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(), dst_coord);
				Point nxt = path.get(1);
				worker.setNextcoord(nxt);
				sleep(480);
				worker.MoveTo(nxt.x, nxt.y);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		worker.stopMoving();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(map.CellIsOccupiedByBuilding(map.GetCellFromCoord(dst_coord.x, dst_coord.y))) {
			ctrl.GetGameModel().Collect(worker,map.GetBuildingFromCoord(dst_coord));
		} else {
			ctrl.GetGameModel().Harvest(worker,map.GetCellFromCoord(dst_coord.x, dst_coord.y));
		}
		worker.Free();
		ctrl.nb_th--;
	}
}
