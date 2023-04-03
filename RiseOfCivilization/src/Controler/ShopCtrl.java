package Controler;

import View.*;

public class ShopCtrl {
	private GameCtrl game;
	private ShopView shop;
	
	public ShopCtrl(GameCtrl GC, ShopView SV) {
		game = GC;
		shop = SV;
		shop.GetBuyButton().addActionListener(new ActionBuy(game, this));
		shop.GetSellButton().addActionListener(new ActionSell(game,this));
	}
}
