package main.util;

import java.util.HashMap;
import java.util.Stack;

public class StringUtil {

	
	/**
	 * converts ascii String s into int
	 * @param s
	 * @return
	 */
	public static int atoi(String s) {
		int i = 0;
		char[]c = s.toCharArray();
		boolean neg = c[0] == '-';
		for (int x = (neg) ? 1 : 0; x < c.length; x++){
			i *= 10;
			i += c[x] - 48;
		}
		return (neg) ? (i * -1) : i;
	}
	
	/**
	 * Reverses the order of words in a string in place (w/o extra memory)
	 * E.g. "the cat came home" = "home came cat the"
	 *   - naive approach would be to break words apart by string.split, then 
	 *     compose the string in reverse order.
	 *   - in place, reverse all the characters, then go through string again
	 *     everywhere you find a word, reverse all characters there
	 *     
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		char[]c = reverseWord(s).toCharArray();
		int wordLength = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ' || i == c.length-1) {
				//reverse
				for (int x = i-wordLength, y = 0; x < i-wordLength/2; x++, y++) {
					char temp = c[x];
					char swap = (i == c.length -1) ? c[i-y] : c[i-1-y];
					c[x] = swap;
					if (i == c.length -1) {
						c[i-y] = temp;
					} else {
						c[i-1-y] = temp;
					}
				}
				wordLength=0;
			} else {
				wordLength++;
			}
		}
		System.out.println("reversed: " + new String(c));
		return new String(c);
		
	}
	
	private static String reverseWord(String s) {
		char[]c = s.toCharArray();
		for (int x = 0; x < c.length/2; x++) {
			char temp = c[x];
			c[x] = c[c.length-1-x];
			c[c.length-1-x] = temp;
		}
		//System.out.println("Reversed:" + c);
		return new String(c);
	}
	
	 /**
	 * Function that determines whether string s1 is a substring of string s2
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isSubstring(String s1, String s2) {
		int s1_ptr = 0;
		int s2_ptr = 0;
		while (s2_ptr < s2.length()) {
			if (s2.charAt(s2_ptr) == s1.charAt(s1_ptr)) {
				s1_ptr++;
				s2_ptr++;
				if (s1_ptr >= s1.length()) {	
					return true;	//reached end of s1 string
				}
			} else if (s1_ptr > 0){
				s1_ptr = 0;
			} else {
				s2_ptr++;
			}
		}	
		return false;	//reached end of s2 string
		
	}
	/*
	A k-palindrome is a string which transforms into a palindrome on removing at most k characters. 

	Given a string S, and an interger K, print "YES" if S is a k-palindrome; otherwise print "NO". 
	Constraints: 
	S has at most 20,000 characters. 
	0<=k<=30 

	Sample Test Case#1: 
	Input - abxa 1 
	Output - YES 
	Sample Test Case#2: 
	Input - abdxa 1 
	Output - No
	*/
	public static boolean isKPalindrome(String s, int k) {
		System.out.println(s + " " + k + "-Palindrome?");

		if (k < 0) {
			System.out.println("\t"+s + " is not" + k + "-palindrome.");
			return false;
		} else if (k == 0 || s == null || s.length() == 1) {
			if (StringUtil.isPalindrome(s)) {
				System.out.println("\t"+s + " is " + k + "-palindrome.");
				return true;
			} else {
				System.out.println("\t"+s + " is not " + k + "-palindrome.");
				return false;
			}
		} else {
			int l = 0;
			int r = s.length()-1;
			if (s.charAt(l) == s.charAt(r)) {
				return (l+1 <= r-1) ? isKPalindrome(s.substring(l+1, r), k) : false;
			} else {
				return isKPalindrome(s.substring(l+1), k-1) ||
						isKPalindrome(s.substring(l, r), k-1);
			}
		}
		
	}
	
	/**
	 * function to determine if a string is a palindrome
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if (s == null || s.length() == 1) 
			return true;
		int l = 0;
		int r = s.length()-1;
		while (l < r && s.charAt(l) == s.charAt(r)) {
			l++;
			r--;
		}
		return (l < r) ? false : true;
	}
	
	/**
	 * function to determine if a string is a palindrome
	 * @param s
	 * @return
	 */
	public static boolean isPalindromeRecursive(String s) {
		if (s == null || s.length() <= 1) 
			return true;
		if (s.charAt(0) == s.charAt(s.length()-1)) {
			return isPalindromeRecursive(s.substring(1, s.length()-1));
		}
		return false;

	}
	
	/**
	 * Returns if String t is a permutation of String s
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isPermutationMethod3(String s, String t) {

		if (s.length() < t.length()) {
			return false;
		}
		
		HashMap<Character, Integer> h = new HashMap <Character, Integer>(s.length());
		
		//step 1 - for each character in a string, add a character key to the map and an int (if null, make it 1) otherwise increment it)
		for (char c : s.toCharArray()) {
			Character cc = new Character(c);
			if (h.containsKey(cc)) {
				int i = h.get(cc);
				i++;
				h.put(cc, new Integer(i));
			} else {
				h.put(cc, new Integer(1));
			}
		}
		
		//step 2 - for each character in t, make sure there is a corresponding value key pair in hashmap
		//if there is one, decrement; if value would be 0, delete
		//if ever you can't find it, return false
		
		for (char d : t.toCharArray()) {
			Character dd = new Character(d);
			if (h.containsKey(dd)) {
				int j = h.get(dd);
				j--;
				if (j == 0) {
					h.remove(dd);
				} else {
					h.put(dd, new Integer(j));
				}
			} else {
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * Returns the index of the nth occurrence of a character
	 * IF there is no nth occurrence, returns -1
	 * @param s
	 * @param c
	 * @param nth
	 * @return
	 */
	public static int nthOccurrence(String s, char c, int nth) {
		char[]chars  = s.toCharArray();
		int matches = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == c) {
				matches++;
				if (matches == nth)
					return i;
			}
		}
		return -1;
	}
	

	private static HashMap<String, Boolean> dictionary = new HashMap<String, Boolean>();
	
	/**
	 * Returns whether a word is a valid "word", i.e. made up of characters
	 * in the character array
	 * @param c
	 * @return
	 */
	public static boolean isValidWord(String s, char [] c) {
		//System.out.println("[" + s + "]");
		if (dictionary.containsKey(s)) {	//word found in dictionary
			//System.out.println("\tFound word in dictionary: " + s);
			return true;
		}
		if (s.length() == 0) //empty words are in the language
			return true;
		boolean goLeft = false;
		boolean goRight = false;
		for (int i = 0; i < c.length; i++) {
			if (s.charAt(0) == c[i])
				goLeft = true;
			if (s.charAt(s.length()-1) == c[i])
				goRight = true;
		}
		if (s.length() <= 1 && goLeft && goRight)
			return true;
		if (goLeft == false || goRight == false) {
			return false;
		} 
		boolean isValid = isValidWord(s.substring(1, s.length()), c) && isValidWord(s.substring(0, s.length()-1), c);
		if (isValid) {
			dictionary.put(s.substring(1, s.length()-1), true);
			//System.out.println("Adding " + s.substring(1, s.length()) + " as valid word.");
		}
		return isValid; 
	}
	
	public static String reconstructSentence(String s, HashMap<String, Boolean> dictionary) {
		int ptr = 0;
		Stack<Integer> spaces = new Stack<Integer>();
		spaces.add(ptr);
		String currWord = "";
		while (!spaces.isEmpty()) {
			currWord = s.substring(spaces.peek(), ptr);
			System.out.println("Curr Word: " + currWord);
			if (dictionary.containsKey(currWord)) {
				System.out.println(">>>Found word: " + currWord);
				spaces.push(ptr);
			}
			if (ptr >= s.length()) {	//reached the end and currWord is still not a word
				if (dictionary.containsKey(currWord)) {
					System.out.println("Sentence is done!");
					spaces.push(ptr);	//Done.
					break;
				}
				System.out.println("Backtracking ...");
				if (spaces.isEmpty()) {
					System.out.println("Sentence could not be reconstructed");
					break;
				} else {
					ptr = spaces.pop();
				}
			}
			ptr++;
		}
		
		//Reconstruct Sentence
		int to = s.length();
		String sentence = "";
		while (!spaces.isEmpty()) {
			int from = spaces.pop();
			String word = s.substring(from, to);
			to = from;
			sentence = word + " " + sentence;
		}
		
		return sentence;
	}
}
