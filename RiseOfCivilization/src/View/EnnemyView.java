package View;

import java.awt.*;
import Model.EnnemyModel;

public class EnnemyView {
	private GameView game;
	private EnnemyModel model;
	
	public EnnemyView(GameView GV, EnnemyModel EM) {
		game = GV;
		model = EM;
	}
	
	public void DrawEnnemy(Graphics G) {
		int l = model.GetWidth();
		Point pos = model.GetPos();
		Point px_pos = game.GetGameModel().GetMapModel().GetPosFromCoord(pos.x, pos.y);
		G.setColor(new Color(25,0,0));
		G.fillRect(px_pos.x-l/2, px_pos.y-l/2, l, l);
	}
}
