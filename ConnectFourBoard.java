import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class ConnectFourBoard extends JPanel //implements GameBoardInterface, ActionListener
	{
		private JButton [][] board;
		private static final int NUM_ROWS = 6;
		private static final int NUM_COLS = 7;
		
		public ConnectFourBoard()
		{
			setLayout(new GridLayout(6,7));
			displayBoard();
		}
		
		public void clearBoard()
		{
			for(int row = 0; row < board.length; row++)
			{
				for(int col = 0; col<board[row].length; col++)
				{
					board[row][col].setText("");
					board[row][col].setEnabled(true);
				}
			}
		}
		
		//@Override
		public void displayBoard()
		{
			board = new JButton[6][7];
			for(int row=0; row<board.length; row++)
				{
					for(int col=0; col<board[row].length; col++)
					{
	//					board[row][col] = new JButton("["+row+"]["+col+"]");
						board[row][col] = new JButton();
						Font bigF = new Font(Font.SANS_SERIF, Font.BOLD, 30);
						board[row][col].setFont(bigF);
						//board[row][col].addActionListener(this);//listen for clicks
	//					board[row][col].setEnabled(true);//enable
						add(board[row][col]);
					}
				}
		}
		
		public boolean isWinnerInCol()
		{
			for(int col=0; col<NUM_COLS; col++)
			{
			int numMatchesInCol = 0;
			for(int row=0; row<board.length; row++)
			{
					if(board[row][col].getText().equals(currPlayer.getSymbol()))
					{
						numMatchesInCol++;
						if(numMatchesInCol == 4)
						{
							return true;
						}
					}
				}
			}
			return false;
		}
	/*
	@Override
	public void populateBoard()
	{
		
	}
	
	@Override
	public void takeTurn()
	{
		if(currPlayer.equals(player1))
		{
			currPlayer = player2;
		}
		else
		{
			currPlayer = player1;
		}
	}
	
	@Override
	public void displayWinner()
	{
		
	}
	
	@Override
	public boolean isFull()
	{
		return true;
	}
	
	@Override
	public boolean isEmpty()
	{
		return true;
	}
	
	@Override
	public boolean isWinner(String currPlayer)
	{
		return true;
	}
	
	@Override
	public boolean isDraw()
	{
		return true;
	}
	*/
}
