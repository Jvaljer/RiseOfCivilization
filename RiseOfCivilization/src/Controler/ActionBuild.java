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
            
            //before that we want the nearest worker to move there
            //and we also want the player to have enough resources inside his City Inventory
            if(map.CanBuild(cell)) {
            	BuildingId bid;
            	switch (cell.GetId()) {
            		case Mountain:
            			bid = BuildingId.Mine;
            			if(g_ctrl.GetGameModel().PlayerHasEnoughToBuild(bid)) {
            				g_ctrl.GetGameModel().AddBuilding(bid,cell.GetCoord());
            			}
            			break;
            		case Forest:
            			bid = BuildingId.SawMill;
            			if(g_ctrl.GetGameModel().PlayerHasEnoughToBuild(bid)) {
            				g_ctrl.GetGameModel().AddBuilding(bid,cell.GetCoord());
            			}
            			break;
            		case Iron_Deposit:
            			bid = BuildingId.Quarry;
            			if(g_ctrl.GetGameModel().PlayerHasEnoughToBuild(bid)) {
            				g_ctrl.GetGameModel().AddBuilding(bid,cell.GetCoord());
            			}
            			break;
            		case City:
            			//here we wanna give the player the choice between
            			// MinerCamp || LumberCamp
            			break;
            		default:
            			break;
            	}
            }
    }

}
