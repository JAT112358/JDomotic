package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Vector;

import javax.swing.UIManager;

import entities.Item;

/**
 * @author Jordan Aranda Tejada
 */

public class Properties implements Serializable {

	private static final long	serialVersionUID	= 4780308659003926926L;

	private static Properties	properties;
	private Vector <Item>		items;
	private Locale				locale;
	private String				lookAndFeelClass;
	private String				version;

	private Properties(Vector <Item> items, Locale locale, String lookAndFeelClass, String version)
	{
		this.items = items;
		this.locale = locale;
		this.lookAndFeelClass = lookAndFeelClass;
		this.version = version;
	}

	private void update()
	{
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream(
			"data/config.properties"));
			oos.writeObject(properties);
			oos.close();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			properties = new Properties(new Vector<Item>(), Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), "1.0");
		}
	}

	private static void init()
	{
		ObjectInputStream ois;
		try
		{
			ois = new ObjectInputStream(new FileInputStream(
			"data/config.properties"));
			properties = (Properties) ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			if ( ! (e instanceof FileNotFoundException))
			{
				e.printStackTrace();
			}

			properties = new Properties(new Vector<Item>(), Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), "1.0");
		}
	}
	public static void addItem(Item item) 
	{
		if (properties == null)
		{
			init();
		}
		properties.items.add(properties.items.size(), item);
		properties.update();		
	}
	
	public static Vector<Item> getItems() 
	{
		if (properties == null)
		{
			init();
		}
		return properties.items;
	}

	public void setItems(Vector<Item> items) 
	{
		if (properties == null)
		{
			init();
		}
		properties.items = items;
		properties.update();
	}

	/**
	 * @return Current locale
	 */
	public static Locale getLocale()
	{
		if (properties == null)
		{
			init();
		}

		return properties.locale;
	}

	/**
	 * @param l Locale to set as default
	 */
	public static void setLocale(final Locale l)
	{
		if (properties == null)
		{
			init();
		}

		if (Lang.getAvailableLocales().contains(l))
		{
			properties.locale = l;
		}
		else
		{
			properties.locale = Lang.getDefaultLocale();
		}

		properties.update();
	}

	/**
	 * @return Current look and feel
	 */
	public static String getLookAndFeel()
	{
		if (properties == null)
		{
			init();
		}

		return properties.lookAndFeelClass;
	}

	/**
	 * @param lf The new Look and feel to set
	 */
	public static void setLookAndFeelClass(final String lf)
	{
		if (properties == null)
		{
			init();
		}

		properties.lookAndFeelClass = lf;
		properties.update();
	}

	/**
	 * @return Version of application
	 */
	public static String getVersion()
	{
		if (properties == null)
		{
			init();
		}

		return properties.version;
	}

	/**
	 * @param version New version of application
	 */
	public static void setVersion(final String version)
	{
		if (properties == null)
		{
			init();
		}

		properties.version = version;

		properties.update();
	}

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("LECTURA DE DATOS");
		System.out.println("LANGUAGE = " + Properties.getLocale().toString());
		System.out.println("VERSION = " + Properties.getVersion());
		System.out.println("LF = " + Properties.getLookAndFeel());
		System.out.println("ITEMS: ");
		for(int i=0; i<Properties.getItems().size(); i++)
		{
			System.out.println(" - "+Properties.getItems().get(i).getName());
		}
		
		System.out.println("\nMODIFICACION DE DATOS");
		//Properties.addItem(new Item("Luz Estudio", false, 0, 13));

		//Properties.setLookAndFeelClass("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

	}
}
