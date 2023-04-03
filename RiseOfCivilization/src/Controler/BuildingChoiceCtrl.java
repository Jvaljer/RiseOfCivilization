package Controler;

import Model.BuildingChoiceModel;
import View.BuildingChoiceView;

public class BuildingChoiceCtrl {
	private GameCtrl game;
	private BuildingChoiceView view;
	private BuildingChoiceModel model;
	
	public BuildingChoiceCtrl(GameCtrl GC, BuildingChoiceView BCV) {
		game = GC;
		view = BCV;
		model = view.GetModel();
		
		view.GetBarrackButton().addActionListener(new BuildBarrack());
		view.GetLumberCampButton().addActionListener(new BuildLulberCamp());
		view.GetMinerCampButton().addActionListener(new BuildMinerCamp());
		view.GetQuarrymanCampButton().addActionListener(new BuildQuarrymanCamp());
	}
}
