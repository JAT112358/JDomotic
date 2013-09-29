package components;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage((new ImageIcon("img/light-on-icon.png")).getImage());
		setSize(600, 500);
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setTitle("JDomotic");
		setDefaultLookAndFeelDecorated(true);
		
		addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(final WindowEvent winEvt)
			{

				final String[] options = {"Si", "No"};
				final int selection = JOptionPane.showOptionDialog(
				Window.getInstance(), "Si sales y vuelves a entrar todos los aparatos electrónicos se reiniciaran. ¿Estas seguro de querer salir?",
				"Salir", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, new ImageIcon(
				"img/warning.png"), options, options[1]);

				if (selection == 0)
				{
					System.exit(0);
				}
			}
				
		});
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