package solver.grid;

import java.util.ArrayList;
import java.util.Stack;

/**
* Committed from Git local
*/
public class Grid {

	public GridCell grid[][];
	public final int gridRows = 7;
	public final int gridCols = 7; 
	public GridCell [] winCondition;
	public int numStones;
	
	public Grid () {
		//initialize grid
		grid = new GridCell[gridRows][gridCols];
		winCondition = new GridCell[6];
		//winCondition[0] = new GridCell(true, 3, 3, true);
		winCondition[0] = new GridCell(true, 2, 2, true);
		winCondition[1] = new GridCell(true, 4, 2, true);
		winCondition[2] = new GridCell(true, 4, 4, true);
		winCondition[3] = new GridCell(true, 2, 4, true);
		winCondition[4] = new GridCell(true, 6, 3, true);
		winCondition[5] = new GridCell(true, 3, 0, true);
		//winCondition[5] = new GridCell(true, 3, 6, true);
		numStones = 0;
	}
	
	
	public void Initialize() {
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if ((i < 2 && j < 2) || 
						(i < 2 && j > 4) ||
						(i > 4 && j < 2) || 
						(i > 4 && j > 4)) {
					//Constructor: boolean isValidCell, int row, int col, boolean hasStone
					grid [i][j] = new GridCell(false, i, j, false);
				}
				else if (i == gridRows/2 && j == gridCols/2) {
					grid[i][j] = new GridCell(true, i, j, false);
				} else {
					grid[i][j] = new GridCell(true, i, j, true);
					numStones++;
				}
			}
		}
		//System.out.println("numstones initialized at: " + numStones);
		//grid[3][3] = new GridCell(true, 3, 3, true);
	}
	
	public boolean cellHasStone(GridCell gc) {
		return gc.hasStone;
	}
	
	public boolean cellIsValid(GridCell gc) {
		return gc.isValidCell;
	}
	
	public boolean cellExceedsRange(GridCell gc) {
		if (gc.row >= gridRows || gc.row < 0 || gc.col >= gridCols || gc.col < 0) 
			return true;
		return false;
	}
	
	public boolean isAdjacent(GridCell origin, GridCell destination) {
		//horizontally jump
		if (origin.row == destination.row) {
			if (origin.col > destination.col) { //move left
				return cellHasStone(grid[origin.row][origin.col-1]);
			} else // move right
				return cellHasStone(grid[origin.row][origin.col+1]);
		}
		//vertically adjacent
		else if (origin.col == destination.col) {
			if (origin.row > destination.row) { //move up
				return cellHasStone(grid[origin.row-1][origin.col]);
			} else { //move down
				return cellHasStone(grid[origin.row+1][origin.col]);
			}
		}
		else 
			return false;
	}
	
	public boolean canJump(GridCell origin, GridCell destination) {
		if (cellHasStone(destination)) return false;
		if (!cellIsValid(destination)) return false;
		if (cellExceedsRange(destination)) return false;
		if (!isAdjacent(origin, destination)) return false;
		return true;
	}
	

	
	public ArrayList<GridMove> getMovesForCell(GridCell gc) {
		ArrayList<GridMove> validMoves = new ArrayList<GridMove> ();
		//up 
		if (gc.row-2 > -1 && canJump(gc, grid[gc.row-2][gc.col])) 
			validMoves.add(new GridMove(gc, grid[gc.row-2][gc.col]));
		//down
		if (gc.row+2 < gridRows && canJump(gc, grid[gc.row+2][gc.col])) 
			validMoves.add(new GridMove(gc, grid[gc.row+2][gc.col]));
		//left
		if (gc.col-2 > -1 && canJump(gc, grid[gc.row][gc.col-2])) 
			validMoves.add(new GridMove(gc, grid[gc.row][gc.col-2]));
		//right
		if (gc.col+2 < gridCols && canJump(gc, grid[gc.row][gc.col+2])) 
			validMoves.add(new GridMove(gc, grid[gc.row][gc.col+2]));

		return validMoves;
		
	}
	
	public ArrayList<GridMove> getAllMoves() {
		ArrayList<GridMove> allMoves = new ArrayList<GridMove> ();

		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (cellHasStone(grid[i][j])) {
					//System.out.print("[" + i + "][" + j + "]");
					for (GridMove g : getMovesForCell(grid[i][j])) {
						allMoves.add(g);
					}
				}
			}
		}
		return allMoves;
	}
	
	public void move(GridMove gridmove) {
		//assuming that move is valid
		grid[gridmove.origin.row][gridmove.origin.col].hasStone = false;
		if (gridmove.origin.row == gridmove.destination.row) {
			if (gridmove.origin.col > gridmove.destination.col) { //move left
				grid[gridmove.origin.row][gridmove.origin.col-1].hasStone = false;
			} else // move right
				grid[gridmove.origin.row][gridmove.origin.col+1].hasStone = false;
		}
		//vertically adjacent
		else if (gridmove.origin.col == gridmove.destination.col) {
			if (gridmove.origin.row > gridmove.destination.row) { //move up
				grid[gridmove.origin.row-1][gridmove.origin.col].hasStone = false;
			} else { //move down
				grid[gridmove.origin.row+1][gridmove.origin.col].hasStone = false;
			}
		}
		grid[gridmove.destination.row][gridmove.destination.col].hasStone = true;
		numStones--;
	}
	
	public boolean isWinConditionTrue() {
		/*for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (i != winCondition[0].row || j != winCondition[0].col) { //if any other cell than winCondition has stone, false
					if (cellHasStone(grid[i][j])) 
						return false;
				}
			}
		}*/
		if (numStones != winCondition.length) {
				return false;
		}
		for (int i = 0; i < winCondition.length; i++) {
			if (!cellHasStone(grid[winCondition[i].row][winCondition[i].col])) {
				return false;
			}
		}
		return true;
	}

	public void undoMove(GridMove gridmove) {
		//assuming move is valid
		grid[gridmove.destination.row][gridmove.destination.col].hasStone = false;
		grid[gridmove.origin.row][gridmove.origin.col].hasStone = true;
		
		if (gridmove.origin.row == gridmove.destination.row) {
			if (gridmove.destination.col > gridmove.origin.col) { //was a move right
				grid[gridmove.origin.row][gridmove.origin.col+1].hasStone = true;
			} else // was a move to left
				grid[gridmove.origin.row][gridmove.origin.col-1].hasStone = true;
		}
		//vertically adjacent
		else if (gridmove.origin.col == gridmove.destination.col) {
			if (gridmove.destination.row > gridmove.origin.row) { //was a move up
				grid[gridmove.origin.row+1][gridmove.origin.col].hasStone = true;
			} else { //was a move down
				grid[gridmove.origin.row-1][gridmove.origin.col].hasStone = true;
			}
		}
		numStones++;
		
	}
	
	public void printAllMoves () {
		ArrayList<GridMove> allMoves = getAllMoves();
		System.out.println("All Moves: ");
		for (GridMove g : allMoves) {
			System.out.println("["+g.origin.row + "][" + g.origin.col + "] -> [" + g.destination.row + "][" + g.destination.col + "]");
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
				if (cellIsValid(grid[i][j])) {
					if (cellHasStone(grid[i][j]))
						System.out.print(" X ");
					else
						System.out.print(" _ ");
				} else
					System.out.print ("   ");
			}
			System.out.println();
		}
	}
	


	public void solve() {
		ArrayList<GridMove> allMoves = getAllMoves();		
		int numMoves = 0;
		
		Stack<GridMove> moves = new Stack<GridMove> ();
		Stack<Integer> movesCounter = new Stack<Integer> (); //a stack of counters
		GridMove lastMove = new GridMove(null, null);
		Integer m;	//m is the current move for a given state (integer)
		Integer i;
		
		while (!isWinConditionTrue()) {
			allMoves = getAllMoves();
			//printGrid();
			//printAllMoves();
			
			if (movesCounter.size() == 0) { //nothing added yet)
				m = 0;
			} else {
				m = movesCounter.peek();
			}
			
			//System.out.println("MC: " + m);
			if (allMoves.size() == 0) { //out of moves - move up
				//System.out.println("As far as we can go, backing up\n");
				lastMove = moves.pop();
				undoMove(lastMove);
				
				movesCounter.pop();
				i = movesCounter.pop();
				i++;
				movesCounter.push(i);
				
			} else if (m < allMoves.size()) { //there are still moves to be done
				lastMove = allMoves.get(m);
				moves.push(lastMove);
				movesCounter.push(new Integer(0));
				move(lastMove);
				//System.out.println("MOVING: " + "["+lastMove.origin.row + "][" + lastMove.origin.col + "] -> [" + lastMove.destination.row + "][" + lastMove.destination.col + "]");
				numMoves++;
				
				if (numMoves % 100000 == 0) {
					System.out.println("numMoves: " + numMoves);
					//System.out.println("numStones: " + numStones);
				}
			} else { 	//tried all the moves, move up
				//System.out.println("Out of moves, backing up.");
				lastMove = moves.pop();
				undoMove(lastMove);
				//System.out.println("UNDOING MOVE: " + "["+lastMove.origin.row + "][" + lastMove.origin.col + "] -> [" + lastMove.destination.row + "][" + lastMove.destination.col + "]\n");
				movesCounter.pop();
				i = movesCounter.pop();
				i++;
				movesCounter.push(i);
			}
		}
		System.out.println("Complete!");
		printGrid();
		printStackMoves(moves);
		System.out.println("Total Moves Considered: " + numMoves);
	}
	
	public void printStackMoves(Stack<GridMove> myStack) {
		
		System.out.println("All Moves: ");
		for (GridMove g : myStack) {
			System.out.println("["+g.origin.row + "][" + g.origin.col + "] -> [" + g.destination.row + "][" + g.destination.col + "]");
		}
	}
	
}
