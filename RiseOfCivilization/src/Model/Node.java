package Model;

import java.awt.*;
import java.util.*;

public class Node {
	public int x;
	public int y;
	public boolean visited;
	
	public Node(int i, int j) {
		x = i;
		y = j;
		visited = false;
	}
	
	public ArrayList<Node> GetNeighbors(Node[][] graph){
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		Point[] direction = new Point[6];
		direction[0] = new Point(0,-1);
		direction[1] = new Point(1,-1);
		direction[2] = new Point(1,0);
		direction[3] = new Point(0,1);
		direction[4] = new Point(-1,0);
		direction[5] = new Point(-1,-1);
		
		for(Point dir : direction) {
			int i = dir.x;
			int j = dir.y;
			
			if(x+i > 0 && x+i < 21 && y+j > 0 && y+j < 18) {
				Point pts = new Point(x+i, y+j);
				neighbors.add(graph[pts.x][pts.y]);
			}
		}
		
		return neighbors;
	}
}
