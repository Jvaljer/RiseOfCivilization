package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import Types.BuildingId;

public class ActionDrop implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionDrop(GameCtrl ctrl) {
		map = ctrl.GetGameModel().GetMapModel();
		g_ctrl = ctrl;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println("clicked Drop button");
			CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
			if(map.CellIsOccupiedByBuilding(cell)) {
				if(map.GetBuildingFromCoord(cell.GetCoord()).getId()==BuildingId.CityHall) {
					for(WorkerModel worker : g_ctrl.GetGameModel().GetWorkerModel()) {
						if(!worker.GetOccupied()) {
							(new WorkerDrop(g_ctrl,worker)).start();
						}
					}
				}
			} else {
				//we must first make the nearest worker (first without the right Id) move on the clicked cell
				WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
				if(nearest!=null) {
					(new WorkerDrop(g_ctrl,nearest)).start();
				}
			}
	}
}
