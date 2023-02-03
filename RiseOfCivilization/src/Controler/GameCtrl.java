package Controler;

import View.*;

public class GameCtrl extends Thread{
	private GameView view;
	
	public GameCtrl(GameView V) {
		view = V;
	}
	
	@Override
	public void run() {
		//must implement
	}
}
