package Controler;

import View.*;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;

public class GameCtrl extends Thread{
	private GameView view;
	private GameModel model;
	private ArrayList<JButton> buttons;
	private MapCtrl map;
	
	public GameCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
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
			}
		}
		
		(new RefreshCtrl(view)).start();
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	public GameView GetGameView() {
		return view;
	}
	public MapCtrl GetMapCtrl() {
		return map;
	}
	public ArrayList<JButton> GetButtons(){
		return buttons;
	}
	public JButton GetButtonFromName(String name) {
		for(JButton but : buttons) {
			if(but.getName()==name) {
				return but;
			}
		}
		return null;
	}
	
	@Override
	public void run() {
		//must implement
	}
}
