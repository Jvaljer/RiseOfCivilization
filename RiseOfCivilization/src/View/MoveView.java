package View;

import java.awt.Point;

import Model.WorkerModel;

public class MoveView extends Thread
{
	private GameView gameView;
	private WorkerModel worker;
	private Point dst_cord;
	
	public MoveView(GameView gameView, WorkerModel worker, Point cord)
	{
		this.gameView = gameView;
		this.worker = worker;
		this.dst_cord = cord;
	}
	
	public void run()
	{
		Point cord = this.gameView.GetGameModel().GetMapModel().GetPosFromCoord(this.worker.getCordX(), this.worker.getCordY());
		int x_src = cord.x - this.worker.getWidth()/2;
		int y_src = cord.y - this.worker.getHeight()/2;
		int x_dst = this.dst_cord.x - this.worker.getWidth()/2;
		int y_dst = this.dst_cord.y - this.worker.getHeight()/2;
		for(int i = 0; i < 48; i++)
		{
			int x = (int) ((x_dst - x_src) / 48) + x_src;
			int y = (int) ((y_dst - y_src) / 48) + y_src;
			this.gameView.getWorkerView().DrawMovingPlayer(null, x, y);
		}
	}
}
