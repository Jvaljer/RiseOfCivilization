package View;

import Model.*;
import java.awt.*;

public class WorkerView {
	private GameView view;
	private MapModel map_model;
	private WorkerModel player;
	
	private Point player_size;
	
	public WorkerView(GameView V) {
		view = V;
		map_model = view.GetGameModel().GetMapModel();
		player = view.GetGameModel().GetWorkerModel();
	}
	
	public void DrawPlayer(Graphics G) {
		
		int p_width = this.player.getWidth();
		int p_height = this.player.getHeight();
		Point pos = this.map_model.GetPosFromCoord(this.player.getCordX(), this.player.getCordX());
		
		
		G.setColor(new Color(0,0,0));
		G.drawOval(pos.x - p_width/2 , pos.y - p_height/2, player.getWidth(), player.getHeight());
		G.setColor(new Color(0,0,255));
		G.fillOval(pos.x - p_width/2, pos.y - p_height/2, player.getWidth(), player.getHeight());
	}
	
	public void DrawMovingPlayer(Graphics G, int i, int j) {
		
		G.setColor(new Color(0,0,0));
		G.drawOval(i,j, player.getWidth(), player.getHeight());
		G.setColor(new Color(0,100,255));
		G.drawOval(i,j, player.getWidth(), player.getHeight());
	}
}
