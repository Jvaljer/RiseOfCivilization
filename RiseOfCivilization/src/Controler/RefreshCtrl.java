package Controler;

import View.*;

/**
 * Controler which is used to constantly repaint & revalidate the View.
 * @author abel
 */
public class RefreshCtrl extends Thread {
	private GameView view;
	
	public RefreshCtrl(GameView V) {
		view = V;
	}
	
	@Override
	public void run() {
		while(true) {
			//working but don't know why it is not visually good
			view.repaint();
			view.revalidate();
			
			try {
				Thread.sleep(120);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
