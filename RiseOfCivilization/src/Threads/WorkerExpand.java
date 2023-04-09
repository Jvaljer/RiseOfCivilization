package Threads;

import java.awt.Point;
import Types.*;
import java.util.ArrayList;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;

public class WorkerExpand extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	private Point dst_coord;
	
	public WorkerExpand(GameCtrl GC, WorkerModel WM, Point pts) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = WM;
		dst_coord = pts;
	}
	
	@Override
	public void run() {
		worker.occupied();
		worker.moving();
		while (this.worker.getcoordX() != dst_coord.x || this.worker.getcoordY() != dst_coord.y){
			try {
				ArrayList<Point> path = map.GetShortestPath(this.worker.getPos(),dst_coord);
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
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CellId cell_id = map.GetCellFromCoord(dst_coord.x, dst_coord.y).GetId();
		ctrl.GetGameModel().Expand(dst_coord);
		ctrl.GetGameView().getCityInfoView().update();
		worker.Free();
	}
}
