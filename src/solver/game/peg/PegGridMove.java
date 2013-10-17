package solver.game.peg;

import solver.GridCell;
import solver.GridMove;
import solver.exceptions.InvalidCellException;

public class PegGridMove extends GridMove {

	private PegGrid grid;
	
	public PegGridMove(PegGridCell origin, PegGridCell destination) {
		super (null,null);
	}
	
	public PegGridMove(PegGrid grid, PegGridCell origin, PegGridCell destination) {
		super(origin, destination);
		this.grid = grid;
	}
	
	public boolean canJump() {
		try {
			if (cellHasStone((PegGridCell) this.getDestination())) return false;
			if (!cellIsValid((PegGridCell) this.getDestination())) return false;
			if (cellExceedsRange(grid, this.getDestination())) return false;
			if (!isAdjacent(grid, (PegGridCell) this.getOrigin(), (PegGridCell) this.getDestination())) return false;
		} catch (InvalidCellException ice) {
			//this.getDestination is invalid
			return false;
		}
		return true;
	}
	
	public boolean cellHasStone(PegGridCell gc) {
		return gc.hasStone();
	}
	
	public boolean cellIsValid(PegGridCell gc) {
		return gc.isOpen();
	}
	
	public boolean cellExceedsRange(PegGrid grid, GridCell gc) {
		if (gc.getRow() >= grid.getGridRows() || gc.getRow() < 0 || gc.getCol() >= grid.getGridCols() || gc.getCol() < 0) 
			return true;
		return false;
	}
	
	public boolean isAdjacent(PegGrid grid, PegGridCell origin, PegGridCell destination) throws InvalidCellException {
		//horizontally jump
		if (origin.getRow() == destination.getRow()) {
			if (origin.getCol() > destination.getCol()) { //move left
				return cellHasStone(grid.getCell(origin.getRow(), origin.getCol()-1));
			} else // move right
				return cellHasStone(grid.getCell(origin.getRow(), origin.getCol()+1));
		}
		//vertically adjacent
		else if (origin.getCol() == destination.getCol()) {
			if (origin.getRow() > destination.getRow()) { //move up
				return cellHasStone(grid.getCell(origin.getRow()-1, origin.getCol()));
			} else { //move down
				return cellHasStone(grid.getCell(origin.getRow()+1, origin.getCol()));
			}
		}
		else 
			return false;
	}

}
