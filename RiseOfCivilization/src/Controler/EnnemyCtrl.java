package Controler;

import Model.MapModel;
import Model.EnnemyModel;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

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
			System.out.println("ennemy is alive");
			int wait = ThreadLocalRandom.current().nextInt(5,10);
			try {
				sleep(wait*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("ennemy moving");
			ArrayList<Point> neigh = map.GetValidNeighbors(cur_pos.x, cur_pos.y);
			int rand_i = ThreadLocalRandom.current().nextInt(0,neigh.size());
			dst_coord = neigh.get(rand_i);
			System.out.println("first dst -> "+dst_coord);
			int second_move = ThreadLocalRandom.current().nextInt(0,2);
			if(second_move==1) {
				ArrayList<Point> neigh_ = map.GetValidNeighbors(dst_coord.x, dst_coord.y);
				int rand_j = ThreadLocalRandom.current().nextInt(0,neigh_.size());
				dst_coord = neigh_.get(rand_j);
				System.out.println("second dst -> "+dst_coord);
			}
			while (cur_pos.x != dst_coord.x || cur_pos.y != dst_coord.y){
				try {
					ArrayList<Point> path = map.GetShortestPath(cur_pos,dst_coord);
	 				Point nxt_coord = path.get(1);
					cur_pos = nxt_coord;
					model.MoveTo(nxt_coord);
				} catch (Exception e) {
					System.out.println("Error in Move Worker");
					e.printStackTrace();
				}
			}
		}
		System.out.println("ennemy is DEAD");
	}
}
