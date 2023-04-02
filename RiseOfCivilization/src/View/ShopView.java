package View;

import javax.swing.JFrame;
import java.awt.*;
import Model.ShopModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ShopView extends JFrame{
	private GameView game;
	private ShopModel model;
	
	public ShopView(GameView GV, ShopModel SM) {
		game = GV;
		model = SM;
		
		setTitle("Shop Interface");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		
		JPanel pane = new JPanel();
		//set the wanted buttons (sell & buy)
		JButton button = new JButton();
		//set the wanted menus (resource type) 
		
		//set the price indicator
		
		add(pane);
	}
	
	
	@Override
	public void paint(Graphics G) {
		//must implement
		return;
	}
}
