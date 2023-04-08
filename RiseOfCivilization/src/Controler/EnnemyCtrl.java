package Controler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Model.EnnemyModel;
import Model.MapModel;

public class EnnemyCtrl extends Thread{
	private GameCtrl controler;
	private MapModel map;
	private Random random;
	private ArrayList<EnnemyModel> ennemies;
	
	public EnnemyCtrl(GameCtrl controler) {
		this.controler = controler;
		this.random = new Random();
		map = this.controler.GetGameModel().GetMapModel();
		this.ennemies = this.controler.GetGameModel().getEnnemyModel();
	}
	
	@Override
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
