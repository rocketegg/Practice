//package main.adt;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//import main.Node;
//
//public class BinaryTreeBackup {
//
//	Node root;
//	BinaryTree left;
//	BinaryTree right;
//	ArrayList<int[]> dataRep;
//	
//	public BinaryTreeBackup() {
//		this.root = new Node();
//		left = null;
//		right = null;
//		dataRep = new ArrayList<int[]>();
//	}
//	
//	public BinaryTreeBackup(Node node) {
//		this.root = node;
//		left = null;
//		right = null;
//		dataRep = new ArrayList<int[]>();
//	}
//	
//	/**
//	 * Insert in order, assuming root is not null
//	 * @param n
//	 */
//	public void insert(Node n) {
//		if (root.value > n.value) {
//			if (left == null) {
//				left = new BinaryTree(n);
//			} else {
//				left.insert(n);
//			}
//		} else {
//			if (right == null) {
//				right = new BinaryTree(n);
//			} else {
//				right.insert(n);
//			}
//		}
// 	}
//	
//	public BinaryTreeBackup find(int value) {
//		if (root != null && root.value == value) {
//			return this;
//		} else {
//			if (root != null && root.value > value) {
//				return (left != null) ? left.find(value) : null;
//			} else {
//				return (right != null) ? right.find(value) : null;
//			}
//		}
//	}
//	
//	public BinaryTree getSmallest() {
//		if (left == null) {
//			return this;
//		} else {
//			return left.getSmallest();
//		}
//	}
//	
//	public BinaryTree getBiggest() {
//		if (right == null) {
//			return this;
//		} else {
//			return right.getBiggest();
//		}
//	}
//	
//	public BinaryTree findParent(int value) {
//		BinaryTree prev = this;
//		BinaryTree curr = this;
//		while (curr != null) {
//			if (curr.root.value == value) {
//				break;
//			} else if (curr.root.value > value) {
//				prev = curr;
//				curr = curr.left;
//			} else {
//				prev = curr;
//				curr = curr.right;
//			}
//		}
//		if (curr == null)
//			return null; // value not in there
//		return prev;
//	}
//	
//	public BinaryTree delete(int value) {
//		BinaryTree toDelete = find(value);
//		BinaryTree parent = findParent(value);
//		
//		if (toDelete.left == null && toDelete.right == null) {	//no children
//			if (parent.left == toDelete)
//				parent.left = null;
//			else if (parent.right == toDelete)
//				parent.right = null;
//			else if (toDelete == parent) 
//				this.root = null;
//		} else if (toDelete.left != null && toDelete.right != null) {	//two children
//			BinaryTree min = toDelete.right.getSmallest();
//			BinaryTree minParent = findParent(min.root.value);
//			if (minParent == toDelete)
//				parent = minParent;
//			parent.root.value = min.root.value;
//			if (minParent == parent) {
//				if (min.right != null)
//					minParent.right = min.right;
//				else 
//					minParent.right = null;
//			} else {
//				if (min.right != null) {
//					minParent.left = min.right;
//				} else {
//					minParent.left = null;
//				}
//			}
//		} else {	//1 child
//			if (toDelete == parent) {
//				return (toDelete.left == null) ? toDelete.right : toDelete.left;
//			} else {
//				if (toDelete.root.value < parent.root.value) {
//					if (toDelete.left == null) parent.left = toDelete.right; else parent.left = toDelete.left;
//				} else {
//					if (toDelete.left == null) parent.right = toDelete.right; else parent.right = toDelete.left;
//				}
//			}
//		} 
//		return this;
//		
//	}
//	
//	
//	
//	public void inOrder() {
//		if (left != null) left.inOrder();
//		if (root!= null) System.out.println(root.value);
//		if (right != null) right.inOrder();
//	}
//	
//	public void preOrder() {
//		if (root != null) System.out.println(root.value);
//		if (left != null) left.preOrder();
//		if (right != null) right.preOrder();
//	}
//	
//	public int countNodes() {
//		if (left == null && right == null) 
//			return 1;
//		else if (left == null) 
//			return 1 + right.countNodes();
//		else if (right == null )
//			return 1 + left.countNodes();
//		else 
//			return 1 + left.countNodes() + right.countNodes();
//	}
//	
//	public int countLevels() {
//		if (left == null && right == null) 
//			return 0;
//		else if (left == null) 
//			return 1 + right.countLevels();
//		else if (right == null)
//			return 1 + left.countLevels();
//		else
//			return 1 + max(left.countLevels(), right.countLevels());
//	}
//	
//	private int max (int left, int right) {
//		if (left < right)
//			return right;
//		return left;
//	}
//	
//	public Node leastCommonAncestor(int v1, int v2) {
//		return leastCommonAncestor(false, v1, v2);
//	}
//	
//	/**
//	 * This function returns the least common ancestor of two values within the Binary Tree
//	 * @param v1
//	 * @param v2
//	 * @return
//	 */
//	private Node leastCommonAncestor(boolean ancestor, int v1, int v2) {
//		if (root == null) {
//			return null;
//		} /*else {
//			System.out.println("root is: " + root);
//		}*/
//		if (left != null && left.hasValue(v1) && left.hasValue(v2)) {
//			//System.out.println("left has: " + v1 + " and " + v2);
//			return left.leastCommonAncestor(true, v1, v2);
//		} else if (right != null && right.hasValue(v1) && right.hasValue(v2)) {
//			//System.out.println("right has: " + v1 + " and " + v2);
//			return right.leastCommonAncestor(true, v1, v2);
//		} else if (left != null && right != null) {
//			if (left.hasValue(v1) && right.hasValue(v2)) {
//				//System.out.println("left has: " + v1 + " and right has: " + v2);
//				return root;
//			} else if (left.hasValue(v2) && right.hasValue(v1)) {
//				//System.out.println("left has: " + v2 + " and right has: " + v1);
//				return root;
//			} else {
//				return (ancestor) ? root : null;
//			}
//		} else 
//			return (ancestor) ? root : null;
//	}
//	
//	public boolean hasValue(int value) {
//		if (root == null) {
//			return false;
//		} else if (root.value == value) {
//			return true;
//		} else {
//			boolean l = false;
//			boolean r = false;
//			if (left != null)
//				l = left.hasValue(value);
//			if (right != null)
//				r = right.hasValue(value);
//			return l || r;
//		}
//		//return (left != null) ? left.hasValue(value) : false || (right != null) ? right.hasValue(value) : false;
//	}
//	
//	/**
//	 * Prints levels of the binary tree.  Uses a BinaryTreeTuple which
//	 * contains a BinaryTree and an int representing the "level" in the master tree
//	 * 
//	 * This algorithm basically follows BFS
//	 */
//	public void printLevels() {
//		ConcurrentLinkedQueue<BinaryTreeTuple> q = new ConcurrentLinkedQueue<BinaryTreeTuple>(); 
//		if (root != null) {
//			q.add(new BinaryTreeTuple(this, 1));
//			int prevLevel = 1;
//			while (!q.isEmpty()) {
//				BinaryTreeTuple t = q.remove();
//				BinaryTree n = t.bt;
//				if (t.level > prevLevel) {
//					System.out.print("\nLevel " + t.level + ": ");
//					prevLevel = t.level;
//				} 
//				System.out.print(n.root + " ");
//				if (n.left != null)
//					q.add(new BinaryTreeTuple(n.left, t.level + 1));
//				if (n.right != null)
//					q.add(new BinaryTreeTuple(n.right, t.level + 1));
//			}
//		}
//		System.out.println();
//	}
//	
//	private class BinaryTreeTuple {
//		public BinaryTree bt;
//		public int level;
//		
//		public BinaryTreeTuple(BinaryTree bt, int level) {
//			this.bt = bt;
//			this.level = level;
//		}
//	}
//}
//package main.adt;
//
//public class BinaryTreeBackup {
//
//}
