package main;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * This is a reusable forwarding class (could be a public class)
 * @author albert
 *
 * @param <E>
 */
public class ForwardingSet<E> implements Set<E> {
	//All forwarding methods
	private Set<E> s;
	public ForwardingSet(Set<E> s) {this.s = s; }

	@Override
	public boolean add(E e) { 
		System.out.println("ForwardingSet add(" + e + ") called.");
		return s.add(e);}

	@Override
	public boolean addAll(Collection<? extends E> c) { 
		System.out.println("ForwardingSet addAll(" + c + ") called.");
		return s.addAll(c); }

	@Override
	public void clear() { s.clear(); }

	@Override
	public boolean contains(Object o) { return s.contains(o);}

	@Override
	public boolean containsAll(Collection<?> c) { return s.containsAll(c); }

	@Override
	public boolean isEmpty() { return s.isEmpty();	}

	@Override
	public Iterator<E> iterator() { return s.iterator(); }

	@Override
	public boolean remove(Object o) { return s.remove(o);	}

	@Override
	public boolean removeAll(Collection<?> c) { return s.removeAll(c);	}

	@Override
	public boolean retainAll(Collection<?> c) { return s.retainAll(c);	}

	@Override
	public int size() { return s.size(); }

	@Override
	public Object[] toArray() { return s.toArray();	}

	@Override
	public <T> T[] toArray(T[] a) { return s.toArray(a);}
}
