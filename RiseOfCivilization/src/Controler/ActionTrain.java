package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionTrain implements ActionListener {
	private MapModel map;
	
	public ActionTrain(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked New_Worker button");
			//must implement
	}

}