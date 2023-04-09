package Controler;

import java.awt.event.*;

import Model.InventoryModel;
import Types.Resource;

public class ActionBuy implements ActionListener {
	private GameCtrl game;
	private ShopCtrl shop;
	private InventoryModel inventory;
	
	private int sell_value;
	
	private int wood_amount;
	private int stone_amount;
	private int iron_amount;
	
	public ActionBuy(GameCtrl GC, ShopCtrl SC) {
		game = GC;
		shop = SC;
		inventory = game.GetGameModel().getInventoryModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sell_value = shop.GetView().GetSellPrice();
		
		wood_amount = shop.GetView().GetWoodAmount();
		stone_amount = shop.GetView().GetStoneAmount();
		iron_amount = shop.GetView().GetIronAmount();
		
		inventory.remove(Resource.Gold, sell_value);
		inventory.add(Resource.Wood, wood_amount);
		inventory.add(Resource.Stone, stone_amount);
		inventory.add(Resource.Iron, iron_amount);
		
		shop.GetView().dispose();
	}
}
