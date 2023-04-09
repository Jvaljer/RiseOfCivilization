package Controler;

import java.awt.event.*;
import Model.InventoryModel;
import Types.Goals;
import Types.Resource;
import View.ShopView;


/**
 * This class is the the "Sell" Action Controller. It implements
 * ActionListener and is called whenever the "Sell" button of the
 * Shop Controller is pressed.
 * 
 * @author Abel
 */
public class ActionSell implements ActionListener {
	private GameCtrl game_ctrl;
	private ShopCtrl shop_ctrl;
	private InventoryModel inventory_model;
	private int sell_price;
	private int wood_amount;
	private int stone_amount;
	private int iron_amount;
	
	/**
     * Default Constructor of ActionCollect.
     *
     * @param ctrl the main controller
     * @param sc   the shop controller
     */
	public ActionSell(GameCtrl ctrl, ShopCtrl sc) {
		game_ctrl = ctrl;
		shop_ctrl = sc;
		inventory_model = game_ctrl.GetGameModel().getInventoryModel();
	}
	
	public void AdjustValues() {
		int wood_available = inventory_model.getAmount(Resource.Wood);
		int stone_available = inventory_model.getAmount(Resource.Stone);
		int iron_available = inventory_model.getAmount(Resource.Iron);
		
		wood_amount = Math.min(wood_amount, wood_available);
		stone_amount = Math.min(stone_amount, stone_available);
		iron_amount = Math.min(iron_amount, iron_available);
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, removes the minimum between the desired amount
	 * and the available amount of resources from the global
	 * inventory and adds gold instead according to the sell rates.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ShopView shop_view = shop_ctrl.GetView();
		
		sell_price = shop_view.GetSellPrice();
		wood_amount = shop_view.GetWoodAmount();
		stone_amount = shop_view.GetStoneAmount();
		iron_amount = shop_view.GetIronAmount();
		
		AdjustValues();
		
		inventory_model.add(Resource.Gold, sell_price);
		inventory_model.remove(Resource.Wood, wood_amount);
		inventory_model.remove(Resource.Stone, stone_amount);
		inventory_model.remove(Resource.Iron, iron_amount);
		
		game_ctrl.GetGameView().getCityInfoView().update();
		shop_ctrl.GetView().dispose();
		game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.SoldResources, Math.round(sell_price/100));
	}
}
