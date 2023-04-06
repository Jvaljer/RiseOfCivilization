package Controler;

import Model.MapModel;
import Model.CellModel;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import Types.EnnemyRole;
import Model.EnnemyModel;

public class EnnemiesSpawn extends Thread {
	private GameCtrl game;
	private MapModel map;
	private ArrayList<CellModel> spawners;
	private CellModel cell;
	
	public EnnemiesSpawn(GameCtrl GC) {
		game = GC;
		map = game.GetGameModel().GetMapModel();
		spawners = map.GetSpawnerCells();
	}
	
	@Override
	public void run() {
		while(true) {
			int wait = ThreadLocalRandom.current().nextInt(20,45);
			try {
				sleep(wait*100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int index = ThreadLocalRandom.current().nextInt(0,spawners.size());
			cell = spawners.get(index);
			int old_len = game.GetGameModel().GetEnnemiesList().size();
			game.GetGameModel().AddEnnemy(cell,EnnemyRole.Wolf);
			int new_len = game.GetGameModel().GetEnnemiesList().size();
			if(old_len < new_len) {
				game.GetGameView().AddEnnemyView(game.GetGameModel().GetEnnemiesList().get(old_len));
				(new EnnemyCtrl()).start();
			}
		}
	}
}
