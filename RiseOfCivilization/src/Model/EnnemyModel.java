package Model;

import java.awt.Point;

/**
 * This is the Model of the ennemy (simple version) 
 * This class will have all the information of the ennmy in the game
 * 
 * @author William
 */
public class EnnemyModel {
	// The "Main" model that have all the information of the game running
	private GameModel model;
	// Model of the map 
	private MapModel mapModel;
	// Model of the Cell where the ennemy is currently on
	private CellModel cell;
	// Boolean that represent if the ennemy is mooving
	private boolean moving;
	// Position on the screen of the ennemy
	private Point coordOnScreen;
	// Boolean that say if the ennemy is visible on screen (If it's on the fog of war or not)
	private boolean visible;

	// Height and Width of the circle that represent the ennemy on the screeen
	private static final int width = 10;
	private static final int height = 10;
	
	/**
	 * Constructor of for the EnnemyModel 
	 * @param model model of the game
	 * @param cell cell where the ennemy will spawn
	 */
	public EnnemyModel(GameModel model, CellModel cell)
	{
		this.model = model;
		this.cell = cell;
		this.mapModel = model.GetMapModel();
		this.coordOnScreen = this.mapModel.GetPosFromCoord(this.cell.GetX(), this.cell.GetY());
		this.moving = false;
		this.visible = false;
	}
	
	/**
	 * Getter for the GameModel
	 * @return this.model
	 */
	public GameModel getGame()
	{
		return this.model;
	}
	
	/**
	 * Getter for the map
	 * @return this.mapModel
	 */
	public MapModel getMap()
	{
		return this.mapModel;
	}
	
	/**
	 * Getter for the width of the ennemy
	 * @return this.width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Getter for the Height of the ennemy
	 * @return this.height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Getter for the position on the map of the ennemy
	 * @return this.cell.GetCoord()
	 */
	public Point getPos()
	{
		return this.cell.GetCoord();
	}
	
	/**
	 * Getter for the boolean Moving
	 * @return this.moving
	 */
	public boolean getMoving()
	{
		return this.moving;
	}
	
	/**
	 * Setter for the boolean moving
	 * @param b boolean that indicat if the ennemy is moving or not
	 */
	public void setMoving(boolean b)
	{
		this.moving = b;
	}
	
	/**
	 * the moveTo Method update the position of the ennemy and recalcul if the ennemy is Visible or not
	 * @param p new position of the ennemy
	 */
	public void moveTo(Point p)
	{
		this.cell = this.mapModel.GetCellFromCoord(p.x, p.y);
		this.isVisible();
	}
	
	/**
	 * setPosWhileMoving Method update the Coordonate on the screen of the ennemy
	 * @param i x pos on the screen
	 * @param j y pos on the screen
	 */
	public void setPosWhileMoving(int i, int j)
	{
		this.coordOnScreen = new Point(i,j);
	}
	
	/**
	 * Getter for the position on the screen
	 * @return this.coordOnScreen
	 */
	public Point getPosWhileMoving()
	{
		return this.coordOnScreen;
	}
	
	/**
	 * Setter for the visible boolean
	 * @param b boolean that indicate if the ennemy will be visible
	 */
	public void setVisible(boolean b)
	{
		this.visible = b;
	}
	
	/**
	 * Getter for the variable visible
	 * @return this.visible
	 */
	public boolean getVisible()
	{
		return this.visible;
	}
	
	/**
	 * The isVisible Method update if the ennemy is visible for the player
	 */
	public void isVisible()
	{
		this.setVisible(false);
		for(int i = 0; i < this.model.GetWorkerModel().size(); i++)
		{
			if(this.mapModel.GetShortestPath(this.model.GetWorkerModel().get(i).getPos(), this.getPos()).size() <= 3)
			{
				this.setVisible(true);
			}
		}
	}
}