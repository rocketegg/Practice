package tests;

import main.util.Stack;

import org.junit.Before;

import junit.framework.TestCase;

public class StackTest extends TestCase {
	
	Stack myStack;
	
	@Before
	public void setUp() {
		myStack = new Stack();
		
	}
	
	public void testStack() {
		myStack.slowPush(1);
		myStack.slowPush(2);
		myStack.slowPush(3);
		myStack.slowPush(4);
		myStack.slowPush(5);
		myStack.printBoth();
		System.out.println(myStack.fastPop());
		myStack.printBoth();
		System.out.println(myStack.fastPop());
		System.out.println(myStack.fastPop());
		myStack.printBoth();
		myStack.slowPush(6);
		myStack.slowPush(7);
		myStack.slowPush(8);
		myStack.printBoth();
		System.out.println(myStack.fastPop());
		System.out.println(myStack.fastPop());
		myStack.slowPush(9);
		myStack.slowPush(10);
		myStack.printBoth();
		System.out.println(myStack.fastPop());
		System.out.println(myStack.fastPop());
		System.out.println(myStack.fastPop());
		myStack.printBoth();
		
	}
	

}
