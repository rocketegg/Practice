package solver.game.peg;

import java.util.ArrayList;

import solver.IGrid;
import solver.GridCell;
import solver.GridMove;
import solver.exceptions.InvalidCellException;
import solver.grid.Grid;

public class PegGrid extends IGrid<PegGridCell, PegGridMove> {

	private int numStones;
	private int gridRows;
	private int gridCols;
	private PegGridCell [][] grid;
	

	/**
	 * Initializes empty grid
	 */
	public PegGrid() {	
		int gridRows = 7;
		int gridCols = 7;
		grid = new PegGridCell[gridRows][gridCols];
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				grid[i][j] = new PegGridCell(i, j, false, false);
			}
		}
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		int gridRows = 7;
		int gridCols = 7;
		grid = new PegGridCell[gridRows][gridCols];
		//winCondition = new GridCell[1];
		//winCondition[0] = new GridCell(true, 3, 4, true);
		/*winCondition[0] = new GridCell(true, 2, 2, true);
		winCondition[1] = new GridCell(true, 4, 2, true);
		winCondition[2] = new GridCell(true, 4, 4, true);
		winCondition[3] = new GridCell(true, 2, 4, true);
		winCondition[4] = new GridCell(true, 6, 3, true);
		winCondition[5] = new GridCell(true, 3, 0, true);*/
		//winCondition[5] = new GridCell(true, 3, 6, true);
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if ((i < 2 && j < 2) || 
						(i < 2 && j > 4) ||
						(i > 4 && j < 2) || 
						(i > 4 && j > 4)) {
					//Constructor: int row, int col, boolean isOpen, boolean hasStone
					grid[i][j] = new PegGridCell(i, j, false, false);
				}
				else if (i == gridRows/2 && j == gridCols/2) {
					grid[i][j] = new PegGridCell(i, j, true, false);
				} else {
					grid[i][j] = new PegGridCell(i, j, true, true);
					setNumStones(getNumStones() + 1);
				}
			}
		}
		setNumStones(0);
	}
	
	@Override
	public ArrayList<PegGridMove> getMovesForCell(PegGridCell gc) {
		ArrayList<PegGridMove> validMoves = new ArrayList<PegGridMove> ();
		//up 
		PegGridMove up = new PegGridMove(this, gc, grid[gc.getRow()-2][gc.getCol()]);
		if (gc.getRow()-2 > -1 && up.canJump()) 
			validMoves.add(up);
		//down
		PegGridMove down = new PegGridMove(this, gc, grid[gc.getRow()+2][gc.getCol()]);
		if (gc.getRow()+2 < gridRows && down.canJump()) 
			validMoves.add(down);
		//left
		PegGridMove left = new PegGridMove(this, gc, grid[gc.getRow()][gc.getCol()-2]);
		if (gc.getCol()-2 > -1 && left.canJump())
			validMoves.add(left);
		//right
		PegGridMove right = new PegGridMove(this, gc, grid[gc.getRow()][gc.getCol()+2]);
		if (gc.getCol()+2 < gridCols && right.canJump()) 
			validMoves.add(right);

		return validMoves;
	}

	@Override
	public ArrayList<PegGridMove> getAllMoves() {
		ArrayList<PegGridMove> allMoves = new ArrayList<PegGridMove> ();

		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (cellHasStone(grid[i][j])) {
					//System.out.print("[" + i + "][" + j + "]");
					for (PegGridMove m : getMovesForCell(grid[i][j])) {
						allMoves.add(m);
					}
				}
			}
		}
		return allMoves;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	private boolean cellHasStone(PegGridCell gc) {
		return gc.hasStone();
	}

	@Override
	public PegGridCell getCell(int row, int col)
			throws InvalidCellException {
		if (row < gridRows && row >= 0 && col < gridCols && col >= 0 && grid != null) {
			return grid[row][col];
		} else {
			throw new InvalidCellException();
		}
	}
	
	@Override
	public void setCell(int row, int col, PegGridCell t)
			throws InvalidCellException {
		if (row < gridRows && row >= 0 && col < gridCols && col >= 0 && grid != null) {
			grid[row][col] = t;
		} else {
			throw new InvalidCellException();
		}
	}
	
	/*
	 * Methods specific to this class
	 */
	public void printAllMoves () {
		ArrayList<PegGridMove> allMoves = getAllMoves();
		System.out.println("All Moves: ");
		for (PegGridMove g : allMoves) {
			System.out.println("["+	g.getOrigin().getRow() + "][" + g.getOrigin().getCol() + "] -> [" + 
									g.getDestination().getRow() + "][" + g.getDestination().getCol() + "]");
		}
	}
	
	public void printGrid() {
		System.out.print("  ");
		for (int i = 0; i < gridRows; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (j == 0) System.out.print(i + " ");
				if (grid[i][j].isOpen()) {
					if (grid[i][j].hasStone())
						System.out.print(" X ");
					else
						System.out.print(" _ ");
				} else
					System.out.print ("   ");
			}
			System.out.println();
		}
	}
	
	public ArrayList<PegGridCell> getCellsWithStones() {
		ArrayList<PegGridCell> listOfStones = new ArrayList<PegGridCell>();
		for (int x = 0; x < gridRows; x++) 
			for (int y = 0; y < gridCols; y++) {
				if (grid[x][y].hasStone())
					listOfStones.add(grid[x][y]);
			}
		return listOfStones;
	}
	/*
	 * Getters and Setters
	 */
	
	public int getNumStones() {
		return numStones;
	}
	
	public void setNumStones(int numStones) {
		this.numStones = numStones;
	}
	
	public void decrementNumStones() {
		this.numStones--;
	}
	
	public void incrementNumStones() {
		this.numStones--;
	}

	
}
