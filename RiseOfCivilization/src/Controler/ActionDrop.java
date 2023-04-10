package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import Threads.WorkerDrop;
import Types.BuildingId;
import Types.WorkerRole;

/**
 * This class is the the "Drop" Action Controller. It implements
 * ActionListener and is called whenever the "Drop" button of the
 * Main Controller is pressed.
 * 
 * @author Abel
 */
public class ActionDrop implements ActionListener {
	private GameCtrl game_ctrl;
    private MapModel map_model;
	
    /**
     * Default Constructor of ActionDrop.
     *
     * @param ctrl the main controller
     */
	public ActionDrop(GameCtrl ctrl) {
		game_ctrl = ctrl;
		map_model = game_ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * Overrrides the super actionPerformed method.
	 * This method, takes the nearest free worker and makes him
	 * move to the clicked cell to drop his resources if the
	 * clicked cell has the City Hall or makes him drop his
	 * resources if he is already on the City Hall cell. It
	 * then creates the thread which handles the resource droping.
	 * 
	 * @param e the preceding ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CellModel cell = game_ctrl.GetMapCtrl().GetClickedCell();
		if(map_model.CellIsOccupiedByBuilding(cell)) {
			if(map_model.GetBuildingFromCoord(cell.GetCoord()).getId() == BuildingId.CityHall) {
				for(WorkerModel worker : game_ctrl.GetGameModel().GetWorkerModel()) {
					if(!worker.GetOccupied() && (worker.GetRole()!=WorkerRole.Knight)) {
						(new WorkerDrop(game_ctrl, worker)).start();
					}
				}
			}
		} else {
			// We must first make the nearest worker (first without the right Id) move on the clicked cell
			WorkerModel nearest = map_model.GetNearestWorker(cell.GetCoord());
			if(nearest != null) {
				(new WorkerDrop(game_ctrl, nearest)).start();
			}
		}
	}
}
