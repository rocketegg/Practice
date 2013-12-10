package tests;


import java.util.HashMap;

import main.adt.element.Tuple;
import main.util.StringUtil;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.util.StringUtils;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
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
	
	public void testReverseString() {
		String s = "the cat came home";
		assertTrue(StringUtil.reverseWords(s).equals("home came cat the"));
		
		s = "i am going to the store";
		assertTrue(StringUtil.reverseWords(s).equals("store the to going am i"));
		
		s = "turn the t.v. on, dude!";
		assertTrue(StringUtil.reverseWords(s).equals("dude! on, t.v. the turn"));
		
		s = "yes i said it because i can";
		assertTrue(StringUtil.reverseWords(s).equals("can i because it said i yes"));
	}
	*/
	
	/*
	public void testPalindrome() {
		System.out.println("TESTING PALINDROME");
		String s = "the cat came home";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "aabbccbbaa";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "bbcccbb";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "bbcdcbb";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "racecar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "raceeeeeeeecar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "raceeeeeeeedar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "aa";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
		s = "c";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindrome(s));
	}
	
	public void testPalindromeRecursive() {
		System.out.println("TESTING PALINDROME RECURSIVE");
		String s = "the cat came home";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "aabbccbbaa";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "bbcccbb";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "bbcdcbb";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "racecar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "raceeeeeeeecar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "raceeeeeeeedar";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "aa";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
		s = "c";
		System.out.println(s + " is palidrome? " + StringUtil.isPalindromeRecursive(s));
	}
	
	public void testKPalindrome() {
		System.out.println("TESTING K-PALINDROME");
		String s = "malayalxam";
		
		for (int x = 0; x <= 1; x ++) {
			System.out.println(s + " is " + x + " palindrome? " + StringUtil.isKPalindrome(s, x));
		}
	}

	public void testTemp() {
		String s = "dealer-12345-TW-Simplefeed_al";
		String dealerID = s.substring(StringUtil.nthOccurrence(s, '-', 1)+1, StringUtil.nthOccurrence(s, '-', 2));
		String pageID = s.substring(StringUtil.nthOccurrence(s, '-', 3)+1, s.length());
		System.out.println("dealer ID: " + dealerID + " pageID: " + pageID);
		
		s = "dealer-4094-FB-556970337691274";
		dealerID = s.substring(StringUtil.nthOccurrence(s, '-', 1)+1, StringUtil.nthOccurrence(s, '-', 2));
		pageID = s.substring(StringUtil.nthOccurrence(s, '-', 3)+1, s.length());
		System.out.println("dealer ID: " + dealerID + " pageID: " + pageID);
		
		s = "dealer-12345-FB-1234567891011";
		dealerID = s.substring(StringUtil.nthOccurrence(s, '-', 1)+1, StringUtil.nthOccurrence(s, '-', 2));
		pageID = s.substring(StringUtil.nthOccurrence(s, '-', 3)+1, s.length());
		System.out.println("dealer ID: " + dealerID + " pageID: " + pageID);
		
		s = "dealer-12345-TW-Simplefeed-al";
		dealerID = s.substring(StringUtil.nthOccurrence(s, '-', 1)+1, StringUtil.nthOccurrence(s, '-', 2));
		pageID = s.substring(StringUtil.nthOccurrence(s, '-', 3)+1, s.length());
		System.out.println("dealer ID: " + dealerID + " pageID: " + pageID);
		
		s = "dealer-12345-RSS-Simplefeed_al";
		dealerID = s.substring(StringUtil.nthOccurrence(s, '-', 1)+1, StringUtil.nthOccurrence(s, '-', 2));
		pageID = s.substring(StringUtil.nthOccurrence(s, '-', 3)+1, s.length());
		System.out.println("dealer ID: " + dealerID + " pageID: " + pageID);
	}*/
	/*
	public void testValidWord() {
		String s = "iheihrieeihriehhriereiree";
		String letters = "hire";
		System.out.println(s + " is valid? " + StringUtil.isValidWord(s, letters.toCharArray()));
		
		s = "iheihrieeihraiehhriereiaree";
		System.out.println(s + " is valid? " + StringUtil.isValidWord(s, letters.toCharArray()));
		
		String ds = "iehhrierei";
		System.out.println(ds + " is valid? " + StringUtil.isValidWord(ds, letters.toCharArray()));
		
		String ss = ds + "iheihrieeihriehhriereiree";
		System.out.println(ss + " is valid? " + StringUtil.isValidWord(ss, letters.toCharArray()));
		
		s = "iheihrieeihriehhriereiree"; 
		System.out.println(s + " is valid? " + StringUtil.isValidWord(s, letters.toCharArray()));
	}*/
	
	/*
	public void testValidSentence() {
		HashMap<String, Boolean> dictionary = new HashMap<String, Boolean>();
		dictionary.put("the", true);
		dictionary.put("dog", true);
		dictionary.put("went", true);
		dictionary.put("to", true);
		dictionary.put("park", true);
		dictionary.put("in", true);
		dictionary.put("sunshine", true);
		dictionary.put("happy", true);
		dictionary.put("do", true);
		dictionary.put("sun", true);
		dictionary.put("shine", true);
		dictionary.put("sunny", true);
		dictionary.put("on", true);
		dictionary.put("a", true);
		dictionary.put("day", true);
		
		String sentence = "thehappydogwenttotheparkonasunnyday";
		
		System.out.println(sentence + " Reconstructed: " + StringUtil.reconstructSentence(sentence, dictionary));
	}
	*/
	
	public void testAnagram() {
		String a = "a";
		String b = "b";
		
		System.out.println(StringUtil.isAnagram(a, b));
	}
	
	public void testCountChar() {
		String str = "aaaaaabbbbbbbbcccddddd";
		Tuple second = StringUtil.getSecondHighest(str);
		System.out.println(str + ":  Tuple: " + second.toString());
	}
	
}
