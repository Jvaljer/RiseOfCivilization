package Controler;

import Model.*;
import Threads.BuildingUpgrade;
import Threads.WorkerUpgrade;
import Types.Actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class is the the "LevelUp" Action Controller. It implements
 * ActionListener and is called whenever the "LevelUp" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionLevelUp implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
    /**
     * Default Constructor of ActionLevelUp.
     *
     * @param ctrl the main controller
     */
	public ActionLevelUp(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, creates a thread which handles the worker leveling
	 * if the cell is occupied by a worker or creates a thread which
	 * handlesthe building leveling if the cell has a building built
	 * on it.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
		if(map_model.CellIsOccupiedByWorker(cell)) {
			WorkerModel worker = map_model.GetWorkerFromCoord(cell.GetCoord());
			if(game_ctrl.GetGameModel().PlayerCanUpgradeWorker(worker)) {
				(new WorkerUpgrade(game_ctrl,worker)).start();
			}
		} else if(map_model.CellIsOccupiedByBuilding(cell)) {
			WorkerModel nearest = map_model.GetNearestWorker(cell, Actions.LevelUp);
			BuildingModel building = map_model.GetBuildingFromCoord(cell.GetCoord());
			if(nearest!=null) {
				(new BuildingUpgrade(game_ctrl,nearest,building)).start();
			}
		}
	}

}
