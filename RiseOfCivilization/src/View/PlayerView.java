package View;

import Model.*;
import java.awt.*;

public class PlayerView {
	private GameView view;
	private MapModel map_model;
	private PlayerModel player;
	
	private Point player_pos;
	private Point player_size;
	
	public PlayerView(GameView V) {
		view = V;
		map_model = view.GetGameModel().GetMapModel();
		player = view.GetGameModel().GetPlayerModel();
		player_pos = player.GetPos();
		player_size = new Point(player.GetWidth(),player.GetHeight());
	}
	
	public void DrawPlayer(Graphics G) {
		Point pixel_pos = map_model.GetPosFromCoord(player_pos.x, player_pos.y);
		
		int p_width = player_size.x;
		int p_height = player_size.y;
		
		G.setColor(new Color(150,0,0));
		G.drawOval(pixel_pos.x - p_width/2 , pixel_pos.y - p_height/2, player.GetWidth(), player.GetHeight());
		G.setColor(new Color(255,0,0));
		G.fillOval(pixel_pos.x - p_width/2, pixel_pos.y - p_height/2, player.GetWidth(), player.GetHeight());
	}
}
