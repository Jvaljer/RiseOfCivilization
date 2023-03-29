package Controler;

import java.awt.Point;
import Model.CellModel;
import java.util.ArrayList;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerBuild extends Thread {
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static Point dst_coord;
	
	public WorkerBuild(GameCtrl GC,WorkerModel W, Point pts) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
		dst_coord = pts;
	}
	
	@Override
	public void run() {
		worker.occupied();
		worker.moving();
		while(dst_coord.x != worker.getcoordX() || dst_coord.y != worker.getcoordY()) {
			try {
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
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int old_len = ctrl.GetGameModel().GetBuildingList().size();
		CellModel cell = map.GetCellFromCoord(dst_coord.x, dst_coord.y);
		ctrl.GetGameModel().Build(cell);
		int new_len = ctrl.GetGameModel().GetBuildingList().size();
		if(old_len < new_len) {
			ctrl.GetGameView().AddBuildingView(ctrl.GetGameModel().GetBuildingList().get(old_len));
		}
		worker.Free();
	}
}
