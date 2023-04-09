package View;

import Model.*;
import java.awt.*;

/**
 * This class is responsible for rendering the visual representation of a worker on the game map.
 */
public class WorkerView {
    private GameView view;
    private MapModel map_model;
    private WorkerModel worker;
    
    /**
     * Constructs a WorkerView object with the specified GameView and WorkerModel objects.
     * 
     * @param V The GameView object used to render the game.
     * @param w The WorkerModel object to render.
     */
    public WorkerView(GameView V, WorkerModel w) {
        view = V;
        map_model = view.GetGameModel().GetMapModel();
        this.worker = w;
    }
    
    /**
     * Draws a single frame of the moving worker animation.
     * 
     * @param G The Graphics object to draw the animation on.
     * @param i The x-coordinate of the worker's position on the screen.
     * @param j The y-coordinate of the worker's position on the screen.
     */
    public void drawMove(Graphics G, int i, int j) {
        G.setColor(new Color(0,0,0));
        G.drawOval(i,j, worker.getWidth(), worker.getHeight());
        G.setColor(new Color(0,100,255));
        G.fillOval(i,j, worker.getWidth(), worker.getHeight());
    }

    /**
     * Draws the moving player animation.
     * 
     * @param G The Graphics object to draw the animation on.
     * @param dst_cord The destination coordinate of the worker.
     */
    public void drawMovingPlayer(Graphics G, Point dst_cord)
    {
        Point cord_src = this.view.GetGameModel().GetMapModel().GetPosFromCoord(worker.getcoordX(), worker.getcoordY());
        Point cord_dst = this.view.GetGameModel().GetMapModel().GetPosFromCoord(dst_cord.x, dst_cord.y);
        int x_src = cord_src.x - worker.getWidth()/2;
        int y_src = cord_src.y - worker.getHeight()/2;
        int x_dst = cord_dst.x - worker.getWidth()/2;
        int y_dst = cord_dst.y - worker.getHeight()/2;
        for(int i = 1; i <= 48; i++)
        {
            int x = (int) ((x_dst - x_src) * i/ 48) + x_src;
            int y = (int) ((y_dst - y_src) * i/ 48) + y_src;
            this.drawMove(G, x, y);
        }
    }
    
    /**
     * Draws the worker on the game map.
     * 
     * @param G The Graphics object to draw the worker on.
     */
    public void DrawWorker(Graphics G) {
        int w = worker.getWidth();
        int h = worker.getHeight();
        int w_div = w/2;
        int h_div = h/2;
        
        Point coord = worker.getPos();
        Point pos = map_model.GetPosFromCoord(coord.x,coord.y);
		
		G.setColor(new Color(0,0,0));
		G.drawOval(pos.x - w_div, pos.y - h_div, w, h);
		if(worker.GetOccupied()) {
			G.setColor(new Color(200,0,0));
		} else {
			G.setColor(SetColorFromRole());
		}
		G.fillOval(pos.x - w_div, pos.y - h_div, w, h);
	}
    
    public Color SetColorFromRole() {
    	Color color;
    	switch (worker.GetRole()) {
    		case Lumberjack:
    			color = new Color(30,70,20);
    			break;
    			
    		case Miner:
    			color = new Color(75,75,75);
    			break;
    			
    		case QuarryWorker:
    			color = new Color(110,40,30);
    			break;
    			
    		case Knight:
    			color = new Color(0,10,100);
    			break;
    			
    		case Worker:
    			color = new Color(0,100,200);
    			break;
    			
    		default: 
    			color = null;
    			break;
    	}
    	return color;
    }
    
    public WorkerModel GetModel() {
    	return worker;
    }
}
