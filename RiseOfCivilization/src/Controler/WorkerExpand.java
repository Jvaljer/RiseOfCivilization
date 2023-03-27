package Controler;

import java.awt.Point;
import Types.*;
import java.util.ArrayList;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerExpand extends Thread {
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static Point dst_coord;
	
	public WorkerExpand(GameCtrl GC, WorkerModel WM, Point pts) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
		dst_coord = pts;
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
		worker.occupied();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CellId cell_id = map.GetCellFromCoord(dst_coord.x, dst_coord.y).GetId();
		map.GetCellFromCoord(dst_coord.x, dst_coord.y).TurnToCity();
		worker.Free();
	}
}
