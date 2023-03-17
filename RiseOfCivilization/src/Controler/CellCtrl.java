package Controler;

import Model.*;
import Types.CellId;
import View.*;
import java.util.*;

import javax.swing.*;
import Types.Resource;

/**
 * This is the Controller of a Cell, which contains & defines all behaviors we
 * want a cell to have (continuously and when we click on it). Using a Thread for 
 * the continuous events, and an OnClick method for the click event.
 * 
 * @author abel
 * @author martin
 */
public class CellCtrl extends Thread {
	/**represents the ctrl of the whole game*/
	private GameCtrl g_ctrl;
	/**represents the view of the whole game*/
	private GameView g_view;
	/**represents the map model*/
	private MapModel map;
	/** represents the model of the cell we wanna infer on */
	private CellModel cell_model;
	/** represents the view of the cell we wanna infer on */
	private CellView cell_view;
	/** refers to the Id of the cell, on which its behavior will depend */
	private CellId id;
	/** refers to the actions buttons*/
	private ArrayList<JButton> buttons;
	
	/**
	 * Constructor, associating all the necessaries variables (Model, View, Id) 
	 * from an already existing Cell View, to link the controler to it & its model.
	 * @param V
	 */
	public CellCtrl(GameCtrl G, CellView V) {
		g_ctrl = G;
		g_view = g_ctrl.GetGameView();
		buttons = g_ctrl.GetButtons();
		cell_view = V;
		cell_model = cell_view.GetCellModel();
		id = cell_model.GetId();
		map = g_ctrl.GetGameModel().GetMapModel();
	}
	
	/**
	 * Getter for the model associated to this controller
	 * @return cell_model, the model we take the informations from and which we
	 * 		   wanna possibly infer on.
	 */
	public CellModel GetCellModel() {
		return cell_model;
	}
	
	/**
	 * Getter for the view associated to this controller
	 * @return cell_view, the view we take the informations from and which we
	 * 		   wanna possibly infer on.
	 */
	public CellView GetCellView() {
		return cell_view;
	}
	
	/**
	 * Method which defines the 'OnClick' behavior of the cell, depending on 
	 * its Id. 
	 */
	public void OnClick() {
		cell_view.Click();
		//here we wanna give the possibility for the player to click certain buttons
		if(map.CellIsOccupiedByBuilding(cell_model)) {
			g_ctrl.GetButtonFromName("Move").setEnabled(false);
			g_ctrl.GetButtonFromName("Collect").setEnabled(true);
			g_ctrl.GetButtonFromName("LevelUp").setEnabled(true);
			g_ctrl.GetButtonFromName("Expand").setEnabled(map.CanExpand(cell_model));
			g_ctrl.GetButtonFromName("Build").setEnabled(false);
			g_ctrl.GetButtonFromName("Train").setEnabled(true);
			
		} else if(map.CellIsOccupiedByWorker(cell_model)) {
			g_ctrl.GetButtonFromName("Move").setEnabled(false);
			g_ctrl.GetButtonFromName("Collect").setEnabled(map.CanCollect(cell_model));
			g_ctrl.GetButtonFromName("LevelUp").setEnabled(true);
			g_ctrl.GetButtonFromName("Expand").setEnabled(map.CanExpand(cell_model));
			g_ctrl.GetButtonFromName("Build").setEnabled(map.CanBuild(cell_model));
			g_ctrl.GetButtonFromName("Train").setEnabled(false);
			
		} else {
			g_ctrl.GetButtonFromName("Move").setEnabled(true);
			g_ctrl.GetButtonFromName("Collect").setEnabled(map.CanCollect(cell_model));
			g_ctrl.GetButtonFromName("LevelUp").setEnabled(false);
			g_ctrl.GetButtonFromName("Expand").setEnabled(map.CanExpand(cell_model));
			g_ctrl.GetButtonFromName("Build").setEnabled(map.CanBuild(cell_model));
			g_ctrl.GetButtonFromName("Train").setEnabled(false);
			
		}
		
	}
	
	public void UnClick() {
		cell_view.Click();
	}
	
	/** 
	 * Method which dictates & describe the behaviour of the cell throughout the game
	 */
	public void CellBehaviour() {
		InventoryModel cell_inventory = cell_model.getInventory();
		
		if(map.CellIsOccupiedByBuilding(cell_model)) {
			BuildingModel building = map.GetBuildingFromCoord(cell_model.GetCoord());
			InventoryModel inventory = building.GetInventory();
			switch (building.GetId()) {
				case SawMill:
					inventory.add(Resource.Wood, 16);
					break;
				case Mine:
					inventory.add(Resource.Stone, 10);
					break;
				case Quarry:
					inventory.add(Resource.Iron, 5);
					break;
					
				default:
					break;
			}
		}
		switch (id) {
			case City :
				break;
			case Forest :
				cell_inventory.add(Resource.Wood,8);
				break;
			case Plain :
				break;
			case Mountain :
				cell_inventory.add(Resource.Stone, 5);
				break;
			case Iron_Deposit : 
				cell_inventory.add(Resource.Iron, 2);
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * method that is started when ".start()" is called on it, and which continuously calls
	 * to the "CellBehaviour()" method to simulate a cell behavior
	 * @Override from the Thread class, allowing us to run this controler as one 
	 */
	public void run() {
		//must implement
		while(true) {
			CellBehaviour();
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}