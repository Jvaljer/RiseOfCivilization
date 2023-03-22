package Controler;

import Model.MapModel;

public class WorkersCtrl extends Thread{
	private GameCtrl ctrl;
	private MapModel map;
	
	public WorkersCtrl(GameCtrl GC) {
		ctrl = GC;
		map = ctrl.GetGameModel().GetMapModel();
	}
}
