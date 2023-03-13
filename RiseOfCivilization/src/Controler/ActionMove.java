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
			CellModel cell = controler.GetMapCtrl().GetClickedCell();
			WorkerModel nearest_worker = map.GetNearestWorker(cell.GetCoord());
	}
	
}
