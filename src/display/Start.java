package display;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import utils.Lang;
import utils.Properties;
import components.IButton;
import components.Window;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * @author Jordan Aranda Tejada
 */

public class Start extends JPanel implements ActionListener{

	private static final long	serialVersionUID	= - 6855686055738953340L;

	private IButton 	btnSettings;
	
	public Start()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{75, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelMenu = new JPanel();
		GridBagConstraints gbc_panelMenu = new GridBagConstraints();
		gbc_panelMenu.insets = new Insets(0, 0, 5, 0);
		gbc_panelMenu.gridx = 0;
		gbc_panelMenu.gridy = 0;
		add(panelMenu, gbc_panelMenu);
		
		btnSettings = new IButton("Ajustes");
		btnSettings.setForeground(Color.BLACK);
		btnSettings.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnSettings.addActionListener(this);
		
		IButton btnAddItem = new IButton("Ajustes");
		btnAddItem.setText("A\u00F1adir dispositivo");
		btnAddItem.setForeground(Color.BLACK);
		btnAddItem.setFont(new Font("Calibri", Font.PLAIN, 25));
		panelMenu.add(btnAddItem);
		panelMenu.add(btnSettings);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JList listItems = new JList();
		listItems.setForeground(Color.BLACK);
		listItems.setFont(new Font("Calibri", Font.PLAIN, 20));
		listItems.setOpaque(false);
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
			dialog.pack();
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
		} 
		
	}
}