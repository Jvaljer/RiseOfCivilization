package Controler;

import Model.*;
import Types.CellId;
import View.*;

/**
 * This is the Controller of a Cell, which contains & defines all behaviors we
 * want a cell to have (continuously and when we click on it). Using a Thread for 
 * the continuous events, and an OnClick method for the click event.
 * @author abel
 */
public class CellCtrl extends Thread {
	/** represents the model of the cell we wanna infer on */
	private CellModel cell_model;
	/** represents the view of the cell we wanna infer on */
	private CellView cell_view;
	/** refers to the Id of the cell, on which its behavior will depend */
	private CellId id;
	
	/**
	 * Constructor, associating all the necessaries variables (Model, View, Id) 
	 * from an already existing Cell View, to link the controler to it & its model.
	 * @param V
	 */
	public CellCtrl(CellView V) {
		cell_view = V;
		cell_model = cell_view.GetCellModel();
		id = cell_model.GetId();
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
		//here we wanna give the possibility for the player to click certain buttons depending on the cell's id and condition
		switch (id) {
			case City :
				
			case Forest :
				
			case Plain :
				
			case None :
				
			default:
				break;
		}
	}
	
	public void UnClick() {
		cell_view.Click();
	}
	
	/** 
	 * Method which dictates & describe the behaviour of the cell throughout the game
	 */
	public void CellBehaviour() {
		switch (id) {
			case City :
				
			case Forest :
				
			case Plain :
				
			case None :
				
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
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}