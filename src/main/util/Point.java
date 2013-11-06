package main.util;

public class Point implements Comparable<Point>{

	private int x;
	private int y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Double distFromOrigin() {
		return Math.sqrt(x*x + y*y);
	}
	
	@Override
	public int compareTo(Point o) {
		if (this.distFromOrigin() < o.distFromOrigin())
			return -1;
		else if (this.distFromOrigin() > o.distFromOrigin())
			return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
