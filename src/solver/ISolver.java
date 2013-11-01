package solver;

import solver.exceptions.InvalidCellException;

public interface ISolver<G extends IGrid<?, ?>, M extends GridMove<?>> {

	public void move(G g, M m) throws InvalidCellException;	//steps forward through the Grid
	public void undoMove(G g, M m) throws InvalidCellException;	//undoes last step - necessary for solve();
	public boolean canMove(M m, G g);	//returns true if the move m is legal in the Grid g
	
	public boolean isWinConditionTrue(G g);	//returns if win condition is true for a particular configuration
	public void setWinCondition(G g);	//sets a win condition - can this be abstracted more? i.e. multiple grids or non-grid conditions
	public void solve(G g);	//the actual solver function using DFS or BFS
}
