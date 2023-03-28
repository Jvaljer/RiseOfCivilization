package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

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
			//we must first make the nearest worker (first without the right Id) move on the clicked cell
			WorkerModel nearest = map.GetNearestWorker(cell.GetCoord());
			if(nearest!=null) {
				System.out.println("drop -> selected worker : "+ nearest.ID);
				(new WorkerDrop(g_ctrl,nearest)).start();
			}
	}
}
