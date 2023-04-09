package Model;

import Types.Goals;
import java.util.concurrent.*;

public class GoalsModel {
	private GameModel game;
	private ConcurrentHashMap<Goals, Integer> goals_count;
	
	public GoalsModel(GameModel GM) {
		game = GM;
		goals_count = new ConcurrentHashMap<Goals, Integer>();
		Goals[] goals = Goals.values();
		for(Goals g : goals) {
			goals_count.put(g, 0);
		}
	}
	
	public void IncrementGoal(Goals key) {
		int amount = goals_count.get(key);
		goals_count.replace(key, amount++);
	}
}
