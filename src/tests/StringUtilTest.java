package tests;


import main.StringUtil;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testATOI() {
		for (int x = 0; x < 100; x++ ) {
			int i = (x % 2 == 0) ? Math.round((float) (Math.random()*100000)) : Math.round((float) (Math.random()*100000)) * -1; 
			String s = new Integer(i).toString();
			int atoi = StringUtil.atoi(s);
			System.out.println("Testing ATOI. String s: [" + s + "] to int i: [" + atoi + "]");
			assertTrue(atoi == i);
		}
	}
	
	@Test
	public void testSubstring() {
		//mypypqmxbdsydnnmjbmcdvoiusronp
		//ypqmxbdsydnnm
		assertTrue(StringUtil.isSubstring("ypqmxbdsydnnm", "mypypqmxbdsydnnmjbmcdvoiusronp") == true);
		
		for (int x = 0; x < 100; x++) {
			String s2 = TestUtils.randomString(30);
			String s1 = TestUtils.randomSubstring(s2);
			System.out.println("Testing if " + s1 + " is substring of " + s2 + ": " + StringUtil.isSubstring(s1, s2));
			assertTrue(StringUtil.isSubstring(s1, s2) == true);
		}
		
		for (int x = 0; x < 100; x++) {
			String s2 = TestUtils.randomString(30);
			String s1 = TestUtils.randomSubstring(s2) + '!';
			System.out.println("Testing if " + s1 + " is substring of " + s2 + ": " + StringUtil.isSubstring(s1, s2));
			assertTrue(StringUtil.isSubstring(s1, s2) == false);
		}
		
	}

}
