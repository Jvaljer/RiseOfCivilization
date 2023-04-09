package Controler;

import java.awt.Point;
import java.util.ArrayList;

import Model.EnnemyModel;
import Model.GameModel;

/**
 * The ennemyMove is the controller that extend Thread and make one nnemy move to an another cell
 * 
 * @author William
 */
public class EnnemyMove extends Thread{
	// Main model of the gale
	private GameModel model;
	// The ennemy that's currently moving
	private EnnemyModel ennemy;
	// The destination Coord where the ennemy is moving
	private Point destCord;
	
	/**
	 *  Constructor for the ennemyMove
	 * @param m Main Model of the game
	 * @param e ModelClass of the ennemy that's mooving
	 * @param p Destination
	 */
	public EnnemyMove(GameModel m, EnnemyModel e, Point p) {
		this.model = m;
		this.ennemy = e;
		this.destCord = p;
	}
	
	/**
	 * This is the function that will be lunch when we start the Thread
	 * Set the state of the annemy as moving
	 * Then make him move from cell to cell until he reach his destination
	 */
	public void run()
	{
		this.ennemy.setMoving(true);
		while (this.ennemy.getPos().x != this.destCord.x && this.ennemy.getPos().y != this.destCord.y)
		{
			try 
			{
				ArrayList<Point> path = this.model.GetMapModel().GetShortestPath(this.ennemy.getPos(),this.destCord);
				Point nextCord = this.destCord;
				if(path.size() > 1)
				{
					nextCord = path.get(1);
				}
				Point cord_src = this.model.GetMapModel().GetPosFromCoord(ennemy.getPos().x, ennemy.getPos().y);
		        Point cord_dst = this.model.GetMapModel().GetPosFromCoord(nextCord.x, nextCord.y);
		        int x_src = cord_src.x - ennemy.getWidth()/2;
		        int y_src = cord_src.y - ennemy.getHeight()/2;
		        int x_dst = cord_dst.x - ennemy.getWidth()/2;
		        int y_dst = cord_dst.y - ennemy.getHeight()/2;
			    for(int i = 1; i <= 24; i++)
			    {
			        int x = (int) ((x_dst - x_src) * i/ 24) + x_src;
			        int y = (int) ((y_dst - y_src) * i/ 24) + y_src;
			        this.ennemy.setPosWhileMoving(x, y);
					Thread.sleep(1000/24);
			    }
				this.ennemy.moveTo(nextCord);
			} catch (Exception e) {
				System.out.println("Error in Move EnnemyMove");
				e.printStackTrace();
			}
		}
		this.ennemy.setMoving(false);
	}
}