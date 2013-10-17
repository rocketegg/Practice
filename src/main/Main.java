package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import solver.grid.*;


public class Main {

	public static int countBitsNoLoop(int i) {
		//c is some number of characters
		//converted to a bitvector, how many 1s are there?
		//constraint = no loops
		
		if (i <= 1)
			return i;
		if (i % 2 == 0) {
			return countBitsNoLoop(i/2);
		} else {
			return 1 + countBitsNoLoop(i/2);
		}
	}
	

	public static int countBitsLoop(char [] c) {
		StringBuffer s = new StringBuffer();
		for (char d : c) {
			s.append(Integer.toBinaryString((int)d));
		}
		
		int count = 0;
		for (char e : s.toString().toCharArray()) {
			if (e == '1') {
				count++;
			}
		}
		
		return count;
	}
	
	public static void printBits(char [] c) {
		for (char d : c) {
			System.out.println(Integer.toBinaryString((int)d));
		}
	}
	
	public static boolean isPermutationMethod3(String s, String t) {

		if (s.length() != t.length()) {
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
	
	public static boolean isPermutationMethod2(String s, String t) {
		
		//Bases cases
		//1) if strings are not the same size, they are not permutations so return false
		//don't forget fringe cases
		//  - spaces, capital letters, etc.
		if (s.length() != t.length()) {
			return false;
		}
		
		//2) method 1
		/*
		 * get all permutations of string s, if one of these permutations matches string t, return true
		 * else return false 
		 * this method way too slow - if s gets big, you get a huge number of permutations (n!)
		 */
		
		//3) method 2
		/*
		 * for each char in string s, search through string t removing the character if it matches
		 * if you get to a point where the char in string s in your forloop has no match in string t, return false
		 * else return true
		 * 
		 * worst case scenario, strings are mirror images of each other with unique characters, ends up being O(n2), (e.g. alphabet and reverse alphabet)
		 * implementation is easier
		 * 
		 * t char array needs to be resizeable; best way is to use a hashmap or quickly resizable array
		 * for now, can traverse whole array
		 */
		
		char [] schararray = s.toCharArray();
		char [] tchararray = t.toCharArray();
		char c, d;
		boolean match = true;
		
		for (int i = 0; i < schararray.length; i++) {
			c = schararray[i];
			match = false;
			for (int j = 0; j < tchararray.length; j++) {
				d = tchararray[j];
				
				if (c == d) {
					tchararray[j] = '.';
					c = '.';
					match = true;
				}
			}
			if (match == false) return false;
		}
		return true;
		
		//4) method 3
		/*
		 * put all characters in string s into a hashmap of linked lists
		 * then for each character in string t, remove value from hashmap/linked list,
		 * 	if you can't remove a value or hashmap is empty (or is not empty at end) then return false
		 * else return true
		 */
		
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long startTime = System.currentTimeMillis();

		/* 
		 * Binary Trees
		 */
		BinaryTree b = new BinaryTree();
			
		for (int x = 0; x < 20; x++) {
			Node n = new Node(Math.round((float) (Math.random()*100)));
			System.out.println("inserting: " + n.value);
			if (x == 0)
				 b = new BinaryTree(n);
			else{
				b.insert(n);
			}
		}
		
		b.preOrder();
		System.out.println("Num nodes: " + b.countNodes());
		System.out.println("Num levels: " + b.countLevels());
		System.out.println("Is valid tree?" + b.isValidBinaryTree());
		b.printTree();
		/*
		 * Composition over Inheritance
		 */
		/*InstrumentedHashSet<String> ihs = new InstrumentedHashSet<String>();
		ihs.addAll(Arrays.asList("one", "two", "three"));
		System.out.println(ihs.toString() + " length: " + ihs.getAddCount());
		
		CompositionInstrumentedSet<String> cihs = new CompositionInstrumentedSet<String>(new HashSet<String>());
		cihs.addAll(Arrays.asList("one", "two", "three"));
		System.out.println(cihs.toString() + " length: " + cihs.getAddCount());
		*/
		
		/*
		 * Interfaces
		 */
		
		/*LinkedList<IPest> pests = new LinkedList<IPest>();
		pests.add(new Fly());
		pests.add(new Telemarketer());
		pests.add(new Fly());
		
		for (IPest p : pests) {
			p.beAnnoying();
		}
		
		
		int[] l = new int[100];
		for (int i = 0; i < l.length; i++) {
			l[i] = Math.round((float) (Math.random()*100));
		}*/
		
		//IS PERMUTATION
		/*

		
		String s = "abcdefghijlkmnopqrstuvwxyzabcdefghijlkmnopqrstuvwxyzabcdefghijlkmnopqrstuvwxyz";
		String t = "zyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcba";
		
		//Character array method
		//call it 100 times
		for (int x = 0; x < 50000; x++) {
			isPermutationMethod2(s,t);
		}
		System.out.println("Is permutatation: " + s + " / " + t + " : " + isPermutationMethod2(s,t));
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time Method 2: " + totalTime);
		
		startTime = System.currentTimeMillis();

		//HashTable Method
		for (int x = 0; x < 50000; x++) {
			isPermutationMethod3(s,t);
		}
		System.out.println("Is permutatation: " + s + " / " + t + " : " + isPermutationMethod3(s,t));
		
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Total time Method 3: " + totalTime);
		*/
		
		//Count bits
		
		/*char [] c = new char[3];
		c[0] = 'a';
		c[1] = 'b';
		c[2] = 'c';
		
		printBits(c);
		
		//verification
		System.out.println("Total 1s: " + countBitsLoop(c));
		
		System.out.println("Total 1s: " + countBitsNoLoop((int)c[2]));
		*/
		
		//Linked List Practice
		//add a bunch of values
		/*
		SampleLinkedList myList = new SampleLinkedList();
		myList.add("F");
		myList.add("F");
		myList.add("O");
		myList.add("L");
		myList.add("L");
		myList.add("O");
		myList.add("O");
		myList.add("W");
		myList.add("U");
		myList.add("P");
		myList.add("P");
		myList.add("P");
		
		myList.printValues();
		
		System.out.println("Deleting duplicates");
		myList.deleteDuplicates(); 
		
		myList.printValues();
		*/
		
		//Sum up all int values
		/*Long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
			//if (i % 1000000000 == 0) System.out.println("i: " + i);
		}
		System.out.println(sum);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time taken: " + totalTime);
		
		startTime = System.currentTimeMillis();
		long sumlong = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sumlong += i;
			//if (i % 1000000000 == 0) System.out.println("i: " + i);
		}
		System.out.println(sumlong);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		System.out.println("Total time taken: " + totalTime);*/
		
		/*PhoneNumber myPn = new PhoneNumber(703, 339, 3649);
		PhoneNumber yourPn = new PhoneNumber(703, 339, 3649);
		
		myPn.printPhoneNumber();
		yourPn.printPhoneNumber();
		
		System.out.println("Equal? " + myPn.equals(yourPn));
		
		myPn.printHashCode();
		yourPn.printHashCode();
		
		HashMap<PhoneNumber, String> hashmap = new HashMap<PhoneNumber, String>();
		hashmap.put(myPn, "home");
		System.out.println("Getting hash: " + hashmap.get(yourPn));
		*/
		
		/*
		Grid myGrid = new Grid();
		myGrid.Initialize();
		//myGrid.printGrid();
		myGrid.solve();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time taken: " + totalTime);
		*/
	}

	
}
