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
		//if you wanna switch the game's duration then you can switch the 2nd and 3rd parameters (minutes & seconds)
		GameCtrl ctrl = new GameCtrl(view,5,0);
	}

}
