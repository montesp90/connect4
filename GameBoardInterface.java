public interface GameBoardInterface 
{
	void clearBoard();
	void displayBoard();
	void populateBoard();
	void takeTurn();
	void displayWinner();
	
	boolean isFull();
	boolean isEmpty();
	boolean isWinner(String currPlayer);
	boolean isDraw();
}