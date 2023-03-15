package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class ActionMove implements ActionListener {
	private GameCtrl controler;
	private MapModel map;
	
	public ActionMove(GameCtrl ctrl) {
		controler = ctrl;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked Move Button");
	}
	
}
