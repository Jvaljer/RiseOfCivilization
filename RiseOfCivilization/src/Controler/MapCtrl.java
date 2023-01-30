package Controler;

import Model.*;
import View.*;

/** 
 * This class extends Thread in order to create one which will handle all Maps behaviours.
 * @author Abel
 */
public class MapCtrl extends Thread {
	private GameModel model;
	private GameView view;
	private MapModel map_model;
	private MapView map_view;
	
	public MapCtrl(GameView V) {
		view = V;
		model = view.GetGameModel();
		map_view = view.GetMapView();
		map_model = map_view.GetMapModel();
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	
	public GameView GetGameView() {
		return view;
	}
	
	public MapModel GetMapModel() {
		return map_model;
	}
	
	public MapView GetMapView() {
		return map_view;
	}
	
	@Override
	public void run() {
		//must implement
	}
}