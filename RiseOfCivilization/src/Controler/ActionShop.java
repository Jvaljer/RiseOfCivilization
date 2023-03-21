package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class ActionShop implements ActionListener{
	private GameCtrl g_ctrl;
	private MapModel map;
	
	public ActionShop(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//must implement 
		return;
	}
}
