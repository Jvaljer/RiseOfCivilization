package View;

import Model.*;

public class BuildingView {
	private GameView game;
	private MapModel map;
	private BuildingModel model;
	
	public BuildingView(GameView G, BuildingModel M) {
		game = G;
		map = game.GetGameModel().GetMapModel();
		model = M;
	}
}
