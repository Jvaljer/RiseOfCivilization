package Controler;

import java.awt.event.ActionEvent;
import Types.Actions;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.WorkerCollect;
import Model.*;

/**
 * This class is the the "Collect" Action Controller. It implements
 * ActionListener and is called whenever the "Collect" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionCollect implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
	/**
     * Default Constructor of ActionCollect.
     *
     * @param ctrl the main controller
     */
	public ActionCollect(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}

	/**
	 * Overrrides the super actionPerformed method.
	 * This method, takes the nearest free worker and makes him
	 * move to the clicked It then creates the thread
     * which handles the resource collection.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
		WorkerModel nearest = map_model.GetNearestWorker(cell, Actions.Collect);

		if(nearest != null) {
			(new WorkerCollect(game_ctrl,nearest,cell.GetCoord())).start();
		}
	}

}
