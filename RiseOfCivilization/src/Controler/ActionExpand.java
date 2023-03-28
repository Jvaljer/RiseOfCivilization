package Controler;

import java.awt.event.ActionEvent;
import Model.*;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionExpand implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionExpand(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked on Expand Button");
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			//WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
			WorkerModel nearest = map.GetNearestWorker(cell, "expand");
			if(nearest!=null) {
				System.out.println("expand -> selected worker : "+ nearest.ID);
				(new WorkerExpand(g_ctrl,nearest,cell.GetCoord())).start();
			}
	}
	
}
