package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class ActionMove implements ActionListener {
	private GameCtrl controler;
	private MapModel map;
	private boolean enabled;
	
	public ActionMove(GameCtrl ctrl) {
		controler = ctrl;
		enabled = false;
		map = ctrl.GetGameModel().GetMapModel();
	}
	
	public void Enable() {
		enabled = true;
	}
	public void Disable() {
		enabled = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(enabled) {
			CellModel cell = controler.GetMapCtrl().GetClickedCell();
			WorkerModel nearest_worker = map.GetNearestWorker(cell.GetCoord());
		}
	}
	
}
