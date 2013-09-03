package solver.grid;

public class GridMove {
	public GridCell origin;
	public GridCell destination;
	
	public GridMove(GridCell origin, GridCell destination) {
		this.origin = origin;
		this.destination = destination;
	}
}
