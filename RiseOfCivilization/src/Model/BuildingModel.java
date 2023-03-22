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
	private int level;
	private static final int rec_width = 20;
	private static final int rec_height = 15;
	private static final int triangle_dist = 10;
	private static final int poly_dist = 5;
	
	public BuildingModel(GameModel G, Point C, BuildingId bid) {
		game = G;
		map = game.GetMapModel();
		model_grid = map.GetGrid();
		cell = model_grid[C.x][C.y];
		id = bid;
		inventory = new InventoryModel(200);
		level = 1;
	}
	
	public BuildingId GetId() {
		return id;
	}
	public Point GetPos() {
		return cell.GetCoord();
	}
	public int GetRecWidth() {
		return rec_width;
	}
	public int GetRecHeight() {
		return rec_height;
	}
	public int GetTriangleDist() {
		return triangle_dist;
	}
	public int GetPolyDist() {
		return poly_dist;
	}
	public InventoryModel GetInventory() {
		return inventory;
	}
	public int GetLevel() {
		return level;
	}
}
