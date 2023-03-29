package Controler;

import java.awt.Point;
import java.util.ArrayList;

import Model.GameModel;
import Model.WorkerModel;

/**
 * This class is responsible for all the interraction in the game with the movement of worker
 * it extend thread and will be lunch in the GameCtrl
 */
public class WorkerMoveCtrl extends Thread {
	private GameModel model;
	private WorkerModel worker;
	private Point destCord;

	/**
	 *  Constructor of the workerMoveCtrl
	 * 
	 * @param m The GameModel object that represent the game
	 * @param w The WorkerModel object that represent the worker that we interact with
	 * @param p Point to where we want the worker to move
	 */
	public WorkerMoveCtrl(GameModel m, WorkerModel w, Point p) {
		this.model = m;
		this.worker = w;
		this.destCord = p;
	}

	/**
	 * This is the run method that run this thread.
	 * It will move the player one cell at a time to it destination
	 */
	public void run()
	{
		this.worker.occupied();
		this.worker.moving();
		while (this.worker.getcoordX() != this.destCord.x || this.worker.getcoordY() != this.destCord.y)
		{
			try 
			{
				ArrayList<Point> path = this.model.GetMapModel().GetShortestPath(this.worker.getPos(),this.destCord);
 				Point nextCord = path.get(1);
				this.worker.setNextcoord(nextCord);
				Thread.sleep(480);
				this.worker.MoveTo(nextCord.x, nextCord.y);
			} catch (Exception e) {
				System.out.println("Error in Move Worker");
				e.printStackTrace();
			}
		}

		this.worker.stopMoving();
		this.worker.Free();
	}

}
