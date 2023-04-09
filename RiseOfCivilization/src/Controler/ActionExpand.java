package Controler;

import java.awt.event.ActionEvent;
import Model.*;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.WorkerExpand;
import Types.Actions;
import Types.Goals;

public class ActionExpand implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionExpand(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			WorkerModel nearest = map.GetNearestWorker(cell, Actions.Expand);
			if(nearest!=null && g_ctrl.GetGameModel().PlayerHasEnoughToExpand(cell)) {
				(new WorkerExpand(g_ctrl,nearest,cell.GetCoord())).start();
				g_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.ExpandedSlots);
			}
	}
	
}
