package Controler;

import java.awt.event.*;
import Model.InventoryModel;
import Types.Resource;

public class ActionSell implements ActionListener {
	private GameCtrl game;
	private ShopCtrl shop;
	private InventoryModel inventory;
	
	private int sell_value;
	
	private int wood_amount;
	private int stone_amount;
	private int iron_amount;
	
	public ActionSell(GameCtrl GC, ShopCtrl SC) {
		game = GC;
		shop = SC;
		inventory = game.GetGameModel().getInventoryModel();
	}
	
	public void AdjustValues() {
		int wood = inventory.getAmmount(Resource.Wood);
		int stone = inventory.getAmmount(Resource.Stone);
		int iron = inventory.getAmmount(Resource.Iron);
		
		if(wood_amount > wood) {
			wood_amount = wood;
		} 
		if(stone_amount > stone) {
			stone_amount = stone;
		}
		if(iron_amount > iron) {
			iron_amount = iron;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked Sell Button");
		sell_value = shop.GetView().GetSellPrice();
		
		wood_amount = shop.GetView().GetWoodAmount();
		stone_amount = shop.GetView().GetStoneAmount();
		iron_amount = shop.GetView().GetIronAmount();
		
		AdjustValues();
		
		inventory.add(Resource.Gold, sell_value);
		inventory.remove(Resource.Wood, wood_amount);
		inventory.remove(Resource.Stone, stone_amount);
		inventory.remove(Resource.Iron, iron_amount);
		
		shop.GetView().dispose();
	}
}