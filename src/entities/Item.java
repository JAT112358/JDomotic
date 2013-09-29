package entities;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Item implements Serializable
{
	private static final long serialVersionUID = -8221614746128028892L;

	public static int	light = 0;
	public static int	fan = 1;
	
	private String 	name;
	private boolean connected;
	private int		type;
	private int		arduinoPort;
	
	public Item()
	{
		
	}

	public Item(String name, boolean connected, int type, int arduinoPort) 
	{
		this.name = name;
		this.connected = connected;
		this.type = type;
		this.arduinoPort = arduinoPort;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean isConnected() 
	{
		return connected;
	}

	public void setConnected(boolean connected) 
	{
		this.connected = connected;
	}
	
	public int getType() 
	{
		return type;
	}

	public void setType(int type) 
	{
		this.type = type;
	}

	public int getArduinoPort() 
	{
		return arduinoPort;
	}

	public void setArduinoPort(int arduinoPort) 
	{
		this.arduinoPort = arduinoPort;
	}
	
	public ImageIcon getImage()
	{
		ImageIcon tmpIcon;
		ImageIcon imageIcon = null;
		if(type == Item.light)
		{
			tmpIcon = new ImageIcon("img/light-icon.jpg"); 
			imageIcon = new ImageIcon(tmpIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		} else if(type == Item.fan) {
			
		}
		return imageIcon;
	}
}
