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
	
	public void solve(Stack<Disc> destination) {
		
		System.out.println("Initial State: ");
		printStacks();
		System.out.println("Solving... ");
		Stack<Disc> fromStack = findStack(1);	//the stack with count
		int movable = findNumMovable(fromStack);
		
		while (movable < 5) { //should be max disc size
			fromStack = findStack(1);	//the stack with count
			movable = findNumMovable(fromStack);
			Stack<Disc> toStack = findStack(movable+1);
			System.out.println("num movable:" + movable);
			if (movable < 5) {
				moveDiscs(fromStack, toStack, getUnusedStack(fromStack, toStack), movable);
			} 
		}
		
		if (!fromStack.equals(destination)) {
			fromStack = findStack(1);
			moveDiscs(fromStack, destination, getUnusedStack(fromStack, destination), movable);
		}
		//moveDiscs(fromStack, toStack, getUnusedStack(fromStack, toStack)), movable);
		//int max = Math.max(stack1.size(), Math.max(stack2.size(), stack3.size()));
		//moveDiscs(stack1, stack2, stack3, max);	//move all discs from stack 1 to stack 2
		//if stack isn't 100% - i.e. looks like this
		//			  1
		//            2 
		// 2          3
		// 3 1   =>   4
		// 5 4 _    _ 5 _
		//you solve this by finding the 1, moving it on top of the 2
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
		return stack3;
		
	}
	
	private Stack<Disc> getUnusedStack(Stack<Disc> fromStack, Stack<Disc> toStack) {
		resetFlags();
		if (fromStack.equals(stack1) || toStack.equals(stack1)) {
			stack1_used = true;
		}
		
		if (fromStack.equals(stack2) || toStack.equals(stack2)) {
			stack2_used = true;
		}
		
		if (fromStack.equals(stack3) || toStack.equals(stack3)) {
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
		}
		else if (discs == 2) {
			move(fromStack, thirdStack);
			move(fromStack, toStack);
			move(thirdStack, toStack);
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
