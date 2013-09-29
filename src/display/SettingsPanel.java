package display;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SkinInfo;
import components.ILabel;
import utils.Lang;

/**
 * @author Jordan Aranda Tejada
 */

public class SettingsPanel extends JPanel 
{
	private static final long serialVersionUID = 3726753153976194226L;
	private JComboBox<String>		comboBox_language;
	private JComboBox<String> 		comboBox_appearance;
	private HashMap<String, String> lookNFeelHashMap;
	private String					currentLookAndFeel;

	public SettingsPanel() 
	{
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
				
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{3.0, 0.0, 1.0, 0.0, 3.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		ILabel lblTitle = new ILabel();
		Lang.setLine(lblTitle, "tool_tip_text_settings");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Calibri", Font.BOLD, 50));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 5;
		gbc_lblTitle.insets = new Insets(10, 0, 30, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panel.add(lblTitle, gbc_lblTitle);
		
		ILabel lblLanguage = new ILabel();
		Lang.setLine(lblLanguage, "panel_settings_lbl_language");
		lblLanguage.setForeground(Color.BLACK);
		lblLanguage.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblLanguage = new GridBagConstraints();
		gbc_lblLanguage.anchor = GridBagConstraints.WEST;
		gbc_lblLanguage.insets = new Insets(0, 0, 10, 10);
		gbc_lblLanguage.gridx = 1;
		gbc_lblLanguage.gridy = 1;
		panel.add(lblLanguage, gbc_lblLanguage);
		
		comboBox_language = new JComboBox<String>(Lang.getCombableLocales());
		comboBox_language.setForeground(Color.BLACK);
		comboBox_language.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBox_language.setSelectedIndex(Lang.getCurrentLocaleKey());
		GridBagConstraints gbc_comboBox_language = new GridBagConstraints();
		gbc_comboBox_language.gridwidth = 2;
		gbc_comboBox_language.insets = new Insets(0, 0, 10, 5);
		gbc_comboBox_language.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_language.gridx = 2;
		gbc_comboBox_language.gridy = 1;
		panel.add(comboBox_language, gbc_comboBox_language);
		
		ILabel lblAppearance = new ILabel("Apariencia");
		lblAppearance.setForeground(Color.BLACK);
		lblAppearance.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblAppearance = new GridBagConstraints();
		gbc_lblAppearance.insets = new Insets(0, 0, 10, 10);
		gbc_lblAppearance.gridx = 1;
		gbc_lblAppearance.gridy = 2;
		panel.add(lblAppearance, gbc_lblAppearance);
		
		comboBox_appearance = new JComboBox<String>(getAvailableLF());
		comboBox_appearance.setSelectedItem(currentLookAndFeel);
		comboBox_appearance.setForeground(Color.BLACK);
		comboBox_appearance.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_comboBox_appearance = new GridBagConstraints();
		gbc_comboBox_appearance.gridwidth = 2;
		gbc_comboBox_appearance.insets = new Insets(0, 0, 10, 5);
		gbc_comboBox_appearance.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_appearance.gridx = 2;
		gbc_comboBox_appearance.gridy = 2;
		panel.add(comboBox_appearance, gbc_comboBox_appearance);
	}
	
	private Vector<String> getAvailableLF()
	{
		final LookAndFeelInfo lfs[] = UIManager.getInstalledLookAndFeels();
		
		lookNFeelHashMap = new HashMap<>(lfs.length);
		final Vector<String> v = new Vector<>(lfs.length);

		for (final LookAndFeelInfo lf2: lfs)
		{
			lookNFeelHashMap.put(lf2.getName(), lf2.getClassName());
			v.add(lf2.getName());
			if (utils.Properties.getLookAndFeel().equals(lf2.getClassName()))
			{
				currentLookAndFeel = lf2.getName();
			}
		}
		
		// SUBSTANCE LookAndFeels
		TreeMap<String, SkinInfo> treemap = (TreeMap<String, SkinInfo>) SubstanceLookAndFeel.getAllSkins();
		Iterator<Entry<String,SkinInfo>> it = treemap.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,SkinInfo> e = (Entry<String,SkinInfo>)it.next();
			v.add(e.getKey());
			lookNFeelHashMap.put(e.getKey(), e.getValue().getClassName());
			if (utils.Properties.getLookAndFeel().equals(e.getValue().getClassName()))
			{
				currentLookAndFeel = e.getKey();
			}
		}
		return v;
	}

	public JComboBox<String> getComboBox_language() 
	{
		return comboBox_language;
	}

	public String getLookAndFeel()
	{
		return lookNFeelHashMap.get(comboBox_appearance.getSelectedItem());
	}
}