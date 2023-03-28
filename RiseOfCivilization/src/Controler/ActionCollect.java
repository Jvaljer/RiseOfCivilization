package Controler;

import java.awt.event.ActionEvent;
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
			System.out.println("clicked Collect button");
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			//we must first make the nearest worker (first without the right Id) move on the clicked cell
			//WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
			WorkerModel nearest = map.GetNearestWorker(cell, "collect");
			if(nearest!=null) {
				System.out.println("collect -> selected worker : "+ nearest.ID);
				(new WorkerCollect(g_ctrl,nearest,cell.GetCoord())).start();
			}
	}

}
