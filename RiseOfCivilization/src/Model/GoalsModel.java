package Model;

import Types.Goals;
import java.util.concurrent.*;

/**
 * The GoalsModel is an model that count the score of the player
 * 
 * @author Abel
 */
public class GoalsModel {
	// The Main model of this game it gather all the information about the game
	private GameModel game;
	// An Hashtable that regroupe the score of the player
	private ConcurrentHashMap<Goals, Integer> goals_count;
	//goals view's variable & constants
	private final static int frame_width = 150;
	private final static int frame_height = 300;
	
	/**
	 * Constructor for the GoalsModel
	 * @param GM the GameModel
	 */
	public GoalsModel(GameModel GM) {
		game = GM;
		goals_count = new ConcurrentHashMap<Goals, Integer>();
		Goals[] goals = Goals.values();
		for(Goals g : goals) {
			goals_count.put(g, 0);
		}
	}
	
	/**
	 * Getter for goals_count
	 * return the score of the player
	 * @return this.goals_count
	 */
	public ConcurrentHashMap<Goals, Integer> GetGoalsMap(){
		return goals_count;
	}
	
	/**
	 * The IncrementGoal Method
	 * Increment the value of agoal in the hastable
	 * @param key the key in the Hastable of the goal we increase
	 */
	public void IncrementGoal(Goals key) {
		int amount = goals_count.get(key);
		goals_count.replace(key, amount+1);
	}
	
	/**
	 * The IncrementGoal Method
	 * add n to the value of a goal in the Hastable
	 * @param keyt he key in the Hastable of the goal we increase
	 * @param n Value that we add to the score associated with the key
	 * 
	 */
	public void IncrementGoal(Goals key, int n) {
		int amount = goals_count.get(key);
		goals_count.replace(key, amount+n);
	}
	
	/**
	 * Getter for the width of the frame
	 * @return this.frame_width
	 */
	public int GetWidth() {
		return frame_width;
	}
	
	/**
	 * Getter for the height of the frame
	 * @return this.frame_height
	 */
	public int GetHeight() {
		return frame_height;
	}
}
