package Controler;

import Model.MapModel;
import Model.EnnemyModel;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import Model.WorkerModel;

public class EnnemyCtrl extends Thread {
	private GameCtrl game;
	private MapModel map;
	private EnnemyModel model;
	private Point cur_pos;
	private Point dst_coord;
	
	public EnnemyCtrl(GameCtrl GC, EnnemyModel EM) {
		game = GC;
		map = game.GetGameModel().GetMapModel();
		model = EM;
		cur_pos = model.GetPos();
	}
	
	@Override
	public void run() {
		while(!model.IsDead()) {
			//if the enney is alive, we want it to first check if there's any citizen close to it
			ArrayList<Point> vision = map.GetValidNeighbors(cur_pos.x, cur_pos.y);
			int len = vision.size();
			boolean sees_citizen = false;
			Point citizen_coord = null;
			
			map.EnnemySearchForCitizen(sees_citizen,citizen_coord, vision);
			
			if(sees_citizen) {
				//if so -> then hunt it for 5 to 10 cells
				WorkerModel citizen = map.GetWorkerFromCoord(citizen_coord);
				int pursue_time = ThreadLocalRandom.current().nextInt(5,11);
				boolean citizen_caught = false;
				int pursued_time = 0;
				while(!citizen_caught || (pursued_time<pursue_time)) {
					if(cur_pos.x==citizen_coord.x && cur_pos.y==citizen_coord.y) {
						citizen_caught = true;
					}
					
					ArrayList<Point> pursue_path = map.GetShortestPath(cur_pos, citizen_coord);
					cur_pos = pursue_path.get(1);
					pursued_time++;
					citizen_coord = citizen.getPos();
					try {
						sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if(citizen_caught) {
					//if catches it then kill 
					game.EnnemyAttacksWorker(model,citizen);
				}
				//if not then simply stops
			} else {
				//if not -> then may move of 1 cell
			}
			
			//then thread waits for 1s 
			try {
				sleep(1500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("ennemy is DEAD");
		//if the ennemy is dead then we wanna delete its model & view from the game's model & view lists
	}
}
