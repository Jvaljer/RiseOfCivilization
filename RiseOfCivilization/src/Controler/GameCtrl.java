package Controler;

import View.*;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;

public class GameCtrl extends Thread{
	private GameView view;
	private GameModel model;
	
	private MapCtrl map;
	
	public GameCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
		map = new MapCtrl(view);
		
		ArrayList<JButton> buttons = view.GetDashboardView().GetInfobarView().GetButtonList();
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
				case "New_Worker" :
					button.addActionListener(new ActionNewWorker(this));
					break;
				case "Collect" :
					button.addActionListener(new ActionCollect(this));
					break;
				case "Drop" :
					button.addActionListener(new ActionDrop(this));
					break;
			}
		}
		
		(new RefreshCtrl(view)).start();
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	public MapCtrl GetMapCtrl() {
		return map;
	}
	@Override
	public void run() {
		//must implement
	}
}
