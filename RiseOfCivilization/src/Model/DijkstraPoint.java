package Model;

public class DijkstraPoint {
	public int x;
	public int y;
	public int dist;
	public boolean visited;
	
	public DijkstraPoint(int i, int j, int d) {
		x = i;
		y = j;
		dist = d;
		visited = false;
	}
}
