package solver.block;

import solver.GridCell;


public class BlockGridCell extends GridCell {

	public boolean isValidCell;
	public int row;
	public int col;
	public boolean hasStone;
	
	public BlockGridCell(int row, int col, boolean isValidCell, boolean hasStone) {
		super(row, col, isValidCell);
	}
}
