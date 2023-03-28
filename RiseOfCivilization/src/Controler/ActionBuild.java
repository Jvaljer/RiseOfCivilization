package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;
import Types.BuildingId;
import Model.*;

public class ActionBuild implements ActionListener {
    private MapModel map;
    private GameCtrl g_ctrl;

    public ActionBuild(GameCtrl ctrl) {
    	g_ctrl = ctrl;
        map = ctrl.GetGameModel().GetMapModel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("clicked Build button");
            CellModel cell = g_ctrl.GetMapCtrl().GetClickedCell();
            //WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
            WorkerModel nearest = map.GetNearestWorker(cell, "build");
            if(nearest!=null) {
            	System.out.println("build -> selected worker : "+ nearest.ID);
            	(new WorkerBuild(g_ctrl,nearest,cell.GetCoord())).start();
            }
    }

}
