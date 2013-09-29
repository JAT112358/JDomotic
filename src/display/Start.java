package display;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.Arduino;
import utils.Lang;
import utils.Properties;
import components.IButton;
import components.Window;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

/**
 * @author Jordan Aranda Tejada
 */

public class Start extends JPanel implements ActionListener{

	private static final long	serialVersionUID	= - 6855686055738953340L;

	private IButton 	btnSettings;
	private IButton		btnAllOff;
	
	public Start()
	{
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{75, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelMenu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setHgap(10);
		panelMenu.setOpaque(false);
		GridBagConstraints gbc_panelMenu = new GridBagConstraints();
		gbc_panelMenu.gridx = 0;
		gbc_panelMenu.gridy = 0;
		add(panelMenu, gbc_panelMenu);
		
		btnSettings = new IButton("Ajustes");
		btnSettings.setFocusPainted(false);
		btnSettings.setForeground(Color.BLACK);
		btnSettings.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnSettings.addActionListener(this);
		
		btnAllOff = new IButton("Ajustes");
		btnAllOff.addActionListener(this); 
		btnAllOff.setText("Apagar todo");
		btnAllOff.setForeground(Color.BLACK);
		btnAllOff.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnAllOff.setFocusPainted(false);
		panelMenu.add(btnAllOff);
		
		IButton btnAddItem = new IButton("Ajustes");
		btnAddItem.setFocusPainted(false);
		btnAddItem.setText("A\u00F1adir dispositivo");
		btnAddItem.setForeground(Color.BLACK);
		btnAddItem.setFont(new Font("Calibri", Font.PLAIN, 25));
		panelMenu.add(btnAddItem);
		panelMenu.add(btnSettings);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		final JList<String> listItems = new JList<String>();
		listItems.setOpaque(false);
		listItems.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getClickCount() == 2)
				{
					Window.getInstance().setContentPane(new ItemPanel(listItems.getSelectedIndex()));
					((JPanel) Window.getInstance().getContentPane()).updateUI();
				}
			}
		});
		
		
		
		listItems.setForeground(Color.BLACK);
		listItems.setFont(new Font("Calibri", Font.PLAIN, 25));
		scrollPane.setViewportView(listItems);
		
		DefaultListModel modelo = new DefaultListModel();

		for(int i=0; i<Properties.getItems().size(); i++)
		{
			modelo.addElement(Properties.getItems().get(i).getName());
		}

		listItems.setModel(modelo);
	}

	
	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				if(Properties.getLookAndFeel().substring(0, 3).equals("org"))
				{	
					try {
						SubstanceLookAndFeel.setSkin(Properties.getLookAndFeel());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						UIManager.setLookAndFeel(Properties.getLookAndFeel());
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException | UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}	
				}
				//ItemPanel iPanel = new ItemPanel(Properties.getItems().get(0));
				Start st = new Start();
				Window.getInstance().setContentPane(st);
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
				Arduino.getInstance();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnSettings)
		{
			final SettingsPanel p = new SettingsPanel();

			final String[] options = {Lang.getLine("accept_option"), Lang.getLine("cancel_option")};
			final JOptionPane pane = new JOptionPane(p, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
			options, options[1]);
			final JDialog dialog = pane.createDialog("Ajustes del servidor");
			dialog.setLocationRelativeTo(Window.getInstance());
			dialog.setResizable(false);
			dialog.setSize(500, 300);
			dialog.setVisible(true);

			if (pane.getValue() == options[0])
			{
				Properties.setLocale(Lang.getAvailableLocales().get(p.getComboBox_language().getSelectedIndex()));
				Lang.setLang(Lang.getAvailableLocales().get(p.getComboBox_language().getSelectedIndex()));

				Properties.setLookAndFeelClass(p.getLookAndFeel());
				
				if(Properties.getLookAndFeel().substring(0, 3).equals("org"))
				{
					SubstanceLookAndFeel.setSkin(Properties.getLookAndFeel());
				} else {
					try {
						UIManager.setLookAndFeel(Properties.getLookAndFeel());
					} catch (ClassNotFoundException
							| InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}	
				}
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
			dialog.dispose();
		} else if(e.getSource() == btnAllOff)
		{
			Properties.allOff();
		}
		
	}
}