package Model;

import Types.*;
import java.awt.*;

public class BuildingModel {
	private GameModel game;
	private MapModel map;
	private CellModel[][] model_grid;
	private CellModel cell;
	private BuildingId id;
	private InventoryModel inventory;
	
	private static final int width = 20;
	private static final int height = 15;
	
	public BuildingModel(GameModel G, Point C, BuildingId bid) {
		game = G;
		map = game.GetMapModel();
		model_grid = map.GetGrid();
		cell = model_grid[C.x][C.y];
		id = bid;
		inventory = new InventoryModel(200);
	}
	
	public BuildingId GetId() {
		return id;
	}
	public Point GetPos() {
		return cell.GetCoord();
	}
	public int GetWidth() {
		return width;
	}
	public int GetHeight() {
		return height;
	}
	public InventoryModel GetInventory() {
		return inventory;
	}
}
