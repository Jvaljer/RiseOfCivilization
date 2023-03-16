package View;

import Model.*;
import java.awt.*;

public class BuildingView {
	private GameView game;
	private MapModel map;
	private BuildingModel model;
	private Point coord;
	private Color color;
	
	public BuildingView(GameView G, BuildingModel M) {
		game = G;
		map = game.GetGameModel().GetMapModel();
		model = M;
		coord = model.GetPos();
		
		switch (model.GetId()) {
			case CityHall :
				color = (new Color(77, 38, 0));
				break;
			case SawMill :
				color = (new Color(51, 51, 0));
				break;
			case Mine :
				color = (new Color(31, 31, 20));
				break;
			case LumberCamp :
				color = (new Color(179, 89, 0));
				break;
			case MinerCamp :
				color = (new Color(128, 128, 0));
				break;
			case Quarry :
				color = (new Color(96, 64, 31));
				break;
			case Shop :
				color = (new Color(204, 204, 0));
				break;
			default:
				break;
		}
	}
	
	public void DrawBuilding(Graphics G) {
		Point pos = map.GetPosFromCoord(coord.x,coord.y);
		G.setColor(new Color(0,0,0));
		G.drawRoundRect(pos.x, pos.y, 10, 15, 2, 2);
		G.setColor(color);
		G.fillRoundRect(pos.x, pos.y, 10, 15, 2, 2);
	}
}
