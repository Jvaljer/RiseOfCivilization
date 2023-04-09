package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import Threads.WorkerMoveCtrl;
import Types.Actions;

/**
 * This class is the the "Move" Action Controller. It implements
 * ActionListener and is called whenever the "Move" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionMove implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
    /**
     * Default Constructor of ActionMove.
     *
     * @param ctrl the main controller
     */
	public ActionMove(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, takes the nearest free worker and creates the
	 * thread that handles the worker movement.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
		WorkerModel nearest = map_model.GetNearestWorker(cell, Actions.Move);
		if(nearest != null) {
			(new WorkerMoveCtrl(game_ctrl.GetGameModel(),nearest,cell.GetCoord())).start();
		}
	}
	
}
