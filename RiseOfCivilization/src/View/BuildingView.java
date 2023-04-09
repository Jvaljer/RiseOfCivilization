package View;

import Model.*;
import Types.BuildingId;

import java.awt.*;
/**
 * Contains all Icons shown on the map
 *
 * @author Amaury,Abel,Martin
 */
public class BuildingView {
	/** game view */private GameView game;
	/** map model */private MapModel map;
	/** building model */private BuildingModel model;
	/** coordinates of the building */private Point coord;
	/** color of the building used for the level dots */private Color color;
	/** the image of this building */private CellIcon Icons;


	/** contructor of BuildingView assigns the color to the building  */
	public BuildingView(GameView G, BuildingModel M) {
		game = G;
		map = game.GetGameModel().GetMapModel();
		model = M;
		coord = model.GetPos();
		Icons=new CellIcon();
		
		switch (model.getId()) {
			case CityHall :
				color = (new Color(77, 38, 0));
				break;
			case SawMill :
				color = (new Color(51, 102, 0));
				break;
			case Mine :
				color = (new Color(102, 102, 102));
				break;
			case LumberCamp :
				color = (new Color(179, 89, 0));
				break;
			case MinerCamp :
				color = (new Color(128, 128, 0));
				break;
			case Quarry :
				color = (new Color(134, 45, 45));
				break;
			case Shop :
				color = (new Color(204, 204, 0));
				break;
			default:
				break;
		}
	}
	
	public BuildingModel getModel() {
		return model;
	}
	
	public Point getCoord() {
		return coord;
	}

	/** Draw the image of the building given the coordinates type of building  */
	public void DrawImageFromId(BuildingId m_id, Graphics G, int x, int y){
		switch (m_id) {
			case CityHall :
				G.drawImage(CellIcon.CityHall,x-18,y-19,37,34,null);
				break;
			case SawMill :
				G.drawImage(CellIcon.SawMill,x-15,y-15,30,30,null);
				break;
			case Mine :
				G.drawImage(CellIcon.Mine,x-15,y-15,30,30,null);
				break;
			case Quarry :
				G.drawImage(CellIcon.Quarry,x-15,y-15,30,30,null);
				break;
			case LumberCamp :
				G.drawImage(CellIcon.LumberCamp,x-15,y-15,30,30,null);
				break;
			case MinerCamp :
				G.drawImage(CellIcon.MinerCamp,x-15,y-15,30,30,null);
				break;
			case QuarryWorkerCamp :
				G.drawImage(CellIcon.QuarryWorkerCamp,x-15,y-15,30,30,null);
				break;
			case Barrack :
				G.drawImage(CellIcon.Barrack,x-15,y-15,30,30,null);
				break;
			case Shop :
				G.drawImage(CellIcon.Shop,x-15,y-15,30,30,null);
				break;
			default :
				break;
		}
	}

	/** Draw the image of the building given the coordinates type of building but bigger */
	
	public void DrawImageFromId(BuildingId m_id, Graphics G, int x, int y, boolean is_bigger){
		switch (m_id) {
			case CityHall :
				G.drawImage(CellIcon.CityHall,x-54,y-57,111,102,null);
				break;
			case SawMill :
				G.drawImage(CellIcon.SawMill,x-45,y-45,90,90,null);
				break;
			case Mine :
				G.drawImage(CellIcon.Mine,x-45,y-45,90,90,null);
				break;
			case Quarry :
				G.drawImage(CellIcon.Quarry,x-45,y-45,90,90,null);
				break;
			case LumberCamp :
				G.drawImage(CellIcon.LumberCamp,x-45,y-45,90,90,null);
				break;
			case MinerCamp :
				G.drawImage(CellIcon.MinerCamp,x-45,y-45,90,90,null);
				break;
			case QuarryWorkerCamp :
				G.drawImage(CellIcon.QuarryWorkerCamp,x-45,y-45,90,90,null);
				break;
			case Barrack :
				G.drawImage(CellIcon.Barrack,x-45,y-45,90,90,null);
				break;
			case Shop :
				G.drawImage(CellIcon.Shop,x-45,y-45,90,90,null);
				break;
			default :
				break;
		}
	}
	/** Draws a building withs its icons color and level */
	public void DrawBuilding(Graphics G) {
		Point pos = map.GetPosFromCoord(coord.x,coord.y);
		G.setColor(new Color(0,0,0));
		DrawImageFromId(model.getId(),G,pos.x,pos.y);
		Point[] pts;
		int d = model.GetTriangleDist();
		switch (model.GetLevel()) {
			case 1 :
				/*
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
				if(model.GetOccupied()) {
					G.setColor(new Color(200,0,0));
				} else {
					G.setColor(color);
				}
				G.fillPolygon(pts_x, pts_y, pts.length);
				*/
				G.setColor(color);
				G.drawOval(pos.x-(d/3),pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3),pos.y+d*1,5,5);

				break;
				
			case 2 :
				/*
				int w = model.GetRecWidth();
				int h = model.GetRecHeight();
				int w_div = w / 2;
				int h_div = h / 2;
				G.drawRoundRect(pos.x - w_div, pos.y - h_div, w, h, 2, 2);
				if(model.GetOccupied()) {
					G.setColor(new Color(200,0,0));
				} else {
					G.setColor(color);
				}
				G.setColor(color);
				G.fillRoundRect(pos.x - w_div, pos.y - h_div, w, h, 2, 2);
				*/

				G.setColor(color);
				G.drawOval(pos.x-(d/3)-(d/2)-2,pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3)-(d/2)-2,pos.y+d*1,5,5);
				G.drawOval(pos.x-(d/3)+(d/2)+2,pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3)+(d/2)+2,pos.y+d*1,5,5);
				break;
			case 3 :
				G.setColor(color);
				G.drawOval(pos.x-(d/3)-(d/2)-2,pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3)-(d/2)-2,pos.y+d*1,5,5);
				G.drawOval(pos.x-(d/3),pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3),pos.y+d*1,5,5);
				G.drawOval(pos.x-(d/3)+(d/2)+2,pos.y+d*1,5,5);
				G.fillOval(pos.x-(d/3)+(d/2)+2,pos.y+d*1,5,5);
				break;
				
			default :
				break;	
		}
	}
}
