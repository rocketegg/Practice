package main;
import java.util.Collection;
import java.util.HashSet;

/**
 * Note that this class inherits from HashSet and overrides some methods.
 * As a result, it becomes coupled with the HashSet's implementation.  For instance
 * HashSet.addAll calls add, which is also overriden in this class.  As a result, 
 * addCount gets added twice per entry added to the InstrumentedHashSet.
 * 
 * This generally leads to fragile code, as HashSet could have methods that change
 * in the future.  
 * 	1) Superclasses can acquire new methods in subsequent releases (e.g. validating or
 * 		making other elements more secure)
 *  2) Reimplementing superclass methods may not always be possible because some methods
 *  	depend on private fields inaccessible to the subclass.
 * 
 * To avoid this problem, use composition instead of inheritance.  Composition uses
 * an instance of the existing class instead of extending the existing class.  It uses
 * forwarding methods (or methods that simply invoke the corresponding method on the contained 
 * instance of the existing class and returns the result.  
 * @author albert
 *
 * @param <E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {

	private int addCount;
	
	public InstrumentedHashSet() {
		super();
		addCount = 0;
	}
	
	@Override
	public boolean add(E e) {
		addCount++;
		System.out.println("InstrumentedHashSet add(" + e + ") called.");
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> list) {
		addCount += list.size();
		System.out.println("InstrumentedHashSet addAll(" + list + ") called.");
		return super.addAll(list);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
}
