package solver.game.peg;

import solver.GridCell;

public class PegGridCell extends GridCell {
	
	private boolean hasStone;
	
	/*
	 * Constructors
	 */

	public PegGridCell(int row, int col, boolean isOpen) {
		super(row, col, isOpen);
	}
	
	public PegGridCell(int row, int col, boolean isOpen, boolean hasStone) {
		super(row, col, isOpen);
		this.hasStone = hasStone;
	}
	
	/*
	 * Members
	 */
	public boolean hasStone() {
		return hasStone;
	}

	public void setHasStone(boolean hasStone) {
		this.hasStone = hasStone;
	}
	
}
