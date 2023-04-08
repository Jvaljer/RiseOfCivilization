package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Model.EnnemyModel;
import Model.MapModel;

public class EnnemyView {
	private GameView view;
    private MapModel map_model;
    private EnnemyModel ennemy;
    
    public EnnemyView(GameView view, EnnemyModel ennemy)
    {
    	this.view = view;
        map_model = view.GetGameModel().GetMapModel();
        this.ennemy = ennemy;
    }
    
    public void DrawEnnemy(Graphics G) {
        int w = ennemy.getWidth();
        int h = ennemy.getHeight();
        int w_div = w/2;
        int h_div = h/2;
        
        Point coord = ennemy.getPos();
        Point pos = map_model.GetPosFromCoord(coord.x,coord.y);
		
		G.setColor(new Color(0,0,0));
		G.drawOval(pos.x - w_div, pos.y - h_div, w, h);
		G.setColor(new Color(255,0,0));
		G.fillOval(pos.x - w_div, pos.y - h_div, w, h);
	}
    
    public void drawMove(Graphics G, int i, int j) {
        G.setColor(new Color(0,0,0));
        G.drawOval(i,j, ennemy.getWidth(), ennemy.getHeight());
        G.setColor(new Color(255,0,0));
        G.fillOval(i,j, ennemy.getWidth(), ennemy.getHeight());
    }
}
