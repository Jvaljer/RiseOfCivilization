package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionExpand implements ActionListener {
	private MapModel map;
	
	public ActionExpand(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked Expand button");
	}
	
}
