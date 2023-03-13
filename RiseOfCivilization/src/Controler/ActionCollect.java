package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionCollect implements ActionListener {
	private MapModel map;
	
	public ActionCollect(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked Collect button");
	}

}
