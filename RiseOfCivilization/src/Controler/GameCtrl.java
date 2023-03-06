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
		
		(new RefreshCtrl(view)).start();
	}
	
	//here are the actions method, which will be called whenever the player clicks on the related button
	public void Harvest() {
		return;
	}
	public void Build() {
		return;
	}
	public void Drop() {
		return;
	}
	public void Move() {
		return;
	}
	public void Collect() {
		return;
	}
	public void NewWorker() {
		return;
	}
	public void Expand() {
		return;
	}
	
	@Override
	public void run() {
		//must implement
	}
}
