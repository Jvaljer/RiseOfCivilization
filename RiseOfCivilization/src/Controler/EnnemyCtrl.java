package Controler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Model.EnnemyModel;
import Model.MapModel;

/**
 * This is the Controller for all the ennemy on the game it extend Thread so it can be lunch in the main Controller
 * It suppose to lunch the thread that make them move and fight if their are on the same cell that a worker
 * 
 * @author William
 */
public class EnnemyCtrl extends Thread{
	// Main controller of the game
	private GameCtrl controler;
	// Model of the map so the ennemy can get information about other entity
	private MapModel map;
	// Random to generate random value for where the ennemy is moving
	private Random random;
	// List of all the ennemy
	private ArrayList<EnnemyModel> ennemies;
	
	/**
	 * Constructor for the ennemy Controller
	 * @param controler The main Controller of the game
	 */
	public EnnemyCtrl(GameCtrl controler) {
		this.controler = controler;
		this.random = new Random();
		map = this.controler.GetGameModel().GetMapModel();
		this.ennemies = this.controler.GetGameModel().getEnnemyModel();
	}
	

	@Override
	/**
	 * this is the core of the class, the Thread that will be lunch for controlling the ennemy
	 * While true until the death of ennemy is implemented
	 * Selecte a random ennemy then make him move
	 * Choose random cell and the map that lunch the EnnemyMove Controller
	 * Then the Thread Sleep for random time so it can simulate the way a wolf would move
	 */
	public void run() {
		while(true)
		{	
			try {
				int i = random.nextInt(10);
				if(!this.ennemies.get(i).getMoving())
				{
					int x = this.random.nextInt(this.map.GetLinesAmount());
					int y = this.random.nextInt(this.map.GetColumnsAmount());
					new EnnemyMove(this.map.GetGameModel(), this.ennemies.get(i), new Point(x,y)).start();
					Thread.sleep(random.nextInt(50) * 50);
				}
			} catch (InterruptedException e) {
				System.out.println("Error in EnnemyCtrl");
				e.printStackTrace();
			}
		}
	}
	
}