package Controler;

import java.awt.Point;
import Types.*;
import Model.*;
import java.util.*;

public class WorkerCollect extends Thread{
	private static GameCtrl ctrl;
	private static MapModel map;
	private static WorkerModel worker;
	private static CellModel dst_cell;
	
	public WorkerCollect(GameCtrl GC,WorkerModel W, CellModel C) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
		worker = W;
		dst_cell = C;
	}
	
	@Override
	public void run() {
		worker.isMoving();
		worker.isOccupied();
		System.out.println("path from original pos to destination :");
		System.out.println("       " + map.GetShortestPath(worker.getPos(),dst_cell.GetCoord()));
		while((worker.getPos().x != dst_cell.GetCoord().x) && (worker.getPos().y != dst_cell.GetCoord().y)) {
			try {
				ArrayList<Point> path = map.GetShortestPath(worker.getPos(),dst_cell.GetCoord());
				Point nxt;
				if(path.size() > 1) {
					nxt = path.get(1);
				} else {
					nxt = path.get(0);
				}
				Thread.sleep(500);
				worker.MoveTo(nxt.x, nxt.y);
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//now we wanna make the worker collect
		worker.isNotMoving();
		ctrl.GetGameModel().Harvest(worker,dst_cell);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		worker.isFree();
	}
}
