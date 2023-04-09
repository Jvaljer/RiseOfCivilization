package Controler;

import java.awt.event.*;
import Model.InventoryModel;
import Types.Goals;
import Types.Resource;

/**
 * This class is the the "Buy" Action Controller. It implements
 * ActionListener and is called whenever the "Buy" button of the
 * Shop Controller is pressed.
 * 
 * @author Abel
 */
public class ActionBuy implements ActionListener {
	private GameCtrl game_ctrl;
	private ShopCtrl shop_ctrl;
	private InventoryModel inventory_model;
	private int buy_value;
	private int wood_amount;
	private int stone_amount;
	private int iron_amount;
	
	/**
     * Default Constructor of ActionCollect.
     *
     * @param ctrl the main controller
     * @param sc   the shop controller
     */
	public ActionBuy(GameCtrl ctrl, ShopCtrl sc) {
		game_ctrl = ctrl;
		shop_ctrl = sc;
		inventory_model = game_ctrl.GetGameModel().getInventoryModel();
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, removes gold from the global inventory and
	 * adds resources instead according to the buy rates.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		buy_value = shop_ctrl.GetView().GetSellPrice();
		wood_amount = shop_ctrl.GetView().GetWoodAmount();
		stone_amount = shop_ctrl.GetView().GetStoneAmount();
		iron_amount = shop_ctrl.GetView().GetIronAmount();
		
		inventory_model.remove(Resource.Gold, buy_value);
		inventory_model.add(Resource.Wood, wood_amount);
		inventory_model.add(Resource.Stone, stone_amount);
		inventory_model.add(Resource.Iron, iron_amount);
		
		game_ctrl.GetGameView().getCityInfoView().update();
		shop_ctrl.GetView().dispose();
		game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.BoughtResources, Math.round(buy_value/100));
	}
}
