package Model;

import java.awt.*;
import Types.EnnemyRole;

/**
 * This class is responsible for all information and action of a ennemy
 * (Another version existe on the branch test_fog but this time implemented by William)
 * 
 * @author Abel
 */
public class EnnemyModel {
	private GameModel game;
	private MapModel map;
	private Point pos;
	private EnnemyRole role;
	private int init_health_bar;
	private int current_health_bar;
	private static final int width = 10;
	
	public EnnemyModel(GameModel GM, EnnemyRole ER, Point pts) {
		game = GM;
		map = game.GetMapModel();
		role = ER;
		pos = pts;
		
		switch (role) {
			case Wolf:
				init_health_bar = 1;
				break;
			case Ork:
				init_health_bar = 2;
				break;
			default:
				init_health_bar = 0;
				break;
		}
		current_health_bar = init_health_bar;
	}
	
	public int GetCurrentHealth() {
		return current_health_bar;
	}
	
	public void TakeDamage() {
		current_health_bar--;
	}
	
	public boolean IsDead() {
		return current_health_bar==0;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public Point GetPos() {
		return pos;
	}
	
	public void MoveTo(Point coord) {
		pos = coord;
	}
}
