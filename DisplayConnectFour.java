
public class DisplayConnectFour 
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				ConnectFour gui = new ConnectFour();
			}
		});
	}
}
