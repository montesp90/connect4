
public class Player 
{
	private String name;
	private String symbol;
	private int numGames;
	private int numWins;
	private int numLosses;
	private int numDraws;
	
	public Player()
	{
		symbol = " ? ";
		name = "Player Doe";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		numDraws = 0;
	}
	
	public Player(String symbol)
	{
		this();
		this.symbol = symbol;
	}
	
	public Player(String name, String symbol)
	{
		this();
		this.symbol = symbol;
		this.name = name;
	}
	
	protected void addWin()
	{
		numGames++;
		numWins++;
	}
	
	protected void addLoss()
	{
		numGames++;
		numLosses++;
	}
	
	protected void addDraw()
	{
		numGames++;
		numDraws++;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSymbol()
	{
		return symbol;
	}
	
	public int getNumGames()
	{
		return numGames;
	}
	
	public int getNumWins()
	{
		return numWins;
	}
	
	public int getNumLosses()
	{
		return numLosses;
	}
	
	public int getNumDraws()
	{
		return numDraws;
	}
	
	public void setNumWins(int num)
	{
		this.numWins = num;
	}
	
	public boolean equals(Object obj)
	{
		boolean equal = false;
		
		if(obj instanceof Player)
		{
			Player otherPlayer = (Player)obj;
			
			if(this.name == otherPlayer.getName())
			{
				equal = true;
			}
			else
			{
				equal = false;
			}
		}
		
		return equal;
	}
}
