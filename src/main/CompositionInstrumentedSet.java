package main;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class is a copy of InstrumentedHashSet but that uses composition instead of inheritance.
 * It is much more robust than the INstrumentedHashSet because it is not coupled to the implementation
 * of HashSet in anyway.  Notice that this class uses forwarding methods that simply call the methods
 * on the private instance of HashSet<E> hashSet.
 * 
 * In order to solve problems of other methods not accessible because this class does not extend
 * HashSet look at the ForwardingSet which basically just implements all methods of a Set by forwarding them. 
 * @author albert
 *
 * @param <E>
 */
public class CompositionInstrumentedSet<E> extends ForwardingSet<E>{

	private int addCount;
	

	public CompositionInstrumentedSet(Set<E> s) {
		super(s);
	}
	
	@Override
	public boolean add(E e) {
		//return super.add(e);
		addCount++;
		System.out.println("CompositonInstrumentedHashSet add(" + e + ") called.");
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> list) {
		addCount += list.size();
		System.out.println("CompositonInstrumentedHashSet addAll(" + list + ") called.");
		return super.addAll(list);
		//return super.addAll(list);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
}
