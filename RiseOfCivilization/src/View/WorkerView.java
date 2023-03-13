package View;

import Model.*;
import java.awt.*;

public class WorkerView {
	private GameView view; 
	private MapModel map_model;
	private WorkerModel worker;
	
	public WorkerView(GameView V, WorkerModel worker) {
		view = V;
		map_model = view.GetGameModel().GetMapModel();
		this.worker = worker;
	}
	
	public void drawWorker(Graphics G) {
		
		int p_width = this.worker.getWidth();
		int p_height = this.worker.getHeight();
		Point pos = this.map_model.GetPosFromCoord(this.worker.getCordX(), this.worker.getCordX());
		
		
		G.setColor(new Color(0,0,0));
		G.drawOval(pos.x - p_width/2 , pos.y - p_height/2, worker.getWidth(), worker.getHeight());
		G.setColor(new Color(0,0,255));
		G.fillOval(pos.x - p_width/2, pos.y - p_height/2, worker.getWidth(), worker.getHeight());
	}
	
	public void drawMove(Graphics G, int i, int j) {
		
		G.setColor(new Color(0,0,0));
		G.drawOval(i,j, worker.getWidth(), worker.getHeight());
		G.setColor(new Color(0,100,255));
		G.drawOval(i,j, worker.getWidth(), worker.getHeight());
	}

	public void drawMovingPlayer(Graphics G, Point dst_cord)
	{
		Point cord = this.view.GetGameModel().GetMapModel().GetPosFromCoord(worker.getCordX(), worker.getCordY());
		int x_src = cord.x - worker.getWidth()/2;
		int y_src = cord.y - worker.getHeight()/2;
		int x_dst = dst_cord.x - worker.getWidth()/2;
		int y_dst = dst_cord.y - worker.getHeight()/2;
		for(int i = 0; i < 48; i++)
		{
			int x = (int) ((x_dst - x_src) / 48) + x_src;
			int y = (int) ((y_dst - y_src) / 48) + y_src;
			this.drawMove(G, x, y);
		}
	}
}
