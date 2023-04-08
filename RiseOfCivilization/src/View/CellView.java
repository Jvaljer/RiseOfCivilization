package View;

import Model.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import Types.*;

import javax.imageio.ImageIO;

/**
 * View class of a Cell, containing and defining all the needed informations to 
 * draw properly the cell as a regular Hexagon, who's color gonna evolve throughout
 * the game.
 * @author abel
 */
public class CellView {
	private CellModel model;
	private int abs;
	private int ord;
	private CellId id;
	private boolean clicked;
	private Point[] border_pts;
	private CellIcon Icons;
	
	public CellView(CellModel M) {
		model = M;
		abs = model.GetX();
		ord = model.GetY();
		id = model.GetId();
		clicked = false;
		Icons=new CellIcon();


	}
	
	public int GetAbs() {
		return abs;
	}
	
	public int GetOrd() {
		return ord;
	}
	
	public CellModel GetCellModel() {
		return model;
	}
	
	public Color GetColorFromId(CellId m_id) {
		int n = 2;
		if (this.model.getVisible())
		{
			n = 1;
		}
		switch (m_id) {
			case Plain :
				return (new Color(254/n, 204/n, 102/n));
			case Forest :
				return (new Color(0, 152/n, 0));
			case Mountain :
				return (new Color(150/n, 150/n, 150/n));
			case Iron_Deposit :
				return (new Color(168/n, 84/n, 69/n));
			case City : 
				return (new Color(165/n, 110/n, 20/n));
			default :
				return (new Color(0,0,0));
		}
	}
	public void DrawImageFromId(CellId m_id, Graphics G,int x,int y){
		switch (m_id) {
			case Plain :
				G.drawImage(Icons.Plain,x+3,y-16,37,34,null);
				break;
			case Forest :
				G.drawImage(Icons.Forest,x+3,y-16,37,34,null);
				break;
			case Mountain :
				G.drawImage(Icons.Mountain,x+3,y-16,37,34,null);
				break;
			case Iron_Deposit :
				G.drawImage(Icons.Iron_Deposit,x+3,y-16,37,34,null);
				break;
			case City :
				break;
			default :
				break;
		}
	}
	
	public void Click() {
		clicked = !clicked;
	}
	
	public void DrawClick(Graphics G, int[] X, int[] Y) {
		int len = border_pts.length;
		int[] pts_x = new int[len];
		int[] pts_y = new int[len];
		
		for(int n=0; n<len; n++) {
			pts_x[n] = border_pts[n].x;
			pts_y[n] = border_pts[n].y;
		}
		G.setColor(GetColorFromId(model.GetId()));
		G.fillPolygon(pts_x, pts_y, len);
		DrawImageFromId(model.GetId(),G,pts_x[0],pts_y[0]);
	}
	
	public void DrawCell(Graphics G, int s, int x0, int y0) {
		int w = (int) (Math.sqrt(3) * s);
		
		int w_gap = s + s/2;
		int h_gap = (int) (Math.sqrt(3) * s);
		
		int gap;
		
		border_pts = new Point[7];
		int[] x = new int[7];
		int[] y = new int[7];
		
		if(abs%2==0) {
			gap = 0;
		} else {
			gap = w/2;
		}
		int xi = x0 + abs*w_gap;
		int yi = y0 + ord*h_gap - gap;
		
		border_pts[0] = new Point(xi - s, yi);
		border_pts[1] = new Point(xi - (s/2), yi - (w/2));
		border_pts[2] = new Point(xi + (s/2), yi - (w/2));
		border_pts[3] = new Point(xi + s, yi);
		border_pts[4] = new Point(xi + (s/2), yi + (w/2));
		border_pts[5] = new Point(xi - (s/2), yi + (w/2));
		border_pts[6] = new Point(xi - s, yi);
		
		for(int n=0; n<border_pts.length; n++) {
			x[n] = border_pts[n].x;
			y[n] = border_pts[n].y;
		}
		
		DrawClick(G, x, y);
	}
	
	public void DrawContour(Graphics G, int s, int x0, int y0) {
		int w = (int) (Math.sqrt(3) * s);
		
		int w_gap = s + s/2;
		int h_gap = (int) (Math.sqrt(3) * s);
		
		int gap;
		border_pts = new Point[7];
		int[] x = new int[7];
		int[] y = new int[7];
		
		if(abs%2==0) {
			gap = 0;
		} else {
			gap = w/2;
		}
		int xi = x0 + abs*w_gap;
		int yi = y0 + ord*h_gap - gap;
		
		border_pts[0] = new Point(xi - s, yi);
		border_pts[1] = new Point(xi - (s/2), yi - (w/2));
		border_pts[2] = new Point(xi + (s/2), yi - (w/2));
		border_pts[3] = new Point(xi + s, yi);
		border_pts[4] = new Point(xi + (s/2), yi + (w/2));
		border_pts[5] = new Point(xi - (s/2), yi + (w/2));
		border_pts[6] = new Point(xi - s, yi);
		
		for(int n=0; n<border_pts.length; n++) {
			x[n] = border_pts[n].x;
			y[n] = border_pts[n].y;
		}
		
		G.setColor(new Color(255,0,0));
		G.drawPolyline(x, y, 7);
	}
}