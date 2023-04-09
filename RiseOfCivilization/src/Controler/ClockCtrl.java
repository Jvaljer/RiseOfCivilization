package Controler;

import Model.ClockModel;

public class ClockCtrl extends Thread {
	private ClockModel model;
	private int min_lim;
	private int sec_lim;
	
	public ClockCtrl(ClockModel CM, int lm, int ls) {
		model = CM;
		min_lim = lm;
		sec_lim = ls;
	}
	
	@Override
	public void run() {
		model.StartsTicking();
		while(!model.HasReached(min_lim, sec_lim)) {
			try {
				this.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.ReachedSecond();
			if(model.GetSeconds()==60) {
				model.ReachedMinute();
			}
		}
		model.StopsTicking();
	}
}
