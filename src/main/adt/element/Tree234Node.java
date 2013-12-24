package main.adt.element;

/* Tree234Node.java */
/**
 *  A Tree234Node is a node in a 2-3-4 tree (Tree234 class).
 *
 *  DO NOT CHANGE ANYTHING IN THIS FILE.
 *  You may add helper methods and additional constructors, though.
 **/
public class Tree234Node {

  /**
   *  keys is the number of keys in this node.  Always 1, 2, or 3.
   *  key1 through key3 are the keys of this node.  If keys == 1, the value
   *    of key2 doesn't matter.  If keys < 3, the value of key3 doesn't matter.
   *  parent is this node's parent; null if this is the root.
   *  child1 through child4 are the children of this node.  If this is a leaf
   *    node, they must all be set to null.  If this node has no third and/or
   *    fourth child, child3 and/or child4 must be set to null.
   **/
  public int keys;
  public int key1;
  public int key2;
  public int key3;
  public Tree234Node parent;
  public Tree234Node child1;
  public Tree234Node child2;
  public Tree234Node child3;
  public Tree234Node child4;
  

  public Tree234Node(Tree234Node p, int key) {
    keys = 1;
    key1 = key;
    parent = p;
    child1 = null;
    child2 = null;
    child3 = null;
    child4 = null;
  }
  
  public boolean isLeaf() {
	  return child1 == null && child2 == null && child3 == null && child4 == null;	
  }
  
  public void insert(int key) {
	  assert (keys < 3);	//can't insert into a Tree234Node if it already has 3 keys
	  if (keys == 1) {
		  if (key < this.key1) {
			  int temp = key1;
			  this.key1 = key;
			  this.key2 = temp;
			  keys++;
		  } else if (key > this.key1) {
			  this.key2 = key;
			  keys++;
		  } else {
			  assert (key == this.key1);
		  }
	  } else {	//keys == 2
		  if (key < this.key1) {
			  int temp = this.key2;
			  this.key2 = this.key1;
			  this.key1 = key;
			  this.key3 = temp;
			  keys++;
		  } else if (key > this.key1 && key < this.key2) {
			  int temp = this.key2;
			  this.key2 = key;
			  this.key3 = temp;
			  keys++;
		  } else if (key > this.key2) {
			  this.key3 = key;
			  keys++;
		  } else {	//key must equal key 1 or key 2
			  assert (key == this.key1 || key == this.key2);
		  }
	  }
  }
  
  public void insert(int newkey, Tree234Node newleft, Tree234Node newright) {
	  if (this.keys == 1) {
		  if (newkey < this.key1) {
			  insert(newkey);
			  child3 = child2;
			  child2 = newright;
			  child1 = newleft;
		  } else {
			  insert(newkey);
			  child3 = newright;
			  child2 = newleft;
		  }
	  } else if (this.keys == 2) {
		  if (newkey < this.key1 && newkey < this.key2) {		//less than both
			  insert(newkey);
			  child4 = child3;
			  child3 = child2;
			  child2 = newright;
			  child1 = newleft;
		  } else if (newkey > this.key1 && newkey < this.key2) {//middle
			  insert(newkey);
			  child4 = child3;
			  child3 = newright;
			  child2 = newleft;
		  } else { //greater than both
			  insert(newkey);
			  child4 = newright;
			  child3 = newleft;
		  }
	  }
  }
  
  /**
   *  toString() recursively prints this Tree234Node and its descendants as
   *  a String.  Each node is printed in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.
   **/
  public String toString() {
    String s = "";

    if (child1 != null) {
      s = "(" + child1.toString() + ")";
    }
    s = s + key1;
    if (child2 != null) {
      s = s + "(" + child2.toString() + ")";
    } else if (keys > 1) {
      s = s + " ";
    }
    if (keys > 1) {
      s = s + key2;
      if (child3 != null) {
        s = s + "(" + child3.toString() + ")";
      } else if (keys > 2) {
        s = s + " ";
      }
    }
    if (keys > 2) {
      s = s + key3;
      if (child4 != null) {
        s = s + "(" + child4.toString() + ")";
      }
    }
    return s;
  }

}