package tests;

import main.RecursionUtils;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class RecursionUtilsTest extends TestCase {

	@Before
	protected void setUp () throws Exception {
		super.setUp();
	}
	
	@Test
	public void testNumCombinationsDynamic() {
		System.out.println("TESTING NUM COMBINATIONS DYNAMIC PROGRAMMING: ");
		System.out.println("------- (5,2) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(5,2) == 3);
		System.out.println("------- (5,3) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(5,3) == 5);
		System.out.println("------- (6,4) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(6,4) == 9);
		System.out.println("------- (5,5) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(5,5) == 7);
		System.out.println("------- (10,10) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(10,10) == 42);
		System.out.println("------- (15,15) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(15,15) == 176);
		System.out.println("------- (20,20) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(20,20) == 627);
		System.out.println("------- (40,40) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(40,40) == 37338);
		System.out.println("------- (41,41) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(41,41) == 44583);
		System.out.println("------- (100,100) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(100,100) == 190569292);
		System.out.println("------- (101,101) -------");
		assertTrue(RecursionUtils.getNumCombinationsDynamic(101,101) == 214481126);
		System.out.println("Total calls: " + RecursionUtils.getNumCallsDynamic());
	}
	
	@Test
	public void testNumCombinationsNonDynamic() {
		System.out.println("TESTING NUM COMBINATIONS NON-DYNAMIC: ");
		System.out.println("------- (5,2) -------");
		assertTrue(RecursionUtils.getNumCombinations(5,2) == 3);
		System.out.println("------- (5,3) -------");
		assertTrue(RecursionUtils.getNumCombinations(5,3) == 5);
		System.out.println("------- (6,4) -------");
		assertTrue(RecursionUtils.getNumCombinations(6,4) == 9);
		System.out.println("------- (5,5) -------");
		assertTrue(RecursionUtils.getNumCombinations(5,5) == 7);
		System.out.println("------- (10,10) -------");
		assertTrue(RecursionUtils.getNumCombinations(10,10) == 42);
		System.out.println("------- (15,15) -------");
		assertTrue(RecursionUtils.getNumCombinations(15,15) == 176);
		System.out.println("------- (20,20) -------");
		assertTrue(RecursionUtils.getNumCombinations(20,20) == 627);
		System.out.println("------- (40,40) -------");
		assertTrue(RecursionUtils.getNumCombinations(40,40) == 37338);
		System.out.println("------- (41,41) -------");
		assertTrue(RecursionUtils.getNumCombinations(41,41) == 44583);
		System.out.println("------- (100,100) -------");
		assertTrue(RecursionUtils.getNumCombinations(100,100) == 190569292);
		System.out.println("------- (101,101) -------");
		assertTrue(RecursionUtils.getNumCombinations(101,101) == 214481126);
		System.out.println("Total calls: " + RecursionUtils.getNumCallsNonDynamic());
	}


}
