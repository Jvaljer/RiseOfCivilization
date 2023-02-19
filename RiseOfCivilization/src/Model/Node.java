package Model;

import java.awt.*;
import java.util.*;

public class Node {
	private MapModel map;
	public int x;
	public int y;
	public boolean visited;
	
	public Node(MapModel M, int i, int j) {
		map = M;
		x = i;
		y = j;
		visited = false;
	}
	
	public ArrayList<Node> GetNeighbors(Node[][] graph){
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		ArrayList<Point> pts_neighbors = map.GetNeighbours(x, y);
		
		for(Point pts : pts_neighbors) {
			System.out.println("the point (" + pts.x + "," + pts.y + ") is a neighbor");
			neighbors.add(graph[pts.x][pts.y]);
		}
		return neighbors;
	}
}
