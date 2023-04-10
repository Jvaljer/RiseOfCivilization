package Threads;

import Model.MapModel;
import Model.WorkerModel;
import java.awt.*;

import Controler.BuildingChoiceCtrl;
import Controler.GameCtrl;
import Model.BuildingChoiceModel;
import View.BuildingChoiceView;

/**
 * This class is part of the Build Action Controller.
 * It extends Thread and is called by ActionBuild.
 * 
 * @author Abel
 */
public class WorkerBuildTraining extends Thread {
	private GameCtrl game;
	private MapModel map;
	private WorkerModel worker;
	private Point dst_coord;
	
	/**
     * Default Constructor of ActionBuild.
     *
     * @param GC  the main controller
	 * @param WM  the worker model
	 * @param pts the Point of the coordinates of the training building
     */
	public WorkerBuildTraining(GameCtrl GC, WorkerModel WM, Point P) {
		game = GC;
		map = game.GetGameModel().GetMapModel();
		worker = WM;
		dst_coord = P;
	}
	
	@Override
	public void run() {
		BuildingChoiceModel model = new BuildingChoiceModel(game.GetGameModel());
		BuildingChoiceView view = new BuildingChoiceView(game.GetGameView(),model);
		new BuildingChoiceCtrl(game,view, dst_coord);
	}
}
