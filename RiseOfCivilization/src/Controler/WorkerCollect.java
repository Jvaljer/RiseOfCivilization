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
	}
	
	@Override
	public void run() {
		while(dst_coord.x != worker.getCordX() || dst_coord.y != worker.getCordY()) {
			try {
				worker.occupied();
				worker.moving();
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(), dst_coord);
				Point nxt = path.get(1);
				worker.setNextCord(nxt);
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
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctrl.GetGameModel().Harvest(worker,map.GetCellFromCoord(dst_coord.x, dst_coord.y));
		worker.Free();
	}
}
