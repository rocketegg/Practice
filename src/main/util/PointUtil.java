package main.util;

import java.util.ArrayList;
import java.util.Iterator;

public class PointUtil {

	/**
	 * Returns the k points closest to the origin 
	 * @param points
	 * @return
	 */
	public static Point[] getPointsClosestToOrigin(Point [] points, int k) {
		if (k > points.length) {
			return null;
		} else {
			ArrayList<Point> closestPoints = new ArrayList<Point>(k);
			for (int x = 0; x < points.length; x++) {
				insertInOrder(closestPoints, points[x]);
				//note that you can do this better with a max heap.  Or if you use the binarysearch method,
				//you can insert in O(log(k)) speed.  Having to iterate through the entire array means
				//n log(k) speed.  With O(k) memory usage.
			}
			return closestPoints.subList(0, k).toArray(new Point [k]);
		}
	}
	
	private static void insertInOrder(ArrayList<Point> points, Point in) {
		int x = 0;
		while (x < points.size() && points.get(x) != null && points.get(x).compareTo(in) > 0) {
			x++;
		}
		points.add(x, in);
	}
}
