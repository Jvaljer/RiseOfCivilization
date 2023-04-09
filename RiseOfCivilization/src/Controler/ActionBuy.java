package Controler;

import java.awt.event.*;

import Model.InventoryModel;
import Types.Goals;
import Types.Resource;

public class ActionBuy implements ActionListener {
	private GameCtrl game;
	private ShopCtrl shop;
	private InventoryModel inventory;
	
	private int buy_value;
	
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
		buy_value = shop.GetView().GetSellPrice();
		
		wood_amount = shop.GetView().GetWoodAmount();
		stone_amount = shop.GetView().GetStoneAmount();
		iron_amount = shop.GetView().GetIronAmount();
		
		inventory.remove(Resource.Gold, buy_value);
		inventory.add(Resource.Wood, wood_amount);
		inventory.add(Resource.Stone, stone_amount);
		inventory.add(Resource.Iron, iron_amount);
		
		game.GetGameView().getCityInfoView().update();
		shop.GetView().dispose();
		
		game.GetGameModel().GetGoals().IncrementGoal(Goals.BoughtResources, Math.round(buy_value/100));
	}
}
