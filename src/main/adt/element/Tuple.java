package main.adt.element;

public class Tuple {
	public Character ch = null;
	public Integer count = 0;
	
	public Tuple() {};
	
	public Tuple(Character ch, Integer count) {
		this.ch = ch;
		this.count = count;
	}
	
	@Override
	public String toString() {
		return this.ch + " " + count;
	}
}