package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionExpand implements ActionListener {
	private MapModel map;
	private boolean enabled;
	
	public ActionExpand(GameCtrl ctrl) {
		enabled = false;
		map = ctrl.GetGameModel().GetMapModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(enabled) {
			System.out.println("clicked Expand button");
		}
	}
	
}
