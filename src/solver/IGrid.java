package solver;

import java.util.ArrayList;

import solver.exceptions.InvalidCellException;
import solver.game.peg.PegGridCell;
import solver.game.peg.PegGridMove;

public abstract class IGrid <T extends GridCell, K extends GridMove>{
	
	private GridCell [][] myGrid;
	private int gridRows;
	private int gridCols;
	
	public IGrid() {
		gridRows = 7;
		gridCols = 7;
	}
	
	/*
	 * Abstract Methods
	 */
	
	public abstract ArrayList<K> getMovesForCell(T t);	//Returns all possible moves for a given cell
	public abstract ArrayList<K> getAllMoves();	//Returns all moves for a given grid state	
	public abstract void initialize();	//initializes the grid
	public abstract void reset();	//resets the grid

	public abstract T getCell(int row, int col) throws InvalidCellException;
	public abstract void setCell(int row, int col, T t) throws InvalidCellException;
	
	
	/*
	 * Getters and Setters
	 */

	public GridCell[][] getMyGrid() {
		return myGrid;
	}



	public void setMyGrid(GridCell[][] myGrid) {
		this.myGrid = myGrid;
	}



	public int getGridRows() {
		return gridRows;
	}



	public void setGridRows(int gridRows) {
		this.gridRows = gridRows;
	}



	public int getGridCols() {
		return gridCols;
	}



	public void setGridCols(int gridCols) {
		this.gridCols = gridCols;
	}

}
