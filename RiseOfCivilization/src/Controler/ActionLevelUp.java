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
			} else if(map.CellIsOccupiedByWorker(cell)) {
				//here we want a worker to move to the cell and upgrade the building
			}
	}

}
