package solver;

public abstract class GridMove <G extends GridCell> {
	
	private GridCell origin;
	private GridCell destination;
	
	public GridMove(GridCell origin, GridCell destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public GridCell getOrigin() {
		return origin;
	}

	public void setOrigin(GridCell origin) {
		this.origin = origin;
	}

	public GridCell getDestination() {
		return destination;
	}

	public void setDestination(GridCell destination) {
		this.destination = destination;
	}
}
