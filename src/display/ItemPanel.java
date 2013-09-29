package display;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import utils.Properties;
import entities.Item;
import javax.swing.JButton;

import components.Window;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ItemPanel extends JPanel
{
	private static final long serialVersionUID = -1557098703746234543L;

	private Item	item;
	private String 	name;
	private JLabel	image;
	
	private JButton btnOff;
	private JButton btnOn;
	
	public ItemPanel(int itemIndex) 
	{
		this.item = Properties.getItems().get(itemIndex);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{44, 181, 75, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblItemname = new JLabel(item.getName());
		lblItemname.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemname.setForeground(Color.BLACK);
		lblItemname.setFont(new Font("Calibri", Font.PLAIN, 50));
		GridBagConstraints gbc_lblItemname = new GridBagConstraints();
		gbc_lblItemname.insets = new Insets(15, 0, 5, 0);
		gbc_lblItemname.gridx = 0;
		gbc_lblItemname.gridy = 0;
		add(lblItemname, gbc_lblItemname);
		
		image = new JLabel();
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(item.getImage());
		image.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		GridBagConstraints gbc_image = new GridBagConstraints();
		gbc_image.fill = GridBagConstraints.BOTH;
		gbc_image.insets = new Insets(0, 0, 5, 0);
		gbc_image.gridx = 0;
		gbc_image.gridy = 1;
		add(image, gbc_image);
		
		JPanel btnsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnsPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		GridBagConstraints gbc_btnsPanel = new GridBagConstraints();
		gbc_btnsPanel.gridx = 0;
		gbc_btnsPanel.gridy = 2;
		add(btnsPanel, gbc_btnsPanel);		
		
		btnOn = new JButton("Encender");
		btnOn.setFocusPainted(false);
		btnOn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.connect();
				item.setConnected(true);
				btnOn.setVisible(false);
				btnOff.setVisible(true);
				image.setIcon(new ImageIcon("img/light-on-icon.png"));
			}
		});
		btnOn.setBackground(new Color(50, 205, 50));
		btnOn.setForeground(Color.BLACK);
		btnOn.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnsPanel.add(btnOn);
		
		btnOff = new JButton("Apagar");
		btnOff.setFocusPainted(false);
		btnOff.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.disconnect();
				item.setConnected(false);
				btnOn.setVisible(true);
				btnOff.setVisible(false);
				image.setIcon(new ImageIcon("img/light-off-icon.png"));
			}
		});
		btnOff.setBackground(new Color(250, 128, 114));
		btnOff.setForeground(Color.BLACK);
		btnOff.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnsPanel.add(btnOff);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFocusPainted(false);
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Window.getInstance().setContentPane(new Start());
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnsPanel.add(btnVolver);
		
		if(this.item.isConnected())
		{
			btnOn.setVisible(false);
			image.setIcon(new ImageIcon("img/light-on-icon.png"));
		} else {
			btnOff.setVisible(false);
			image.setIcon(new ImageIcon("img/light-off-icon.png"));
		}
	}
}