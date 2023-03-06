package Controler;

import View.*;

/**
 * Controler which is used to constantly repaint & revalidate the View.
 * @author abel
 */
public class RefreshCtrl extends Thread {
	private GameView view;
	private MapView map_view;
	
	public RefreshCtrl(GameView V) {
		view = V;
		map_view = view.GetMapView();
	}
	
	@Override
	public void run() {
		while(true) {
			map_view.revalidate();
			map_view.repaint();
			try {
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
