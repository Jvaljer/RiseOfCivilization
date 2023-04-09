package Model;

import java.awt.Point;

public class EnnemyModel {
	private GameModel model;
	private MapModel mapModel;
	private CellModel cell;
	private boolean moving;
	private Point coordOnScreen;
	private boolean visible;
	private static final int width = 10;
	private static final int height = 10;
	
	
	public EnnemyModel(GameModel model, CellModel cell)
	{
		this.model = model;
		this.cell = cell;
		this.mapModel = model.GetMapModel();
		this.coordOnScreen = this.mapModel.GetPosFromCoord(this.cell.GetX(), this.cell.GetY());
		this.moving = false;
		this.visible = false;
	}
	
	public GameModel getGame()
	{
		return this.model;
	}
	
	public MapModel getMap()
	{
		return this.mapModel;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Point getPos()
	{
		return this.cell.GetCoord();
	}
	
	public boolean getMoving()
	{
		return this.moving;
	}
	
	public void setMoving(boolean b)
	{
		this.moving = b;
	}
	
	public void moveTo(Point p)
	{
		this.cell = this.mapModel.GetCellFromCoord(p.x, p.y);
		this.isVisible();
	}
	
	public void setPosWhileMoving(int i, int j)
	{
		this.coordOnScreen = new Point(i,j);
	}
	
	public Point getPosWhileMoving()
	{
		return this.coordOnScreen;
	}
	
	public void setVisible(boolean b)
	{
		this.visible = b;
	}
	
	public boolean getVisible()
	{
		return this.visible;
	}
	
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