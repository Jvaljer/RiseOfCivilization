package View;

import javax.swing.*;
import Model.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	private GameModel model;
	private MapModel map_model;
	private InventoryModel inventory_model;
	
	private MapView map_view;
	private InfobarView infobar_view;
	private MinimapView minimap_view;
	private InventoryView inventory_view;
	private WorkerView player_view;
	
	public GameView(GameModel m) {
		model = m;
		map_model = model.GetMapModel();
		inventory_model = model.getInventoryModel();
		
		map_view = new MapView(map_model);
		
		minimap_view = new MinimapView(map_model);
		inventory_view = new InventoryView(map_model, inventory_model);
		infobar_view = new InfobarView(map_model, minimap_view, inventory_view);
		
		player_view = new WorkerView(this);
		
		setTitle("Rise Of Civilizations");
		setLayout(new BorderLayout());
		
		add(map_view, BorderLayout.CENTER);
		add(infobar_view, BorderLayout.EAST);

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
		inventory_view.drawInventory(G);
		player_view.DrawPlayer(G);
	}
}
