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
	// Main Model of this game it gather all information of the game
	private GameModel game;
	// Model of the map
	private MapModel map;
	// Position on the map of the ennemy
	private Point pos;
	// Role of the ennemy
	private EnnemyRole role;
	// Health at begining of the ennemy
	private int init_health_bar;
	// Current state of the health of the ennemy
	private int current_health_bar;
	// COnst of the width of the ennemy
	private static final int width = 10;
	
	/**
	 * Constructor of the EnnemyModel
	 * 
	 * @param GM GameModel
	 * @param ER EnnemyRole
	 * @param pts Position on the map
	 */
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
	
	/**
	 * Getter for the CurrentHealth of the ennemy
	 * @return this.current_health_bar
	 */
	public int GetCurrentHealth() {
		return current_health_bar;
	}
	
	/**
	 * TakeDamage Method 
	 * decrease the life of the ennemy
	 */
	public void TakeDamage() {
		current_health_bar--;
	}
	
	/**
	 * The IsDead Function return a boolean that say id the ennemy has no HP 
	 * @return this.current_health_bar==0
	 */
	public boolean IsDead() {
		return current_health_bar==0;
	}
	
	/**
	 * Getter for the width const
	 * @return this.width
	 */
	public int GetWidth() {
		return width;
	}
	
	/**
	 * Getter for the position on the map of thge ennemy
	 * @return this.pos
	 */
	public Point GetPos() {
		return pos;
	}
	
	/**
	 * The MoveTo Method change the position on the map of the ennemy
	 * @param coord destination coord where the ennemy move
	 */
	public void MoveTo(Point coord) {
		pos = coord;
	}
}
