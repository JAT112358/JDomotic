package display;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import entities.Item;

public class ItemPanel extends JPanel
{
	private static final long serialVersionUID = -1557098703746234543L;

	private Item	item;
	private String 	name;
	private JLabel	image;
	
	public ItemPanel(Item item) 
	{
		this.item = item;
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblItemname = new JLabel(item.getName());
		lblItemname.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemname.setForeground(Color.BLACK);
		lblItemname.setFont(new Font("Calibri", Font.PLAIN, 35));
		add(lblItemname, BorderLayout.NORTH);
		
		image = new JLabel();
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(item.getImage());
		image.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		add(image, BorderLayout.CENTER);
		
		JPanel btnsPanel = new JPanel();
		add(btnsPanel, BorderLayout.SOUTH);		
	}
}