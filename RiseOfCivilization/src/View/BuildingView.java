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
		
		Point[] pts;
		switch (model.GetLevel()) {
			case 1 :
				int d = model.GetTriangleDist();
				pts = new Point[3];
				pts[0] = new Point(pos.x,pos.y-d);
				pts[1] = new Point(pos.x+(d/2)+(d/3), pos.y+(d/2)+(d/3));
				pts[2] = new Point(pos.x-(d/2)-(d/3), pos.y+(d/2)+(d/3));
				
				int[] pts_x = new int[3];
				int[] pts_y = new int[3];
				
				for(int i=0; i<pts.length; i++) {
					pts_x[i] = pts[i].x;
					pts_y[i] = pts[i].y;
				}
				
				G.drawPolygon(pts_x, pts_y, pts.length);
				G.setColor(color);
				G.fillPolygon(pts_x, pts_y, pts.length);
				break;
				
			case 2 :
				int w = model.GetRecWidth();
				int h = model.GetRecHeight();
				int w_div = w / 2;
				int h_div = h / 2;
				G.drawRoundRect(pos.x - w_div, pos.y - h_div, w, h, 2, 2);
				G.setColor(color);
				G.fillRoundRect(pos.x - w_div, pos.y - h_div, w, h, 2, 2);
				break;
			case 3 :
				break;
			default :
				break;	
		}
	}
}
