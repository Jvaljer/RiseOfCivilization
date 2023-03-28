package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class ActionMove implements ActionListener {
	private GameCtrl g_ctrl;
	private MapModel map;
	
	public ActionMove(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked Move Button");
		CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
		//WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
		WorkerModel nearest = map.GetNearestWorker(cell, "move");
		if(nearest!=null) {
			System.out.println("move -> selected worker : "+ nearest.ID);
			(new WorkerMoveCtrl(g_ctrl.GetGameModel(),nearest,cell.GetCoord())).start();
		}
	}
	
}
