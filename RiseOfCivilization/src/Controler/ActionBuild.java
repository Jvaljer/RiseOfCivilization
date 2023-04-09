package Controler;

import java.awt.event.ActionEvent;
import Types.Actions;
import java.awt.event.ActionListener;
import Model.MapModel;
import Types.BuildingId;
import Model.*;
import Types.CellId;

public class ActionBuild implements ActionListener {
    private MapModel map;
    private GameCtrl g_ctrl;

    public ActionBuild(GameCtrl ctrl) {
    	g_ctrl = ctrl;
        map = ctrl.GetGameModel().GetMapModel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
            WorkerModel nearest;
            if(cell.GetId()==CellId.City) {
            	nearest = map.GetNearestWorker(cell, Actions.Build_Training);
           		(new WorkerBuildTraining(g_ctrl,nearest,cell.GetCoord())).start();
           	} else {
           		nearest = map.GetNearestWorker(cell, Actions.Build_Production);
           		(new WorkerBuildProduction(g_ctrl,nearest,cell.GetCoord())).start();
           	}
    }

}
