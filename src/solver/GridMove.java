package solver;

public abstract class GridMove <G extends GridCell> {
	
	private G origin;
	private G destination;
	
	public GridMove(G origin, G destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public G getOrigin() {
		return origin;
	}

	public void setOrigin(G origin) {
		this.origin = origin;
	}

	public G getDestination() {
		return destination;
	}

	public void setDestination(G destination) {
		this.destination = destination;
	}
}
