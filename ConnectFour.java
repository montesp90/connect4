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

public class ConnectFour extends JFrame
{
	private JPanel jpMain;
	Player player1;
	Player player2;
	Player currPlayer;
	ConnectFourBoard connectFourBoard;
	ScoreBoard scoreBoard;
	
	public ConnectFour()
	{
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		
		player1 = new Player("Superman", "S");
		player2 = new Player("WonderWoman", "W");
		currPlayer = player1;
		
		scoreBoard = new ScoreBoard();
		connectFourBoard = new ConnectFourBoard();
		
		jpMain.add(scoreBoard, BorderLayout.NORTH);
		jpMain.add(connectFourBoard, BorderLayout.CENTER);
		
		add(jpMain);
		setSize(500,500);
		//pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public class ScoreBoard extends JPanel
	{
		private JLabel lblChamp, lblLatestWinner, lblPlaceHolder, 
		   			   lblPlayerNames, lblPlayerNumWins,lblPlayer1Name,
		  			   lblPlayer2Name, lblPlayer1NumWins, lblPlayer2NumWins,
		  			   lblTurnPlayer, lblLatestPlayer;
		  			   
		private JPanel jpScoreInfo;
		private JPanel jpPlayerScoreInfo;
		
		public ScoreBoard()
		{
			setLayout(new BorderLayout());
			jpScoreInfo = new JPanel();
			jpScoreInfo.setLayout(new GridLayout(2,2));
			jpScoreInfo.setBackground(Color.yellow);
			lblChamp = new JLabel("CHAMP");
			lblTurnPlayer = new JLabel("Turn Player");
			lblLatestPlayer = new JLabel("*****");
			lblLatestWinner = new JLabel("*****");
			jpScoreInfo.add(lblChamp);
			jpScoreInfo.add(lblLatestWinner);
			jpScoreInfo.add(lblTurnPlayer);
			jpScoreInfo.add(lblLatestPlayer);
			
			jpPlayerScoreInfo = new JPanel();
			jpPlayerScoreInfo.setLayout(new GridLayout(3,3));
			jpPlayerScoreInfo.setBackground(Color.CYAN);
			lblPlaceHolder = new JLabel(" ");
			lblPlayerNames = new JLabel("NAME");
			lblPlayerNumWins = new JLabel("NUM WINS");
			lblPlayer1Name = new JLabel(player1.getName());
			lblPlayer2Name = new JLabel(player2.getName());
			lblPlayer1NumWins = new JLabel(" " + player1.getNumWins());
			lblPlayer2NumWins = new JLabel(" " + player2.getNumWins());
			
			jpPlayerScoreInfo.add(lblPlaceHolder);
			jpPlayerScoreInfo.add(new JLabel("Player 1"));
			jpPlayerScoreInfo.add(new JLabel("Player 2"));
			jpPlayerScoreInfo.add(lblPlayerNames);
			jpPlayerScoreInfo.add(lblPlayer1Name);
			jpPlayerScoreInfo.add(lblPlayer2Name);
			jpPlayerScoreInfo.add(lblPlayerNumWins);
			jpPlayerScoreInfo.add(lblPlayer1NumWins);
			jpPlayerScoreInfo.add(lblPlayer2NumWins);
			
			add(jpScoreInfo, BorderLayout.NORTH);
			add(jpPlayerScoreInfo, BorderLayout.CENTER);
		}
	}
	
	public class ConnectFourBoard extends JPanel implements /*GameBoardInterface,*/ ActionListener
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
					board[row][col].setEnabled(false);
					
					if(row == 5)
					{
						board[row][col].setEnabled(true);
					}
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
						board[row][col] = new JButton(/*"["+row+"]["+col+"]"*/);
						board[row][col].addActionListener(this);//listen for clicks
						board[row][col].setEnabled(false);//enable
						
						if(row == 5)
						{
							board[row][col].setEnabled(true);
						}
						
						add(board[row][col]);
					}
				}
		}
		
		public void takeTurn() 
		{
			if(currPlayer.equals(player1))
			{
				currPlayer = player2;
				scoreBoard.lblLatestPlayer.setText(currPlayer.getName());
			}
			else
			{
				currPlayer = player1;
				scoreBoard.lblLatestPlayer.setText(currPlayer.getName());
			}
		}
		
		public void displayWinner() 
		{
			JOptionPane.showMessageDialog(null, "WINNER! " + currPlayer.getName());
			currPlayer.addWin();
			scoreBoard.lblLatestWinner.setText(currPlayer.getName());
			if(currPlayer.equals(player1))
			{
				scoreBoard.lblPlayer1NumWins.setText(""+player1.getNumWins());
			}
			else
			{
				scoreBoard.lblPlayer2NumWins.setText(""+player2.getNumWins());
			}
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			
            JOptionPane.showConfirmDialog(null,"Do you want to play again?");
            
            if(dialogButton == JOptionPane.NO_OPTION)
            {
            	if(player1.getNumWins() > player2.getNumWins())
            	{
            		JOptionPane.showMessageDialog(null, "Game is over. The Champion is" + player1.getName());
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "Game is over. The Champion is" + player2.getName());
            	}
            	
            	
            	player1.setNumWins(0);
				player2.setNumWins(0);
				scoreBoard.lblPlayer1NumWins.setText(""+player1.getNumWins());
				scoreBoard.lblPlayer2NumWins.setText(""+player2.getNumWins());
				
				
            }
			
		}
		
		public boolean isFull() 
		{
			//iterate through all cells
			for(int row=0; row<board.length; row++)
			{
				for(int col=0; col<board[row].length; col++)
				{
					if(board[row][col].getText().equals(""))
					{
						return false; //if any are empty return false
					}
				}
			}
			return true;
		}
		
		
		public boolean isWinner(String currPlayerSymbol)
		{
			if(isWinnerInRow() || isWinnerInCol() || isWinnerInDiagonal())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public boolean isDraw()
		{
			if(isFull() && !isWinner(player1.getSymbol()) && !isWinner(player2.getSymbol()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public boolean diagonal(int row, int col, int length)
		{
			
			int numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,0 to 5,5
			for(int i = 0; i < length; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			return false;
		}
		
		public boolean isWinnerInDiagonal()
		{
			//diagonal(0,0,6);
			
			int row = 0;
			int col = 0;
			int numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,0 to 5,5
			for(int i = 0; i < 6; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 0;
			col = 1;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,1 to 5,6
			for(int i = 0; i < 6; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 0;
			col = 2;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,2 to 4,6
			for(int i = 0; i < 5; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 0;
			col = 3;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,3 to 3,6
			for(int i = 0; i < 4; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 1;
			col = 0;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 2,0 to 5,3
			for(int i = 0; i < 3; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 2;
			col = 0;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,3 to 3,6
			for(int i = 0; i < 4; i++)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col++;
			}
			
			row = 0;
			col = 6;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,6 to 5,1
			for(int i = 6; i > 0; i--)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col--;
			}
			
			row = 1;
			col = 6;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 1,6 to 5,2
			for(int i = 5; i > 0; i--)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col--;
			}
			
			row = 2;
			col = 6;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 1,6 to 5,2
			for(int i = 4; i > 0; i--)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col--;
			}
			
			row = 0;
			col = 4;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,4 to 4,0
			for(int i = 5; i > 0; i--)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col--;
			}
			
			row = 0;
			col = 3;
			numConsecutiveMatchesInDiagnal = 0;
			//diagonal from 0,3 to 3,0
			for(int i = 4; i > 0; i--)
			{
				if(board[row][col].getText().equals(currPlayer.getSymbol()))
				{
					numConsecutiveMatchesInDiagnal++;
					if(numConsecutiveMatchesInDiagnal == 4)
					{
						return true;
					}
				}
				else
				{
					numConsecutiveMatchesInDiagnal = 0;
				}
				row++;
				col--;
			}
			
			return false;
		}
		
		public boolean isWinnerInRow()
		{
			for(int row = 0; row < board.length; row++)
			{
				int numConsecutiveMatchesInRow = 0;		//how many of the same symbols are in a row
				for(int col = 0; col <board[row].length; col++)
				{
					if(board[row][col].getText().equals(currPlayer.getSymbol()))
					{
						numConsecutiveMatchesInRow++;
						if(numConsecutiveMatchesInRow == 4)
						{
							return true;
						}
					}
					else
					{
						numConsecutiveMatchesInRow = 0;		//if none are in a row restart counter
					}
				}
			}
			return false;
		}
		
		public boolean isWinnerInCol()
		{
			for(int col = 0; col < NUM_COLS; col++)
			{
				int numConsecutiveMatchesInCol = 0;
				for(int row = 0; row < board.length; row++)
				{
					if(board[row][col].getText().equals(currPlayer.getSymbol()))
					{
						numConsecutiveMatchesInCol++;
						if(numConsecutiveMatchesInCol == 4)
						{
							return true;
						}
					}
					else
					{
						numConsecutiveMatchesInCol = 0;
					}
				}
			}
			return false;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			//e.getActionCommand()  
			e.getSource();
			JButton btnClicked = (JButton)e.getSource();
			btnClicked.setText(currPlayer.getSymbol());//set the symbol on the button
			btnClicked.setEnabled(false);//disable
			
			for(int row = 0; row < board.length; row++)
			{
				for(int col = 0; col < board[row].length; col++)
				{
					if(board[row][col] == btnClicked && row != 0)//find the button on the board
					{
						board[row-1][col].setEnabled(true);
					}
				}
			}
			
			//isWinner
			if(isWinner(currPlayer.getSymbol()))
			{
				displayWinner();
				clearBoard();
			}
			else if(isFull())
			{
				JOptionPane.showMessageDialog(null, "DRAW");
				clearBoard();
			}
			
			takeTurn();
		}//end of actionPerformed
	}

}
