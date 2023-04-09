package Controler;

import java.awt.event.ActionEvent;
import Types.Actions;
import java.awt.event.ActionListener;
import Model.MapModel;
import Threads.WorkerBuildProduction;
import Threads.WorkerBuildTraining;
import Model.*;
import Types.CellId;
import Types.Goals;


/**
 * This class is the the "Build" Action Controller. It implements
 * ActionListener and is called whenever the "Build" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionBuild implements ActionListener {
    private GameCtrl game_ctrl;
    private MapModel map_model;

    /**
     * Default Constructor of ActionBuild.
     *
     * @param ctrl the main controller
     */
    public ActionBuild(GameCtrl ctrl) {
    	game_ctrl = ctrl;
    	map_model = game_ctrl.GetGameModel().GetMapModel();
    }

    /**
	 * Overrrides the super actionPerformed method.
	 * This method, takes the nearest free worker and makes him
     * move to the clicked cell. It then creates the thread
     * which handles the building selection.
	 * 
	 * @param e the preceding ActionEvent
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
        WorkerModel nearest;

        if(cell.GetId()==CellId.City) {
            nearest = map_model.GetNearestWorker(cell, Actions.Build_Training);
            (new WorkerBuildTraining(game_ctrl,nearest,cell.GetCoord())).start();
            game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.TrainingBuilt);
        } else {
            nearest = map_model.GetNearestWorker(cell, Actions.Build_Production);
            (new WorkerBuildProduction(game_ctrl,nearest,cell.GetCoord())).start();
            game_ctrl.GetGameModel().GetGoals().IncrementGoal(Goals.ProductionBuilt);
        }
    }
}
