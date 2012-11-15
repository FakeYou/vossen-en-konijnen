package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.helpers.Json;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * A settings tab for each entity
 * 
 * @author FakeYou
 * @verison 2012-11-14
 */
public class SettingsEntityTab extends JPanel
{
	private static final long serialVersionUID = -6423306637988661901L;
	private JTextField txtMaxAge;
	private JTextField txtBreedAge;
	private JTextField txtBreedProbability;
	private JTextField txtBreedMax;
	private JTextField txtCreationProbability;
	private JTextField txtHungerEndurance;
	
	public String entity;
	
	/**
	 * Makes a settings tab for a given entity
	 * 
	 * @param entity name of the entity
	 * @param settings json object with the settings of this entity
	 */
	public SettingsEntityTab(final String entity, Json settings)
	{
		this.entity = entity;
		
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFox = new JLabel(entity);
		this.add(lblFox, "2, 2");
		
		JLabel lblMaximumLeeftijd = new JLabel("Maximum leeftijd");
		this.add(lblMaximumLeeftijd, "2, 6, right, default");
		
		txtMaxAge = new JTextField();
		txtMaxAge.setText(String.valueOf(settings.get("maximumAge").getAsInt()));
		this.add(txtMaxAge, "4, 6, left, default");
		txtMaxAge.setColumns(10);
		txtMaxAge.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0) { update(); }
			public void insertUpdate(DocumentEvent arg0) { update(); }
			public void removeUpdate(DocumentEvent arg0) { update(); }
			
			public void update()
			{
				try
				{
					Vossen.config.set(Integer.valueOf(txtMaxAge.getText()), "simulator", "entities", entity, "maximumAge");
				}
				catch(NumberFormatException e) {}
			}
		});
		
		JLabel lblFokLeeftijd = new JLabel("Fok leeftijd");
		this.add(lblFokLeeftijd, "2, 8, right, default");
		
		txtBreedAge = new JTextField();
		txtBreedAge.setText(String.valueOf(settings.get("breedingAge").getAsInt()));
		this.add(txtBreedAge, "4, 8, left, default");
		txtBreedAge.setColumns(10);
		txtBreedAge.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.config.set(Integer.valueOf(txtBreedAge.getText()), "simulator", "entities", entity, "breedingAge");
			}
		});
		
		JLabel lblFokKans = new JLabel("Fok kans");
		this.add(lblFokKans, "2, 10, right, default");
		
		txtBreedProbability = new JTextField();
		txtBreedProbability.setText(String.valueOf(settings.get("breedingProbability").getAsDouble()));
		this.add(txtBreedProbability, "4, 10, left, default");
		txtBreedProbability.setColumns(10);
		txtBreedProbability.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0) { update(); }
			public void insertUpdate(DocumentEvent arg0) { update(); }
			public void removeUpdate(DocumentEvent arg0) { update(); }
			
			public void update()
			{
				try
				{
					Vossen.config.set(Double.valueOf(txtBreedProbability.getText()), "simulator", "entities", entity, "breedingProbability");
				}
				catch(NumberFormatException e) {}
			}
		});
		
		JLabel lblFokAantal = new JLabel("Fok aantal");
		this.add(lblFokAantal, "2, 12, right, default");
		
		txtBreedMax = new JTextField();
		txtBreedMax.setText(String.valueOf(settings.get("breedingMax").getAsInt()));
		this.add(txtBreedMax, "4, 12, left, default");
		txtBreedMax.setColumns(10);
		txtBreedMax.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0) { update(); }
			public void insertUpdate(DocumentEvent arg0) { update(); }
			public void removeUpdate(DocumentEvent arg0) { update(); }
			
			public void update()
			{
				try
				{
					Vossen.config.set(Integer.valueOf(txtBreedMax.getText()), "simulator", "entities", entity, "breedingMax");
				}
				catch(NumberFormatException e) {}
			}
		});
		
		JLabel lblHongerUithouding = new JLabel("Honger uithouding");
		this.add(lblHongerUithouding, "2, 14, right, default");
		
		txtHungerEndurance = new JTextField();
		txtHungerEndurance.setText(String.valueOf(settings.get("hungerEndurance").getAsInt()));
		this.add(txtHungerEndurance, "4, 14, left, default");
		txtHungerEndurance.setColumns(10);
		txtHungerEndurance.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0) { update(); }
			public void insertUpdate(DocumentEvent arg0) { update(); }
			public void removeUpdate(DocumentEvent arg0) { update(); }
			
			public void update()
			{
				try
				{
					Vossen.config.set(Integer.valueOf(txtHungerEndurance.getText()), "simulator", "entities", entity, "hungerEndurance");
				}
				catch(NumberFormatException e) {}
			}
		});
		
		JLabel lblSpawnKans = new JLabel("Spawn kans");
		this.add(lblSpawnKans, "2, 16, right, default");
		
		txtCreationProbability = new JTextField();
		txtCreationProbability.setText(String.valueOf(settings.get("creationProbability").getAsDouble()));
		this.add(txtCreationProbability, "4, 16, left, default");
		txtCreationProbability.setColumns(10);
		txtCreationProbability.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0) { update(); }
			public void insertUpdate(DocumentEvent arg0) { update(); }
			public void removeUpdate(DocumentEvent arg0) { update(); }
			
			public void update()
			{
				try
				{
					Vossen.config.set(Double.valueOf(txtCreationProbability.getText()), "simulator", "entities", entity, "creationProbability");
				}
				catch(NumberFormatException e) {}
			}
		});
	}
}
