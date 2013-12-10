package main.adt.element;

public class Entry <K extends Comparable<K>,V> implements Comparable<Entry<K,V>>{
	private K key;
	private V value;
	
	public Entry(K k, V v) {
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

	@Override
	public int compareTo(Entry<K, V> o) {
		return o.getKey().compareTo(this.key);
	}
}
