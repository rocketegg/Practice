package solver.block;

import solver.ISolver;
import solver.exceptions.InvalidCellException;

public class BlockSolver implements ISolver<BlockGrid, BlockGridMove> {

	@Override
	public void move(BlockGrid g, BlockGridMove m) throws InvalidCellException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoMove(BlockGrid g, BlockGridMove m)
			throws InvalidCellException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canMove(BlockGridMove m, BlockGrid g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWinConditionTrue(BlockGrid g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWinCondition(BlockGrid g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void solve(BlockGrid g) {
		// TODO Auto-generated method stub
		
	}

}
