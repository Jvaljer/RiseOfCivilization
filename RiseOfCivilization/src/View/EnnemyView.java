package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Model.EnnemyModel;
import Model.MapModel;
/**
 * This is the class responsible for the graphic representation of the ennemy
 * @author William
 */
public class EnnemyView {
    // The Main view of this game
	private GameView view;
    // The model of the map (information of all the current cell)
    private MapModel map_model;
    // The model of the ennemy we want to draw
    private EnnemyModel ennemy;
    
    /**
     * Constructor of the ennemy
     * @param view
     * @param ennemy
     */
    public EnnemyView(GameView view, EnnemyModel ennemy)
    {
    	this.view = view;
        map_model = view.GetGameModel().GetMapModel();
        this.ennemy = ennemy;
    }
    
    /**
     * This function will draw a red Point on the map where the ennemy is
     * @param G
     */
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
    
    /**
     * this Method Draw a red Point that represent the ennemy on the screen
     * This method is usefull when the ennemy is moving
     * @param G
     * @param i
     * @param j
     */
    public void drawMove(Graphics G, int i, int j) {
        G.setColor(new Color(0,0,0));
        G.drawOval(i,j, ennemy.getWidth(), ennemy.getHeight());
        G.setColor(new Color(255,0,0));
        G.fillOval(i,j, ennemy.getWidth(), ennemy.getHeight());
    }
}