package Controler;

import java.awt.event.*;

public class ActionSell implements ActionListener {
	private GameCtrl game;
	private ShopCtrl shop;
	
	public ActionSell(GameCtrl GC, ShopCtrl SC) {
		game = GC;
		shop = SC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked Sell Button");
		//must implement
		shop.GetView().dispose();
	}
}
