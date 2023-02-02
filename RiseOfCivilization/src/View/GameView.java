package View;

import javax.swing.*;
import Model.*;

public class GameView extends JFrame {
	private GameModel model;
	private MapView map_view;
	
	public GameView(GameModel M) {
		model = M;
		map_view = new MapView(model.GetMapModel());
		setTitle("Rise Of Civilizations");
		add(map_view);
		
		pack();
		setVisible(true);
	}
}
