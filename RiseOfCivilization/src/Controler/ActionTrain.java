package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.BuildingTrain;
import Types.Goals;
import Model.CellModel;
import Model.BuildingModel;

public class ActionTrain implements ActionListener {
	private MapModel map;
	private GameCtrl g_ctrl;
	
	public ActionTrain(GameCtrl ctrl) {
		g_ctrl = ctrl;
		map = g_ctrl.GetGameModel().GetMapModel();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
			CellModel cell = map.GetCurrentCell();
			BuildingModel building = map.GetBuildingFromCoord(cell.GetCoord());
			
			(new BuildingTrain(g_ctrl,building)).start();
			g_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.TrainedWorkers);
	}

}