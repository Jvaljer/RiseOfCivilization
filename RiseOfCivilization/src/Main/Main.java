package Main;

import Model.*;
import View.*;
import Controler.*;
import java.awt.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView(model);
		GameCtrl ctrl = new GameCtrl(view);
		
		Point start = new Point(0,0);
		Point end = new Point(4,2);
		ArrayList<Point> path = model.GetMapModel().GetShortestPath(start, end);
		for(int i=0; i<path.size(); i++) {
			System.out.println("path[" + i + "] = (" + path.get(i).x + "," + path.get(i).y + ")");
		}
	}

}
