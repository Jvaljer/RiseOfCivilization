package Controler;

import View.*;

/**
 * Controler which is used to constantly repaint & revalidate the View.
 * @author abel
 */
public class RefreshCtrl extends Thread {
	private GameView view;
	private MapView map_view;
	private DashboardView dashboard_view;
	
	public RefreshCtrl(GameView V) {
		view = V;
		map_view = view.GetMapView();
		dashboard_view = view.GetDashboardView();
	}
	
	@Override
	public void run() {
		while(view.GetGameModel().GetClock().IsTicking()) {
			map_view.revalidate();
			map_view.repaint();
			
			dashboard_view.revalidate();
			dashboard_view .repaint();
			
			try {
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
