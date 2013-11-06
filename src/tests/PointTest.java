package tests;

import main.util.ArrayUtil;
import main.util.Point;
import main.util.PointUtil;

import org.junit.Before;

import junit.framework.TestCase;

public class PointTest extends TestCase {

	Point [] points = new Point[10];
	
	@Before
	public void setUp() {
		for (int x = 0; x < 10; x++) {
			int x_ = TestUtils.randomInt(0, 10);
			int y_ = TestUtils.randomInt(0, 10);
			points[x] = new Point(x_,y_);
		}
	}
	
	public void testClosestPoints() {
		//ArrayUtil.printArrayValueOnly(points);
		Point [] closest5 = PointUtil.getPointsClosestToOrigin(points, 5);
		//ArrayUtil.printArrayValueOnly(closest5);
	}
}
