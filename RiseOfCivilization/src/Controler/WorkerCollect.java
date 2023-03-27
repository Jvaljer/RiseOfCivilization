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
	
	public WorkerCollect(GameCtrl GC,WorkerModel W, Point pts) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
		dst_coord = pts;
		dst_cell = C;
		worker.setNextcoord(dst_cell.GetCoord());
	}
	
	@Override
	public void run() {
		while(dst_coord.x != worker.getcoordX() || dst_coord.y != worker.getcoordY()) {
			try {
				worker.occupied();
				worker.moving();
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(), dst_coord);
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
		System.out.println("worker is collecting resources");
		worker.occupied();
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
	}
}
