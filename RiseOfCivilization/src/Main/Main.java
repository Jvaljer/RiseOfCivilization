package Main;

import Model.*;
import View.*;
import Controler.*;
import java.awt.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		Point start = new Point(0,0);
		Point end = new Point(3,3);
		ArrayList<Point> path = model.GetMapModel().GetShortestPath(start, end);
		System.out.println("here is the path : "+path);
		GameView view = new GameView(model);
		GameCtrl ctrl = new GameCtrl(view);
	}

}
