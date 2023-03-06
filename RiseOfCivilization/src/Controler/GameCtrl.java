package Controler;

import View.*;
import Model.*;
import java.awt.*;

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
	public void Build(CellModel cell) {
		Point pts = cell.GetCoord();
		WorkerModel nearest_worker = model.GetMapModel().GetNearestWorker(pts);
		return;
	}
	public void Drop(CellModel cell) {
		Point pts = cell.GetCoord();
		WorkerModel nearest_worker = model.GetMapModel().GetNearestWorker(pts);
		return;
	}
	public void Move(CellModel cell) {
		return;
	}
	public void Collect(CellModel cell) {
		Point pts = cell.GetCoord();
		WorkerModel nearest_worker = model.GetMapModel().GetNearestWorker(pts);
		return;
	}
	public void NewWorker(CellModel cell) {
		return;
	}
	public void Expand(CellModel cell) {
		Point pts = cell.GetCoord();
		WorkerModel nearest_worker = model.GetMapModel().GetNearestWorker(pts);
		return;
	}
	
	@Override
	public void run() {
		//must implement
	}
}
