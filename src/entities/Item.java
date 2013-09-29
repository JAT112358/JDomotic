package entities;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

import utils.Arduino;

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
		ImageIcon imageIcon = null;
		if(type == Item.light)
		{
			imageIcon = new ImageIcon("img/light-icon.png"); 
		} else if(type == Item.fan) {
			
		}
		return imageIcon;
	}
	
	public void connect ()
	{
		Arduino.getInstance().sendData((arduinoPort+1)+"");
		connected = true;
	}
	
	public void disconnect ()
	{
		Arduino.getInstance().sendData(arduinoPort+"");
		connected = false;
	}
}
