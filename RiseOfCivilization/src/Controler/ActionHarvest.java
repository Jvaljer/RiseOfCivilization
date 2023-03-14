package Controler;

import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHarvest implements ActionListener {
	private MapModel map;
	
	public ActionHarvest(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked Drop button");
	}

}
