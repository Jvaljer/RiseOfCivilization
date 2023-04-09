package Controler;

import java.awt.event.ActionEvent;
import Types.Actions;
import java.awt.event.ActionListener;
import Model.MapModel;
import Model.*;

public class ActionCollect implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionCollect(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
		g_ctrl = ctrl;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			WorkerModel nearest = map.GetNearestWorker(cell, Actions.Collect);
			if(nearest!=null) {
				(new WorkerCollect(g_ctrl,nearest,cell.GetCoord())).start();
			}
	}

}
