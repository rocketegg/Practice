package tests;

import java.util.Stack;

import org.junit.Before;

import solver.game.hanoi.Disc;
import solver.game.hanoi.HanoiSolver;

import junit.framework.TestCase;

public class HanoiTest extends TestCase {

	Stack<Disc> s1;
	Stack<Disc> s2;
	Stack<Disc> s3;
	HanoiSolver hs;
	
	@Before
	public void setUp() {
		s1 = new Stack<Disc>();
		s2 = new Stack<Disc>();
		s3 = new Stack<Disc>();
		/*for (int i = 4; i > 0; i--) {
			s1.push(new Disc(i));
		}*/
		s1.push(new Disc(5));
		s1.push(new Disc(3));
		s2.push(new Disc(2));
		s1.push(new Disc(1));
		s3.push(new Disc(4));
		hs = new HanoiSolver(s1, s2, s3);
	}
	
	/*public void testPrint() {
		hs.printStacks();
	}*/
	
	public void testSolvee() {
		hs.solve(s2);
		System.out.println("Move Count: " + hs.getMoveCount());
	}
	
}
