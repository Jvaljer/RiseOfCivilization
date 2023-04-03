package Controler;

import java.awt.event.*;

public class ActionBuy implements ActionListener {
	private GameCtrl game;
	private ShopCtrl shop;
	
	public ActionBuy(GameCtrl GC, ShopCtrl SC) {
		game = GC;
		shop = SC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked Buy Button");
		//must implement
		return;
	}
}
