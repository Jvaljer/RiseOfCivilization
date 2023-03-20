package View;

import Model.*;
import java.awt.*;

public class WorkerView {
	private GameView view; 
	private MapModel map_model;
	private WorkerModel worker;
	private int FPS = 24;
	
	public WorkerView(GameView V, WorkerModel w) {
		view = V;
		map_model = view.GetGameModel().GetMapModel();
		this.worker = w;
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
		for(int i = 1; i <= FPS; i++)
		{
			int x = (int) ((x_dst - x_src) / FPS) + x_src;
			int y = (int) ((y_dst - y_src) / FPS) + y_src;
			this.drawMove(G, x, y);
		}
	}
	
	public void DrawWorker(Graphics G) {
		int w = worker.getWidth();
		int h = worker.getHeight();
		int w_div = w/2;
		int h_div = h/2;
		
		Point coord = worker.getPos();
		Point pos = map_model.GetPosFromCoord(coord.x,coord.y);
		
		G.setColor(new Color(0,0,0));
		G.drawOval(pos.x - w_div, pos.y - h_div, w, h);
		G.setColor(new Color(0,100,200));
		G.fillOval(pos.x - w_div, pos.y - h_div, w, h);
	}
}
