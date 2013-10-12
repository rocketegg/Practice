package solver.block;


public class BlockGridCell {

	public boolean isValidCell;
	public int row;
	public int col;
	public boolean hasStone;
	
	public BlockGridCell(boolean isValidCell, int row, int col, boolean hasStone) {
		this.isValidCell = isValidCell;
		this.row = row;
		this.col = col;
		this.hasStone = hasStone;
	}
}