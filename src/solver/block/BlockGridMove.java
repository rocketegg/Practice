package solver.block;

import solver.GridMove;
import solver.grid.*;

public class BlockGridMove extends GridMove<BlockGridCell> {
	public BlockGridCell origin;
	public BlockGridCell destination;
	
	public BlockGridMove(BlockGridCell origin, BlockGridCell destination) {
		super(origin, destination);
	}
}
