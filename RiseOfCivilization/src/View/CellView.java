package View;

import Model.*;
import java.awt.*;

import Types.*;


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
		Icons = new CellIcon();
	}
	
	public CellView(CellView other) {
		model = other.model;
		abs = model.GetX();
		ord = model.GetY();
		id = model.GetId();
		clicked = false;
		Icons = new CellIcon();
	}
	
	public int GetAbs() {
		return abs;
	}
	
	public void setAbs(int new_abs) {
		abs = new_abs;
	}
	
	public int GetOrd() {
		return ord;
	}
	
	public void setOrd(int new_ord) {
		ord = new_ord;
	}
	
	public CellModel GetCellModel() {
		return model;
	}
	
	public Color GetColorFromId(CellId m_id) {
		switch (m_id) {
			case Plain :
				return (new Color(243, 209, 117));
			case Forest :
				return (new Color(0, 153, 0));
			case Mountain :
				return (new Color(150, 150, 150));
			case Iron_Deposit :
				return (new Color(169, 84, 69));
			case City : 
				return (new Color(165, 110, 20));
			default :
				return (new Color(0,0,0));
		}
	}

	/** Draw the image of the cell given its coordinates and type  */
	
	public void DrawImageFromId(CellId m_id, Graphics G,int x,int y){
		switch (m_id) {
			case Plain :
				G.drawImage(CellIcon.Plain,x,y-17,40,34,null);
				break;
			case Forest :
				G.drawImage(CellIcon.Forest,x+3,y-16,37,34,null);
				break;
			case Mountain :
				G.drawImage(CellIcon.Mountain,x+2,y-16,38,34,null);
				break;
			case Iron_Deposit :
				G.drawImage(CellIcon.Iron_Deposit,x+3,y-16,37,34,null);
				break;
			case City :
				break;
			default :
				break;
		}
	}
	/** Draw the image of the cell given its coordinates and type but bigger  */
	public void DrawImageFromId(CellId m_id, Graphics G,int x,int y, boolean is_bigger){
		switch (m_id) {
			case Plain :
				G.drawImage(CellIcon.Plain,x-1,y-53,122,105,null);
				break;
			case Forest :
				G.drawImage(CellIcon.Forest,x+6,y-51,110,102,null);
				break;
			case Mountain :
				G.drawImage(CellIcon.Mountain,x+4,y-51,115,105,null);
				break;
			case Iron_Deposit :
				G.drawImage(CellIcon.Iron_Deposit,x+5,y-51,110,102,null);
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
	/** changes the appearance of the cell when called */
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
	/** draws the border of cell
	 * fills it with its color
	 * and draws its icon*/
	public void DrawClick(Graphics G, int[] X, int[] Y, boolean is_bigger) {
		int len = border_pts.length;
		int[] pts_x = new int[len];
		int[] pts_y = new int[len];
		
		for(int n=0; n<len; n++) {
			pts_x[n] = border_pts[n].x;
			pts_y[n] = border_pts[n].y;
		}
		G.setColor(GetColorFromId(model.GetId()));
		G.fillPolygon(pts_x, pts_y, len);
		DrawImageFromId(model.GetId(),G,pts_x[0],pts_y[0], true);
	}
	/** Draws a cell */
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
		
		//DrawClick(G, x, y);
	}
	/** Draws a cell but bigger */
	public void DrawCell(Graphics G, int s, int x0, int y0, boolean is_bigger) {
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
		
		DrawClick(G, x, y, true);
	}
	/** draws a contour around the cell when called */
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