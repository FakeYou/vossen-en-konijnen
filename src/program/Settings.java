package program;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.google.gson.JsonElement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import program.helpers.Json;
import javax.swing.JButton;

/**
 * Settings panel for the simulation
 * 
 * @author FakeYou
 * @version 2012-11-14
 */
public class Settings
{
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Constructor for the settings, builds the settings frame
	 */
	public Settings()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setTitle("Settings");
		frame.setVisible(true);
		
		JButton btnSaveButton = new JButton("Save settings");
		frame.getContentPane().add(btnSaveButton, BorderLayout.SOUTH);
		btnSaveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel SimulatorPanel = new JPanel();
		tabbedPane.addTab("Simulator", null, SimulatorPanel, null);
		SimulatorPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblBreedte = new JLabel("Breedte");
		SimulatorPanel.add(lblBreedte, "2, 2, right, default");
		
		textField = new JTextField();
		textField.setText("100");
		SimulatorPanel.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblHoogte = new JLabel("Hoogte");
		SimulatorPanel.add(lblHoogte, "2, 4, right, default");
		
		textField_1 = new JTextField();
		textField_1.setText("100");
		SimulatorPanel.add(textField_1, "4, 4, left, default");
		textField_1.setColumns(10);

		/*
		 * for every entity in the config we make a tab with the necessary settings for that entity
		 */
		Json entities = Vossen.config.getAsJson("simulator", "entities");
		
		for(Entry<String, JsonElement> entry : entities.getObject().entrySet())
		{
			String entity = entry.getKey();
			
			SettingsEntityTab tab = new SettingsEntityTab(entity, entities.getAsJson(entity));
			tabbedPane.addTab(entity, null, tab, null);
		}
	}
}
