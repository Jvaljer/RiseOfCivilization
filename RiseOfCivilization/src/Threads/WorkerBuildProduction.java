package Threads;

import java.awt.Point;
import Types.CellId;
import Model.CellModel;
import java.util.ArrayList;

import Controler.GameCtrl;
import Model.MapModel;
import Model.WorkerModel;


/**
 * This class is part of the Build Action Controller.
 * It extends Thread and is called by ActionBuild.
 * 
 * @author Abel
 */
public class WorkerBuildProduction extends Thread {
	private GameCtrl ctrl;
	private MapModel map;
	private WorkerModel worker;
	private Point dst_coord;
	
	/**
     * Default Constructor of ActionBuild.
     *
     * @param GC  the main controller
	 * @param W   the worker model
	 * @param pts the Point of the coordinates of the production building
     */
	public WorkerBuildProduction(GameCtrl GC, WorkerModel W, Point pts) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
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
		int old_len = ctrl.GetGameModel().GetBuildingList().size();
		CellModel cell = map.GetCellFromCoord(dst_coord.x, dst_coord.y);
		ctrl.GetGameModel().BuildOnResourceCell(cell);
		int new_len = ctrl.GetGameModel().GetBuildingList().size();
		if(old_len < new_len) {
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ctrl.GetGameView().AddBuildingView(ctrl.GetGameModel().GetBuildingList().get(old_len));
		}
		ctrl.GetGameView().getCityInfoView().update();
		worker.Free();
	}
}
