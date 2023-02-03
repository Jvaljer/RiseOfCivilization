package Main;

import Model.*;
import View.*;
import Controler.*;

public class Main {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView(model);
		GameCtrl ctrl = new GameCtrl(view);

	}

}
