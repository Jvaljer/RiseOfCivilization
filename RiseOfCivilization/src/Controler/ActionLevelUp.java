package Controler;

import Model.*;
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
			System.out.println("clicked LevelUp button");
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			if(map.CellIsOccupiedByWorker(cell)) {
				//here we just wanna upgrade the selected worker
				WorkerModel worker = map.GetWorkerFromCoord(cell.GetCoord());
				if(g_ctrl.GetGameModel().PlayerCanUpgradeWorker(worker)) {
					(new WorkerUpgrade(g_ctrl,worker)).start();
				}
			} else if(map.CellIsOccupiedByBuilding(cell)) {
				//here we want a worker to move to the cell and upgrade the building
				//WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
				WorkerModel nearest = map.GetNearestWorker(cell, "upgrade");
				BuildingModel building = map.GetBuildingFromCoord(cell.GetCoord());
				if(nearest!=null) {
					(new BuildingUpgrade(g_ctrl,nearest,building)).start();
				}
			}
	}

}
