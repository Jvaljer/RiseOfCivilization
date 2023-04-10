package Controler;

import View.*;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;
import Types.Goals;

/**
 * This is the main Controller.
 * It will link All the button with the associated action (Thread)
 * Then will lunch the controller of all the entity on the game
 * 
 */
public class GameCtrl extends Thread {
	// The "Main" View of the game
	private GameView view;
	// The "Main" Model of the game (all the information of the current state of the game)
	private GameModel model;
	// List of all the button in the game
	private ArrayList<JButton> buttons;
	// Controller of the map
	private MapCtrl map;
	
	/**
	 * Constructor of the is Controller
	 * Initialized all the controller that run the game
	 * @param V
	 */
	public GameCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
		(new ClockCtrl(model.GetClock(),30,0)).start();
		map = new MapCtrl(this,view);
		
		buttons = view.GetDashboardView().GetActionView().GetButtonList();
		for(JButton button : buttons) {
			switch (button.getName()) {
				case "Build" :
					button.addActionListener(new ActionBuild(this));
					break;
				case "Expand" :
					button.addActionListener(new ActionExpand(this));
					break;
				case "Move" :
					button.addActionListener(new ActionMove(this));
					break;
				case "Train" :
					button.addActionListener(new ActionTrain(this));
					break;
				case "Collect" :
					button.addActionListener(new ActionCollect(this));
					break;
				case "LevelUp" :
					button.addActionListener(new ActionLevelUp(this));
					break;
				case "Shop" :
					button.addActionListener(new ActionShop(this));
					break;
				case "Drop" :
					button.addActionListener(new ActionDrop(this));
					break;
				case "Objectives":
					button.addActionListener(new ShowGoals(this));
					break;
				default:
					break;
			}
		}
		
		(new RefreshCtrl(view)).start();
		(new EnnemiesSpawn(this)).start();
		(new AllWorkersCtrl(this)).start();
	}
	
	/**
	 * Getter for the gameModel
	 * @return this.model
	 */
	public GameModel GetGameModel() {
		return model;
	}

	/**
	 * Getter for the GameView
	 * @return this.view
	 */
	public GameView GetGameView() {
		return view;
	}

	/**
	 * Getter for the map Controller
	 * @return this.map
	 */
	public MapCtrl GetMapCtrl() {
		return map;
	}

	/**
	 * Getter for all the button
	 * return an arrayList of JButton
	 * @return this.buttons
	 */
	public ArrayList<JButton> GetButtons(){
		return buttons;
	}

	/**
	 * The GetButtonFromName Function return the button associated with the name
	 * if he exist else return null pointer
	 * 
	 * @param name
	 * @return
	 */
	public JButton GetButtonFromName(String name) {
		for(JButton but : buttons) {
			if(but.getName()==name) {
				return but;
			}
		}
		return null;
	}
	
	/**
	 * Show score method, instantiates a new JFrame that prints the player's score after their calculation
	 */
	public void ShowScore() {
		int score = 0;
		for(Goals key : model.GetGoals().GetGoalsMap().keySet()) {
			switch (key) {
			case ExpandedSlots:
				break;
			case ProductionBuilt:
				break;
			case TrainingBuilt:
				break;
			case KilledEnnemies:
				break;
			case CollectResources:
				break;
			case SoldResources:
				break;
			case BoughtResources:
				break;
			case TrainedWorkers:
				break;
			default:
				break;
			}
		}
		boolean win = ;true;
		new ScoreView(score,win);
	}
	
	@Override
	public void run() {
		while(model.GetClock().IsTicking()) {
			try {
				this.sleep(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ShowScore();
	}
}
