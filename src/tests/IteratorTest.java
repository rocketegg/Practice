package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import main.Iterator;

import org.junit.Before;
import org.junit.Test;

public class IteratorTest extends TestCase {

	private Iterator<Integer> mySet;
	private static final int setLength = 100;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		mySet = new Iterator<Integer>();
		for (int x = 0; x < setLength; x++) {
			mySet.addElement(new Integer(x));
			System.out.println("Adding " + x);
		}
	}

	@Test
	public void testHasElement() {
		for (int x = 0; x < setLength + 10; x++) {
			if (x < setLength)
				assertTrue(mySet.hasElement(new Integer(x)));
			else
				assertFalse(mySet.hasElement(new Integer(x)));
		}
	}
	
	@Test
	public void testDeleteElement() {
		for (int x = 0; x < setLength + 10; x++) {
			if (x < setLength)
				assertTrue(mySet.deleteElement(new Integer(x)));
			else
				assertFalse(mySet.deleteElement(new Integer(x)));
		}
	}

}
