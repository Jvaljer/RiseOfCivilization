package Controler;

import java.awt.Point;
import java.util.ArrayList;

import Model.GameModel;
import Model.WorkerModel;
import View.WorkerView;

public class WorkerMoveCtrl extends Thread {
	private GameModel model;
	private WorkerModel worker;
	private Point destCord;

	public WorkerMoveCtrl(GameModel m, WorkerModel w, Point p) {
		this.model = m;
		this.worker = w;
		this.destCord = p;
		this.worker.setDstCord(p);
	}

	public void run()
	{
		while (this.worker.getCordX() != this.destCord.x || this.worker.getCordY() != this.destCord.y)
		{
			try 
			{
				this.worker.moving();
				ArrayList<Point> path = this.model.GetMapModel().GetShortestPath(this.worker.getPos(),this.destCord);
 				Point nextCord = path.get(1);
				Thread.sleep(480);
				this.worker.MoveTo(nextCord.x, nextCord.y);
				this.worker.stopMoving();
			} catch (Exception e) {
				System.out.println("Error in Move Worker");
				e.printStackTrace();
			}
		}
	}

}
