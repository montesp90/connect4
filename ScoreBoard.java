import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class ScoreBoard extends JPanel
	{
		private JLabel lblChamp, lblLatestWinner, lblPlaceHolder, 
		   			   lblPlayerNames, lblPlayerNumWins,lblPlayer1Name,
		  			   lblPlayer2Name, lblPlayer1NumWins, lblPlayer2NumWins;
		  			   
		private JPanel jpScoreInfo;
		private JPanel jpPlayerScoreInfo;
		
		public ScoreBoard()
		{
			setLayout(new BorderLayout());
			jpScoreInfo = new JPanel();
			jpScoreInfo.setLayout(new GridLayout(1,2));
			jpScoreInfo.setBackground(Color.yellow);
			lblChamp = new JLabel("CHAMP");
			lblLatestWinner = new JLabel("*****");
			jpScoreInfo.add(lblChamp);
			jpScoreInfo.add(lblLatestWinner);
			
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