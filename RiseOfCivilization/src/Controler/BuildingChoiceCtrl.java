package Controler;

import Model.BuildingChoiceModel;
import Threads.*;
import Threads.BuildTraining;
import Types.BuildingId;
import View.BuildingChoiceView;
import Types.BuildingId;
import java.awt.Point;

public class BuildingChoiceCtrl {
	private GameCtrl game;
	private BuildingChoiceView view;
	private BuildingChoiceModel model;
	
	public BuildingChoiceCtrl(GameCtrl GC, BuildingChoiceView BCV, Point pts) {
		game = GC;
		view = BCV;
		model = view.GetModel();
		
		view.GetBarrackButton().addActionListener(new BuildTraining(game, view, BuildingId.Barrack, pts));
		view.GetLumberCampButton().addActionListener(new BuildTraining(game, view, BuildingId.LumberCamp, pts));
		view.GetMinerCampButton().addActionListener(new BuildTraining(game, view, BuildingId.MinerCamp, pts));
		view.GetQuarrymanCampButton().addActionListener(new BuildTraining(game, view, BuildingId.QuarrymanCamp, pts));
	}
}
