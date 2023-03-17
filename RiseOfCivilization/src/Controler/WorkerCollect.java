package Controler;

import java.awt.Point;
import Types.*;
import Model.*;

public class WorkerCollect extends Thread{
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static CellModel dst_cell;
	private static Resource resource;
	
	public WorkerCollect(GameCtrl GC,WorkerModel W, CellModel C) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
		dst_cell = C;
		resource = dst_cell.getResource();
	}
	
	@Override
	public void run() {
		worker.isMoving();
		worker.isOccupied();
		while(worker.getPos()!=dst_cell.GetCoord()) {
			try {
				Point nxt = map.GetShortestPath(worker.getPos(),dst_cell.GetCoord()).get(0);
				Thread.sleep(500);
				worker.MoveTo(nxt.x, nxt.y);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//now we wanna make the worker collect
		worker.isNotMoving();
		ctrl.GetGameModel().Harvest(worker,dst_cell);
	}
}
