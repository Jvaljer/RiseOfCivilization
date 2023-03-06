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
	}

}
