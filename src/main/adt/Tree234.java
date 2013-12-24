package main.adt;

/* Tree234.java */
import main.adt.element.Tree234Node;
import main.adt.IntDictionary;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   *
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString or findKey from working correctly.
   **/
  Tree234Node root;

  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /**
   *  findKey() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean findKey(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insertKey() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
 * @throws Exception 
   **/
  public void insertKey(int key) throws Exception {
    // Fill in your solution here.
	  Tree234Node node = root;
	  Tree234Node parent = null;
	  
	  if (root == null) {
		  root = new Tree234Node(parent, key);
	  } else {
		  while (node != null) {
			  //root = new Tree234Node(null, key);
			  if (node.keys == 3) {
				  node = node.isLeaf() ? kickUpLeaf(node, key) : kickUpNonleaf(node, key);
			  } else if (node.isLeaf()) {	// if leaf node
				  node.insert(key);
				  return;
			  } else {	//node is not leaf
				  assert(node.keys < 3);
				  if (node.keys == 1) {
					  if (key < node.key1) {
						  parent = node;
						  node = node.child1;
					  } else if (key > node.key1) {
						  parent = node;
						  node = node.child2;
					  } else {
						  assert (node.key1 == key);
						  return;
					  }
				  } else {
					  assert(node.keys == 2);
					  if (key < node.key1) {
						  parent = node;
						  node = node.child1;
					  } else if (key > node.key1 && key < node.key2) {
						  parent = node;
						  node = node.child2;
					  } else if (key > node.key2) {
						  parent = node;
						  node = node.child3;
					  } else {
						  assert (key == node.key1 || key == node.key2);
						  return;
					  }
				  }
			  }
		  }
		  //node terminated
		  node = new Tree234Node(parent, key);
	  }
  }
  
  
  public Tree234Node kickUpLeaf(Tree234Node node, int key) throws Exception {
	  assert(node.keys == 3);
	  if (node.parent == null) {	//when adjusting root
		  System.out.println("leaf, no parent keys");
		  root = new Tree234Node(null, node.key2);
		  root.child1 = new Tree234Node(root, node.key1);
		  root.child2 = new Tree234Node(root, node.key3);
		  return root;
	  } else {
		  if (node.parent.keys == 1) {
			  System.out.println("leaf, 1 parent key");
			  assert(node.key2 != node.parent.key1);
			  node.parent.insertLeaf(node.key2, node.key1, node.key3);
			  return node.parent;
		  } else if (node.parent.keys == 2) {
			  System.out.println("leaf, 2 parent keys");
			  node.parent.insertLeaf(node.key2, node.key1, node.key3);
			  return getParentChild(node.parent, key);
		  }
		  throw new Exception();
	  }
  }
  
  /*
   * Takes a new node and splits the old node (either left or right) and sets the children of the old node to the new node
   */
  private Tree234Node splitNode(Tree234Node newparent, Tree234Node oldnode, boolean left) {
	  if (left) {
		  Tree234Node newleft = new Tree234Node(newparent, oldnode.key1);
		  newleft.child1 = oldnode.child1;
		  newleft.child2 = oldnode.child2;
		  oldnode.child1.parent = newleft;
		  oldnode.child2.parent = newleft;
		  return newleft;
	  } else {
		  Tree234Node newright = new Tree234Node(newparent, oldnode.key3);
		  newright.child1 = oldnode.child3;
		  newright.child2 = oldnode.child4;
		  oldnode.child3.parent = newright;
		  oldnode.child4.parent = newright;
		  return newright;
	  }
  }
  
  private Tree234Node kickUpNonleaf(Tree234Node node, int key) {
	  if (node.parent == null) {	//root is non-leaf
		  assert(!node.isLeaf());
		  System.out.println("non-leaf, no parent");
		  Tree234Node newleft = splitNode(null, root, true);
		  Tree234Node newright = splitNode(null, root, false);
		  root = new Tree234Node(null, node.key2);
		  root.child1 = newleft;
		  newleft.parent = root;
		  root.child2 = newright;
		  newright.parent = root;
		  return root;
	  } else if (node.parent.keys == 1) {	//parent has 1 key
		  System.out.println("non-leaf, 1 parent key");
		  assert(!node.isLeaf());
		  Tree234Node newleft = splitNode(node.parent, node, true);
		  Tree234Node newright = splitNode(node.parent, node, false);

		  if (node.key2 < node.parent.key1) {
			  node.parent.insert(node.key2);
			  node.parent.child3 = node.parent.child2;
			  node.parent.child2 = newright;
			  node.parent.child1 = newleft;
		  } else {
			  node.parent.insert(node.key2);
			  node.parent.child2 = newleft;
			  node.parent.child3 = newright;
		  }
		  return node.parent;
	  } else {
		  System.out.println("non-leaf, 2 parent keys.");
		  assert(!node.isLeaf() && node.parent.keys == 2);
		  Tree234Node newleft = splitNode(node.parent, node, true);
		  Tree234Node newright = splitNode(node.parent, node, false);
		  if (node.key2 < node.parent.key1 && node.key2 < node.parent.key2) {		//less than both
			  node.parent.insert(node.key2);
			  node.parent.child4 = node.parent.child3;
			  node.parent.child3 = node.parent.child2;
			  node.parent.child2 = newright;
			  node.parent.child1 = newleft;
		  } else if (node.key2 > node.parent.key1 && node.key2 < node.parent.key2) {//middle
			  node.parent.insert(node.key2);
			  node.parent.child4 = node.parent.child3;
			  node.parent.child3 = newright;
			  node.parent.child2 = newleft;
		  } else {																	//greater than both
			  node.parent.child3 = newleft;
			  node.parent.child4 = newright;
		  }
		  return getParentChild(node.parent, key); 
	  }
  }
  
  /**
   * Helper function that gets the appropriate child, but only if the parent is full.
   * @param parent
   * @param key
   * @return
   */
  private Tree234Node getParentChild(Tree234Node parent, int key) {
	  assert(parent.keys == 3);
	  if (key < parent.key1) { 
		  return parent.child1;
	  } else if (key < parent.key2) {
		  return parent.child2;
	  } else if (key < parent.key3) {
		  return parent.child3;
	  } else if (key > parent.key3) {
		  return parent.child4;
	  } else {
		  return null;
	  }
  }

  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    try {
    
    System.out.println("\nInserting 84.");
    t.insertKey(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insertKey(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insertKey(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insertKey(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insertKey(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insertKey(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insertKey(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insertKey(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insertKey(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insertKey(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insertKey(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insertKey(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insertKey(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insertKey(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insertKey(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insertKey(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insertKey(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insertKey(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insertKey(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insertKey(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insertKey(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insertKey(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }

}
