package Controler;

import java.awt.event.*;
import java.util.ArrayList;
import View.BuildingChoiceView;
import Types.BuildingId;
import Types.WorkerRole;
import java.awt.*;
import Types.Actions;
import Model.WorkerModel;
import Model.MapModel;
import Model.CellModel;

public class BuildTraining implements ActionListener {
	private GameCtrl g_ctrl;
	private MapModel map;
	private BuildingChoiceView choice_window;
	private BuildingId wanted_building;
	private CellModel dst_cell;
	private Point pos;
	
	public BuildTraining(GameCtrl GC, BuildingChoiceView BCV, BuildingId bid, Point pts) {
		g_ctrl = GC;
		map = g_ctrl.GetGameModel().GetMapModel();
		choice_window = BCV;
		wanted_building = bid;
		pos = pts;
		dst_cell = map.GetCellFromCoord(pos.x, pos.y);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("a click has been performed inside the window");
		WorkerModel nearest;
		switch (wanted_building) {
			case Barrack:
				System.out.println("we want a BARRACK");
				nearest = map.GetNearestWorkerFromRole(dst_cell, WorkerRole.Worker);
				break;
				
			case LumberCamp:
				System.out.println("we want a LUMBERCAMP");
				nearest = map.GetNearestWorkerFromRole(dst_cell, WorkerRole.LumberJack);
				break;
				
			case MinerCamp:
				System.out.println("we want a MINECAMP");
				nearest = map.GetNearestWorkerFromRole(dst_cell, WorkerRole.Miner);
				break;
				
			case QuarrymanCamp:
				System.out.println("we want a QUARRYMANCAMP");
				nearest = map.GetNearestWorkerFromRole(dst_cell, WorkerRole.QuarryMan);
				break;
				
			default:
				nearest = null;
				break;
		}
		
		choice_window.dispose();
		System.out.println("disposed the frame");
		
		nearest.occupied();
		nearest.moving();
		System.out.println("starting the worker's move along the path");
		while (nearest.getcoordX() != pos.x || nearest.getcoordY() != pos.y){
			System.out.print("tryna ");
			try {
				System.out.print("move");
				System.out.println("");
				ArrayList<Point> path = map.GetShortestPath(nearest.getPos(),pos);
 				Point nxt_coord = path.get(1);
 				nearest.setNextcoord(nxt_coord);
				Point cord_src = map.GetPosFromCoord(nearest.getcoordX(), nearest.getcoordY());
		        Point cord_dst = map.GetPosFromCoord(nxt_coord.x, nxt_coord.y);
		        int x_src = cord_src.x - nearest.getWidth()/2;
		        int y_src = cord_src.y - nearest.getHeight()/2;
		        int x_dst = cord_dst.x - nearest.getWidth()/2;
		        int y_dst = cord_dst.y - nearest.getHeight()/2;
			    for(int i = 1; i <= 24; i++){
			        int x = (int) ((x_dst - x_src) * i/ 24) + x_src;
			        int y = (int) ((y_dst - y_src) * i/ 24) + y_src;
			        nearest.setPosWhileMoving(x, y);
					Thread.sleep(1000/48);
			    }
			    nearest.MoveTo(nxt_coord.x, nxt_coord.y);
			} catch (Exception e_) {
				System.out.println("Error in Move Worker");
				e_.printStackTrace();
			}
		}
		nearest.stopMoving();
		
		int old_len = g_ctrl.GetGameModel().GetBuildingList().size();
		CellModel cell = map.GetCellFromCoord(pos.x, pos.y);
		g_ctrl.GetGameModel().BuildOnCityCell(wanted_building, cell);
		int new_len = g_ctrl.GetGameModel().GetBuildingList().size();
		if(old_len < new_len) {
			try {
				Thread.sleep(5000);
			} catch (Exception e_) {
				e_.printStackTrace();
			}
			System.out.println("we created a new building for sure");
			g_ctrl.GetGameView().AddBuildingView(g_ctrl.GetGameModel().GetBuildingList().get(old_len));
		}
		nearest.Free();
	}
}
