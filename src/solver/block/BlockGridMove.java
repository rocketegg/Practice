package solver.block;

import solver.grid.*;

public class BlockGridMove {
	public GridCell origin;
	public GridCell destination;
	
	public BlockGridMove(GridCell origin, GridCell destination) {
		this.origin = origin;
		this.destination = destination;
	}
}
