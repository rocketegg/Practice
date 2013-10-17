package solver;

/**
 * Abstract class representing a grid cell in a grid.
 * A grid is made up of an X - Y array of grid cells.
 * @author albert
 *
 */
public abstract class GridCell {
	
	private int row;
	private int col;
	private boolean isOpen;
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public GridCell(int row, int col, boolean isOpen) {
		this.isOpen = isOpen;
		this.row = row;
		this.col = col;
	}
	

}
