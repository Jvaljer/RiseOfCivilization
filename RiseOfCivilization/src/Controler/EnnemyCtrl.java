package Controler;

import Model.MapModel;
import Model.EnnemyModel;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import Model.WorkerModel;
import Types.Pair;

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
			ArrayList<Point> vision = map.GetNeighbours(cur_pos.x, cur_pos.y);
			int len = vision.size();
			Pair<Boolean,Point> pair = map.EnnemySearchForCitizen(vision);
			
			if(pair.Fst()) {
				//if so -> then hunt it for 5 to 10 cells
				WorkerModel citizen = map.GetWorkerFromCoord(pair.Snd());
				int pursue_time = ThreadLocalRandom.current().nextInt(5,11);
				boolean citizen_caught = false;
				int pursued_time = 0;
				while(!citizen_caught && (pursued_time<pursue_time)) {
					if(cur_pos.x==pair.Snd().x && cur_pos.y==pair.Snd().y) {
						System.out.println("citizen has been caught");
						citizen_caught = true;
					}
					
					ArrayList<Point> pursue_path = map.GetShortestPath(cur_pos, pair.Snd());
					if(pursue_path.size()==1) {
						cur_pos = pursue_path.get(0);
					} else {
						cur_pos = pursue_path.get(1);
					}
					model.MoveTo(cur_pos);
					pursued_time++;
					pair.SetSnd(citizen.getPos());
					try {
						sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if(citizen_caught) {
					//if catches it then kill 
					System.out.println("starting EnnemyAttacksWorker");
					game.GetGameModel().EnnemyAttacksWorker(model,citizen);
				}
				//if not then simply stops
			} else {
				//if not -> then may move of 1 cell
				ArrayList<Point> neigh = map.GetNeighbours(cur_pos.x, cur_pos.y);
				int rand_index = ThreadLocalRandom.current().nextInt(0,neigh.size());
				
				cur_pos = neigh.get(rand_index);
				model.MoveTo(cur_pos);
			}
			
			//then thread waits for 1.5s 
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
