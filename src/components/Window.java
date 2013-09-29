package components;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import utils.Properties;

/**
 * @author Jordan Aranda Tejada
 */

public class Window extends JFrame implements Internationalizable {

	private static final long	serialVersionUID	= - 8641413596663241575L;
	private static Window		instance;

	private Window()
	{
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage((new ImageIcon("img/server-icon.png")).getImage());
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("JDomotic");
		setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * @return instance of the window
	 */
	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}

	@Override
	public void changeLanguage(String newText)
	{
		setTitle(newText + " " + Properties.getVersion() + "]");
	}
}