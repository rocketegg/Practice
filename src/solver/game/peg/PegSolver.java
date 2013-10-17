package solver.game.peg;

import java.util.ArrayList;
import java.util.Stack;
import solver.ISolver;
import solver.exceptions.InvalidCellException;

public class PegSolver implements ISolver<PegGrid, PegGridMove> {

	private PegGridCell [] winCondition;
	
	@Override
	public void move(PegGrid grid, PegGridMove move) throws InvalidCellException {
		int grid_origin_row = move.getOrigin().getRow();
		int grid_origin_col = move.getOrigin().getCol();
		int grid_dest_row = move.getDestination().getRow();
		int grid_dest_col = move.getDestination().getCol();
		
		grid.getCell(grid_origin_row, grid_origin_col).setHasStone(false);
		if (grid_origin_row == grid_dest_row) {
			if (grid_origin_col > grid_dest_col) { //move left
				grid.getCell(grid_origin_row, grid_origin_col-1).setHasStone(false);
			} else // move right
				grid.getCell(grid_origin_row, grid_origin_col+1).setHasStone(false);
		}
		//vertically adjacent
		else if (grid_origin_col == grid_dest_col) {
			if (grid_origin_row > grid_dest_row) { //move up
				grid.getCell(grid_origin_row-1, grid_origin_col).setHasStone(false);
			} else { //move down
				grid.getCell(grid_origin_row+1, grid_origin_col).setHasStone(false);
			}
		}
		grid.getCell(grid_dest_row, grid_dest_col).setHasStone(true);
		grid.decrementNumStones();
	}

	@Override
	public void undoMove(PegGrid grid, PegGridMove move) throws InvalidCellException {
		int grid_origin_row = move.getOrigin().getRow();
		int grid_origin_col = move.getOrigin().getCol();
		int grid_dest_row = move.getDestination().getRow();
		int grid_dest_col = move.getDestination().getCol();
		
		grid.getCell(grid_dest_row, grid_dest_col).setHasStone(false);
		grid.getCell(grid_origin_row, grid_origin_col).setHasStone(true);
		
		if (grid_origin_row == grid_dest_row) {
			if (grid_dest_col > grid_origin_col) { //was a move right
				grid.getCell(grid_origin_row, grid_origin_col+1).setHasStone(true);
			} else // was a move to left
				grid.getCell(grid_origin_row, grid_origin_col-1).setHasStone(true);
		}
		//vertically adjacent
		else if (grid_origin_col == grid_dest_col) {
			if (grid_dest_row > grid_origin_row) { //was a move up
				grid.getCell(grid_origin_row+1, grid_origin_col).setHasStone(true);
			} else { //was a move down
				grid.getCell(grid_origin_row-1, grid_origin_col).setHasStone(true);
			}
		}
		grid.incrementNumStones();

	}

	@Override
	public boolean canMove(PegGridMove m, PegGrid g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWinConditionTrue(PegGrid g) {
		if (g.getNumStones() != winCondition.length) {
			return false;
		}
		try {
			for (int i = 0; i < winCondition.length; i++) {
				if (g.getCell(winCondition[i].getRow(), winCondition[i].getCol()).hasStone() == false) {
					return false;
				}
			}
		} catch (InvalidCellException ice) {
			ice.printStackTrace();
		}
		return true;
	}

	@Override
	public void setWinCondition(PegGrid g) {
		g.getCellsWithStones().toArray(winCondition);
	}

	@Override
	public void solve(PegGrid grid) {
		ArrayList<PegGridMove> allMoves = grid.getAllMoves();		
		int numMoves = 0;
		
		Stack<PegGridMove> moves = new Stack<PegGridMove> ();
		Stack<Integer> movesCounter = new Stack<Integer> (); //a stack of counters
		PegGridMove lastMove = new PegGridMove(null, null);
		Integer m;	//m is the current move for a given state (integer)
		Integer i;
		
		try {
			while (!isWinConditionTrue(grid)) {
				allMoves = grid.getAllMoves();
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
					undoMove(grid, lastMove);
					
					movesCounter.pop();
					i = movesCounter.pop();
					i++;
					movesCounter.push(i);
					
				} else if (m < allMoves.size()) { //there are still moves to be done
					lastMove = allMoves.get(m);
					moves.push(lastMove);
					movesCounter.push(new Integer(0));
					move(grid, lastMove);
					//System.out.println("MOVING: " + "["+lastMove.origin.row + "][" + lastMove.origin.col + "] -> [" + lastMove.destination.row + "][" + lastMove.destination.col + "]");
					numMoves++;
					
					if (numMoves % 100000 == 0) {
						System.out.println("numMoves: " + numMoves);
						//System.out.println("numStones: " + numStones);
					}
				} else { 	//tried all the moves, move up
					//System.out.println("Out of moves, backing up.");
					lastMove = moves.pop();
					undoMove(grid, lastMove);
					//System.out.println("UNDOING MOVE: " + "["+lastMove.origin.row + "][" + lastMove.origin.col + "] -> [" + lastMove.destination.row + "][" + lastMove.destination.col + "]\n");
					movesCounter.pop();
					i = movesCounter.pop();
					i++;
					movesCounter.push(i);
				}
			}
		} catch (InvalidCellException ice) {
			ice.printStackTrace();
			
		} catch (Exception e) {
			
		} 
		
		
		System.out.println("Complete!");
		grid.printGrid();
		printStackMoves(moves);
		System.out.println("Total Moves Considered: " + numMoves);
		
	}

	public void printStackMoves(Stack<PegGridMove> myStack) {
		
		System.out.println("All Moves: ");
		for (PegGridMove g : myStack) {
			System.out.println("["+	g.getOrigin().getRow() + "][" + g.getOrigin().getCol() + "] -> [" + 
					g.getDestination().getRow() + "][" + g.getDestination().getCol() + "]");
		}
	}


}
