package components;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

/**
 * @author Jordan Aranda Tejada
 */

public class IPPanel extends JPanel {

	private static final long 	serialVersionUID = -1218654215199884472L;
	private String				ip;
	private JTextField 			textField1;
	private JTextField 			textField2;
	private JTextField 			textField3;
	private JTextField 			textField4;

	/**
	 * Create the panel.
	 */
	public IPPanel(String ip) 
	{
		this.ip = ip;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 3, 50, 3, 50, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField1 = new JTextField(getIPByte(1));
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setForeground(Color.BLACK);
		textField1.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField1.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				char car = e.getKeyChar();
				if(textField1.getText().length()>=2) 
				{ 
					textField2.requestFocus();
					textField2.selectAll();
					
				}
				if((car < '0' || car > '9') && car != 127) 
				{
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.gridx = 0;
		gbc_textField1.gridy = 0;
		add(textField1, gbc_textField1);
		
		JLabel lblPoint1 = new JLabel(".");
		lblPoint1.setForeground(Color.BLACK);
		lblPoint1.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_lblPoint1 = new GridBagConstraints();
		gbc_lblPoint1.gridx = 1;
		gbc_lblPoint1.gridy = 0;
		add(lblPoint1, gbc_lblPoint1);
		
		textField2 = new JTextField(getIPByte(2));
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setForeground(Color.BLACK);
		textField2.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField2.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				char car = e.getKeyChar();
				if(textField2.getText().length()>=2) 
				{ 
					textField3.requestFocus();
					textField3.selectAll();
					
				}
				if((car < '0' || car > '9') && car != 127) 
				{
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_textField2 = new GridBagConstraints();
		gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField2.gridx = 2;
		gbc_textField2.gridy = 0;
		add(textField2, gbc_textField2);
		
		JLabel lblPoint2 = new JLabel(".");
		lblPoint2.setForeground(Color.BLACK);
		lblPoint2.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_lblPoint2 = new GridBagConstraints();
		gbc_lblPoint2.gridx = 3;
		gbc_lblPoint2.gridy = 0;
		add(lblPoint2, gbc_lblPoint2);
		
		textField3 = new JTextField(getIPByte(3));
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setForeground(Color.BLACK);
		textField3.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField3.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				char car = e.getKeyChar();
				if(textField3.getText().length()>=2) 
				{ 
					textField4.requestFocus();
					textField4.selectAll();
					
				}
				if((car < '0' || car > '9') && car != 127) 
				{
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_textField3 = new GridBagConstraints();
		gbc_textField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField3.gridx = 4;
		gbc_textField3.gridy = 0;
		add(textField3, gbc_textField3);
		
		JLabel lblPoint3 = new JLabel(".");
		lblPoint3.setForeground(Color.BLACK);
		lblPoint3.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_lblPoint3 = new GridBagConstraints();
		gbc_lblPoint3.gridx = 5;
		gbc_lblPoint3.gridy = 0;
		add(lblPoint3, gbc_lblPoint3);
		
		textField4 = new JTextField(getIPByte(4));
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		textField4.setForeground(Color.BLACK);
		textField4.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField4.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				char car = e.getKeyChar();
				if(textField4.getText().length()>=2) 
				{ 
					e.consume();
					
				}
				if((car < '0' || car > '9') && car != 127) 
				{
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_textField4 = new GridBagConstraints();
		gbc_textField4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4.gridx = 6;
		gbc_textField4.gridy = 0;
		add(textField4, gbc_textField4);
	}
	
	public String getIPByte(int index)
	{
		String number = "";
		boolean enc = false;
		int i = 0;
		
		if(index == 1)
		{
			while(!enc && i<3)
			{
				if(ip.charAt(i) != '.')
				{
					number += ip.charAt(i);
					i++;
				} else {
					enc = true;
				}
			}
		} else if(index == 4)
		{
			int points = 0;
			while(i < ip.length())
			{
				if(points<3)
				{
					if(ip.charAt(i) == '.')
					{
						points++;
					}
					i++;
				} else {
					number += ip.charAt(i);
					i++;
				}
			}
			
		} else {
			int points = 0;
			while(i<ip.length())
			{
				if(ip.charAt(i) == '.')
				{
					points++;
				} else if(points == index-1)
				{
					number += ip.charAt(i);
				}
				i++;
			}
		}
		return number;
	}
	
	
	public String getIP()
	{
		return textField1.getText().trim() + "." + textField2.getText().trim() + "." + textField3.getText().trim() + "." +textField4.getText().trim();
	}

}
