package Controler;

import Model.*;
import Threads.BuildingUpgrade;
import Threads.WorkerUpgrade;
import Types.Actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionLevelUp implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionLevelUp(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			if(map.CellIsOccupiedByWorker(cell)) {
				WorkerModel worker = map.GetWorkerFromCoord(cell.GetCoord());
				if(g_ctrl.GetGameModel().PlayerCanUpgradeWorker(worker)) {
					(new WorkerUpgrade(g_ctrl,worker)).start();
				}
			} else if(map.CellIsOccupiedByBuilding(cell)) {
				WorkerModel nearest = map.GetNearestWorker(cell, Actions.LevelUp);
				BuildingModel building = map.GetBuildingFromCoord(cell.GetCoord());
				if(nearest!=null) {
					(new BuildingUpgrade(g_ctrl,nearest,building)).start();
				}
			}
	}

}
