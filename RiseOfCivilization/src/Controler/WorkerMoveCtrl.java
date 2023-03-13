package Controler;

import java.awt.Point;

import Model.GameModel;
import Model.WorkerModel;
import View.WorkerView;

public class WorkerMoveCtrl extends Thread {
	private GameModel model;
	private WorkerModel worker;
	private WorkerView workerView;
	private Point destCord;

	public WorkerMoveCtrl(GameModel m, WorkerModel w, WorkerView wv, Point p) {
		this.model = m;
		this.worker = w;
		this.workerView = wv;
		this.destCord = p;
	}

	public void run()
	{
		this.worker.isOccupied();
		while (this.worker.getPos() != this.destCord)
		{
			try 
			{
				Point nextCord = this.model.GetMapModel().GetShortestPath(this.worker.getPos(),this.destCord).get(0);
				Thread.sleep(2400);
				this.worker.MoveTo(nextCord.x, nextCord.y);
			} catch (Exception e) {
				System.out.println("Error in Move Worker");
				e.printStackTrace();
			}
		}
		this.worker.isFree();
	}

}
