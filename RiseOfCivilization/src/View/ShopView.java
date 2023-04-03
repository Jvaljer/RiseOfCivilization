package View;

import javax.swing.JFrame;
import java.awt.*;
import Model.ShopModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ShopView extends JFrame {
	private GameView game;
	private ShopModel model;
	
	public ShopView(GameView GV, ShopModel SM) {
		game = GV;
		model = SM;
		//must implement
	}
	
	
	@Override
	public void paint(Graphics G) {
		//must implement
		return;
	}
	
}
