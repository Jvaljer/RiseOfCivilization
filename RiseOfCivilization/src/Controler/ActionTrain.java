package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.BuildingTrain;
import Types.Goals;
import Model.CellModel;
import Model.BuildingModel;


/**
 * This class is the the "Shop" Action Controller. It implements
 * ActionListener and is called whenever the "Shop" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionTrain implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
    /**
     * Default Constructor of ActionTrain.
     *
     * @param ctrl the main controller
     */
	public ActionTrain(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}

	/**
	 * Overrrides the super actionPerformed method.
	 * This method, creates the thread which handles the
	 * worker creation.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = map_model.GetCurrentCell();
		BuildingModel building = map_model.GetBuildingFromCoord(cell.GetCoord());
		
		(new BuildingTrain(game_ctrl,building)).start();
		game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.TrainedWorkers);
	}

}