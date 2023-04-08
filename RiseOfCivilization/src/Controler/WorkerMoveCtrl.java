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
				Point cord_src = this.model.GetMapModel().GetPosFromCoord(worker.getcoordX(), worker.getcoordY());
		        Point cord_dst = this.model.GetMapModel().GetPosFromCoord(nextCord.x, nextCord.y);
		        int x_src = cord_src.x - worker.getWidth()/2;
		        int y_src = cord_src.y - worker.getHeight()/2;
		        int x_dst = cord_dst.x - worker.getWidth()/2;
		        int y_dst = cord_dst.y - worker.getHeight()/2;
			    for(int i = 1; i <= 24; i++)
			    {
			        int x = (int) ((x_dst - x_src) * i/ 24) + x_src;
			        int y = (int) ((y_dst - y_src) * i/ 24) + y_src;
			        this.worker.setPosWhileMoving(x, y);
					Thread.sleep(1000/36);
			    }
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
