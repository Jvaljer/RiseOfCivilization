package Controler;

import Model.*;

public class Checkup extends Thread {
	private GameCtrl game;
	
	public Checkup(GameCtrl GC) {
		game = GC;
	}
	
	@Override
	public void run() {
		while(game!=null) {
			for(WorkerModel w : game.GetGameModel().GetWorkerModel()) {
				if(w.GetOccupied()) {
					System.out.println("worker "+w.ID+" is occupied on slot ("+w.getcoordX()+","+w.getcoordY()+")");
				}
				//System.out.println("amount of running threads : "+game.nb_th);
			}
			
			try {
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
