package Controler;

import View.*;
import Model.*;

public class GameCtrl extends Thread{
	private GameView view;
	private GameModel model;
	
	private MapCtrl map;
	
	public GameCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
		map = new MapCtrl(view);
		
	}
	
	@Override
	public void run() {
		//must implement
	}
}
