package View;

import javax.swing.*;
import Model.*;
import java.awt.*;

public class GameView extends JFrame {
	private GameModel model;
	private MapView map_view;
	private MapModel map_model;
	
	public GameView(GameModel M) {
		model = M;
		map_model = model.GetMapModel();
		map_view = new MapView(map_model);
		setTitle("Rise Of Civilizations");
		add(map_view);
		
		pack();
		setVisible(true);
	}
	
	public GameModel GetGameModel() {
		return model;
	}
	
	public MapView GetMapView() {
		return map_view;
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		
		map_view.DrawMap(G);
	}
}
