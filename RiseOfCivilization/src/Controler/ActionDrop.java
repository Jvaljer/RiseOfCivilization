package Controler;

import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDrop implements ActionListener {
	private boolean enabled;
	private MapModel map;
	
	public ActionDrop(GameCtrl ctrl) {
		enabled = false;
		map = ctrl.GetGameModel().GetMapModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(enabled) {
			System.out.println("clicked Drop button");
		}
	}

}
