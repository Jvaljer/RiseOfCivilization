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
		worker.setDstCord(dst_cell.GetCoord());
	}
	
	@Override
	public void run() {
		worker.moving();
		worker.occupied();
		System.out.println("path from " + worker.getPos() + " to " + dst_cell.GetCoord() + " :");
		ArrayList<Point> shortest = map.GetShortestPath(worker.getPos(),dst_cell.GetCoord());
		Point dst_coord = dst_cell.GetCoord();
		for(int i=0; i<shortest.size(); i++) {
			System.out.println(shortest.get(i));
		}
		while (this.worker.getPos() != dst_coord){
			try {
				Point nextCord = map.GetShortestPath(this.worker.getPos(),dst_coord).get(0);
				Thread.sleep(500);
				this.worker.MoveTo(nextCord.x, nextCord.y);
			} catch (Exception e) {
				System.out.println("Error in Move Worker");
				e.printStackTrace();
			}
		}
		
		//now we wanna make the worker collect
		worker.stopMoving();
		ctrl.GetGameModel().Harvest(worker,dst_cell);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		worker.Free();
	}
}
