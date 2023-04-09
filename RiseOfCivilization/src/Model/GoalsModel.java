package Model;

import Types.Goals;
import java.util.concurrent.*;

public class GoalsModel {
	private GameModel game;
	private ConcurrentHashMap<Goals, Integer> goals_count;
	//goals view's variable & constants
	private final static int frame_width = 150;
	private final static int frame_height = 300;
	
	public GoalsModel(GameModel GM) {
		game = GM;
		goals_count = new ConcurrentHashMap<Goals, Integer>();
		Goals[] goals = Goals.values();
		for(Goals g : goals) {
			goals_count.put(g, 0);
		}
	}
	
	public ConcurrentHashMap<Goals, Integer> GetGoalsMap(){
		return goals_count;
	}
	
	public void IncrementGoal(Goals key) {
		int amount = goals_count.get(key);
		goals_count.replace(key, amount++);
	}
	
	public int GetWidth() {
		return frame_width;
	}
	
	public int GetHeight() {
		return frame_height;
	}
}
