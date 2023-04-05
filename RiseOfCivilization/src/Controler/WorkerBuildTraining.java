package Controler;

import Model.MapModel;
import Model.WorkerModel;
import java.awt.*;
import Model.BuildingChoiceModel;
import View.BuildingChoiceView;

public class WorkerBuildTraining extends Thread {
	private GameCtrl game;
	private MapModel map;
	private WorkerModel worker;
	private Point dst_coord;
	
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
