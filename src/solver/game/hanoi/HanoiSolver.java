package solver.game.hanoi;

import java.util.Stack;

public class HanoiSolver {

	private Stack<Disc> stack1;
	private Stack<Disc> stack2;
	private Stack<Disc> stack3;
	private int moveCount;
	private boolean stack1_used;
	private boolean stack2_used;
	private boolean stack3_used;
	
	public HanoiSolver(Stack<Disc> s1,Stack<Disc> s2,Stack<Disc> s3) {
		this.stack1 = s1;
		this.stack2 = s2;
		this.stack3 = s3;
		moveCount = 0;
	}
	
	/**
	 * This function is the only public function that allows the user to specify the destination stack.
	 * 	This will move every disc to the destination stack in a (hopefully) minimal # of moves
	 * @param destination
	 */
	public void solve(Stack<Disc> destination) {
		System.out.println("Initial State: ");
		printStacks();
		System.out.println("Solving... ");
		
		int maxDiscs = findMaxDisc();
		
		if (findStack(maxDiscs) != destination) {	//ensure that the n-1 stack is moved to the nondestination stack to free the biggest disc to move to the destination stack
			solve(getUnusedStack(findStack(maxDiscs), destination), maxDiscs-1);
		}
		solve(destination, maxDiscs);
	}
	
	/**
	 * This function moves n discs to the destination stack if necessary.  It needs to be used in conjunction
	 * with the solve(destination) method above, which recursively calls this function with alternating
	 * stacks to guarantee no unnecessary moves. 
	 * 
	 * The algorithm is:
	 * 		1) Find the stack with the smallest disc (size 1)
	 * 			A) Find the number of discs movable
	 * 		2) Find the stack with the next biggest disc (number movable discs + 1)
	 * 		3) If there are less movable than the number of discs required to be moved:
	 * 			A) If this number if n - 1 and you can move the last disc instead of building on top, do that
	 * 			B) If this number is n - 1 and the n - 1 stack is the destination, move it to the unused stack
	 * 			C) Otherwise, just move the stack with the smallest disc on top of the stack with the next biggest disc 
	 * @param destination
	 * @param numDiscs
	 */
	private void solve(Stack<Disc> destination, int numDiscs) {
		int movable;
		do { 
			Stack<Disc> fromStack = findStack(1);	
			movable = findNumMovable(fromStack);	
			Stack<Disc> toStack = findStack(movable+1);		
			if (movable < numDiscs) {
				if (movable == numDiscs - 1 && toStack != destination && fromStack != destination) {	
					moveDiscs(toStack, destination, fromStack, 1);
				} else if (movable == numDiscs - 1 && fromStack == destination) { 
					moveDiscs(fromStack, getUnusedStack(fromStack, toStack), toStack, movable);
				} else {
					moveDiscs(fromStack, toStack, getUnusedStack(fromStack, toStack), movable);
				}
			} 
		} while (movable < numDiscs);
	}
	
	private int findNumMovable(Stack<Disc> stack) {
		Disc[] s1 = stack.toArray(new Disc[stack.size()]);
		int next = stack.peek().getSize();
		int movable = 0;
		for (int i = s1.length-1; i >= 0; i--) {
			if (s1[i].getSize() == next) {
				movable++;
				next++;
			} else {
				break;
			}
		}
		return movable;
	}
	
	private int findMaxDisc() {
		return stack1.size() + stack2.size() + stack3.size();
	}
	
	/**
	 * Returns the stack with disc of size n
	 * @param val
	 * @return
	 */
	private Stack<Disc> findStack(int size) {
		Disc[] s1 = stack1.toArray(new Disc[stack1.size()]);
		Disc[] s2 = stack2.toArray(new Disc[stack2.size()]);
		Disc[] s3 =  stack3.toArray(new Disc[stack3.size()]);
		
		for (Disc d: s1) {
			if (d.getSize() == size) {
				return stack1;
			}
		}
		for (Disc d: s2) {
			if (d.getSize() == size) {
				return stack2;
			}
		}
		for (Disc d: s3) {
			if (d.getSize() == size) {
				return stack3;
			}
		}
		return null;	//error
		
	}
	
	private Stack<Disc> getUnusedStack(Stack<Disc> fromStack, Stack<Disc> toStack) {
		resetFlags();
		if (fromStack == stack1 || toStack == stack1) {
			stack1_used = true;
		}
		
		if (fromStack == stack2 || toStack == stack2) {
			stack2_used = true;
		}
		
		if (fromStack == stack3 || toStack == stack3) {
			stack3_used = true;
		}
		
		if (stack1_used && stack2_used) {
			System.out.println("stack 3 is unused");
			return stack3;
		} else if (stack2_used && stack3_used) {
			System.out.println("stack 1 is unused");
			return stack1;
		} else {
			System.out.println("stack 2 is unused");
			return stack2;
		}
	}
	
	private void resetFlags() {
		stack1_used = stack2_used = stack3_used = false;
	}
	
	public void moveDiscs(Stack<Disc> fromStack, Stack<Disc> toStack, Stack<Disc> thirdStack, int discs) {
		if (discs == 1) {
			move(fromStack, toStack);
		} else {
			moveDiscs(fromStack, thirdStack, toStack, discs-1);
			moveDiscs(fromStack, toStack, thirdStack, 1);
			moveDiscs(thirdStack, toStack, fromStack, discs-1);
		}
	}
	
	private boolean canMove(Stack<Disc> fromStack, Stack<Disc> toStack) {
		Disc from = (!fromStack.empty())? fromStack.peek() : null;
		Disc to = (!toStack.empty())? toStack.peek() : null;
		if (from == null) {
			return false;
		} else if (to == null || from.getSize() < to.getSize()) {
			return true;
		}
		return false;
	}
	
	private void move(Stack<Disc> fromStack, Stack<Disc> toStack) {
		toStack.push(fromStack.pop());
		System.out.println();
		printStacks();
		moveCount++;
	}
	
	public void printStacks() {
		int max = Math.max(stack1.size(), Math.max(stack2.size(), stack3.size()));
		Disc[] s1 = stack1.toArray(new Disc[stack1.size()]);
		Disc[] s2 = stack2.toArray(new Disc[stack2.size()]);
		Disc[] s3 =  stack3.toArray(new Disc[stack3.size()]);
		for (int i = max-1; i >= 0; i--) {
			String _s1 = (s1.length > i) ? new Integer(s1[i].getSize()).toString() : "_";
			System.out.print("  [" + _s1 + "]");
			String _s2 = (s2.length > i) ? new Integer(s2[i].getSize()).toString() : "_";
			System.out.print("  [" + _s2 + "]");
			String _s3 = (s3.length > i) ? new Integer(s3[i].getSize()).toString() : "_";
			System.out.print("  [" + _s3 + "]");
			System.out.println();
		}
	}
	
	public int getMoveCount() {
		return moveCount;
	}
}
