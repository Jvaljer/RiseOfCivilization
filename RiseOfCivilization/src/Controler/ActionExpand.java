package Controler;

import java.awt.event.ActionEvent;
import Model.*;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.WorkerExpand;
import Types.Actions;
import Types.Goals;


/**
 * This class is the the "Expand" Action Controller. It implements
 * ActionListener and is called whenever the "Drop" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionExpand implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
	/**
     * Default Constructor of ActionExpand.
     *
     * @param ctrl the main controller
     */
	public ActionExpand(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, takes the nearest worker and if the
	 * inventory has enought resources, it creates the thread
     * which handles the city expension.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
		WorkerModel nearest = map_model.GetNearestWorker(cell, Actions.Expand);
		
		if(nearest != null && game_ctrl.GetGameModel().PlayerHasEnoughToExpand(cell)) {
			(new WorkerExpand(game_ctrl,nearest,cell.GetCoord())).start();
			game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.ExpandedSlots);
		}
	}
	
}
