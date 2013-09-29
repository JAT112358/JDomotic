package utils;

import java.awt.Component;
import java.awt.Font;
import java.util.Hashtable;


import javax.swing.*;


public class RenderList extends JLabel implements ListCellRenderer<Object>
{
	private static final long serialVersionUID = 2220731788955637901L;
	Hashtable<Object, ImageIcon> elementos;
	ImageIcon icononulo = new ImageIcon(this.getClass().getResource("../Iconos/imagen nulo.png"));
 
	public RenderList()
	{
		elementos = new Hashtable<Object, ImageIcon>();
		ImageIcon icono1=new ImageIcon(this.getClass().getResource("../Iconos/imagen 1.png"));
		ImageIcon icono2=new ImageIcon(this.getClass().getResource("../Iconos/imagen 2.png"));
		ImageIcon icono3=new ImageIcon(this.getClass().getResource("../Iconos/imagen 3.png"));
		elementos.put("Elemento 1", icono1);
		elementos.put("Elemento 2", icono2);
		elementos.put("Elemento 3", icono3);
	}

	public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus) 
	{
		if(elementos.get(value) != null)
		{
			setIcon(elementos.get(value));
			setText(""+value);
			if(isSelected)
			{
				setFont(new Font("Verdana",Font.BOLD,16));
			}else{
				setFont(null);
			}
		}else{
			setIcon(icononulo);
			setText(""+value);
			if(isSelected)
			{
				setFont(new Font("Verdana",Font.BOLD,16));
			}else{
				setFont(null);
			}
		}
		return this;
	}
}