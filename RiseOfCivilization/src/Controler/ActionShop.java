package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.*;


/**
 * This class is the the "Shop" Action Controller. It implements
 * ActionListener and is called whenever the "Shop" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionShop implements ActionListener{
	private GameCtrl game_ctrl;
	
	/**
     * Default Constructor of ActionShop.
     *
     * @param ctrl the main controller
     */
	public ActionShop(GameCtrl ctrl) {
		game_ctrl = ctrl;
	}

	/**
	 * Overrrides the super actionPerformed method.
	 * This method, creates the MVC objects for the
	 * shop.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ShopModel model = new ShopModel(game_ctrl.GetGameModel());
		ShopView view = new ShopView(game_ctrl.GetGameView(), model);
		new ShopCtrl(game_ctrl, view);
	}
}
