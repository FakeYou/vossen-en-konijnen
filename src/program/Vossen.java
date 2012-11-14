
package program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vossen.Simulator;

import javax.swing.border.BevelBorder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import program.helpers.Json;
import program.view.PieView;
import program.view.LineView;
import program.view.GridView;
import javax.swing.JLabel;

import org.jfree.data.category.DefaultCategoryDataset;

public class Vossen extends JFrame
{
	private static final long serialVersionUID = 1186051179116663399L;

	public static Json config = new Json();
	
	public static final String TITLE = "Vossen en konijnen 0.1";
	
	public static final int FIELD_WIDTH = 80;
	public static final int FIELD_HEIGHT = 60;
	
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	public static Simulator simulator;
	public static boolean simulate;
	public static int simulateSteps;
	private boolean running;

	public UI ui;
	
	private JLabel lblSteps;

	private static DefaultCategoryDataset dataset;
	
	public Vossen()
	{
		config.loadFile("/config/config.json");
		
		running = true;
		simulate = false;
		simulateSteps = 0;
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);
		simulator.reset();

		dataset = new DefaultCategoryDataset();
		
		ui = new UI();
		
		loop();		
	}
	
	public static void updateStats()
	{		
		HashMap<String, Integer> count = Vossen.simulator.countEntities();
		
		Iterator<Entry<String, Integer>> it = count.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			
			dataset.addValue((Number) entry.getValue(), entry.getKey().toString(), Vossen.simulator.step);
		}
	}
	
	public static DefaultCategoryDataset getStats()
	{
		return dataset;
	}
	
	public void loop()
	{
		long tick = System.nanoTime();
		long ticks = 0;
		long totalTicks = 0;
		int ticksPerSecond = 60;
		long deltaSinceUpdate = 0;
		
		while(running)
		{
			float delta = (System.nanoTime() - tick) / 1000000.0f;
			tick = System.nanoTime();
			deltaSinceUpdate += delta;
			
			if(simulate && deltaSinceUpdate > 1000 / ticksPerSecond)
			{
				totalTicks += 1;
				deltaSinceUpdate = 0;
				simulator.simulate();
				simulateSteps -= 1;
				
				if(simulator.entities.isEmpty())
				{
					System.out.println("stopped at: " + totalTicks);
					simulate = false;
				}
				
				simulator.field.clear();
				updateStats();
			}

			ui.frame.repaint();
			
			if(simulateSteps <= 0)
			{
				simulate = false;
			}
			
			//ui.lblSteps.setText("steps: " + totalTicks);
		}
	}
	
	public void ui(JFrame frame)
	{
		JPanel sidepanel = new JPanel();
		sidepanel.setBounds(10, 11, 148, 420);
		
		JButton btnStartSimulatie = new JButton("Start");
		btnStartSimulatie.setBounds(10, 5, 133, 23);
		btnStartSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = true;
				Vossen.simulateSteps = Integer.MAX_VALUE;
			}
		});
		sidepanel.add(btnStartSimulatie);
		
		JButton btnStopSimulatie = new JButton("Pauze");
		btnStopSimulatie.setBounds(10, 33, 133, 23);
		btnStopSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = false;
			}
		});
		sidepanel.add(btnStopSimulatie);
		
		JButton btnStepSimulatie = new JButton("1 stap");
		btnStepSimulatie.setBounds(10, 61, 133, 23);
		btnStepSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = true;
				Vossen.simulateSteps = 1;
			}
		});
		sidepanel.add(btnStepSimulatie);
		
		JButton btnResetSimulatie = new JButton("Reset");
		btnResetSimulatie.setBounds(10, 140, 133, 23);
		btnResetSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = false;
				Vossen.simulateSteps = 0;
				
				Vossen.simulator.reset();
			}
		});
		sidepanel.add(btnResetSimulatie);
		
		lblSteps = new JLabel("Steps: 0");
		lblSteps.setBounds(10, 395, 128, 14);
		sidepanel.add(lblSteps);
		
		frame.getContentPane().add(sidepanel, BorderLayout.WEST);
		sidepanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(168, 11, 446, 420);
		frame.getContentPane().add(panel, BorderLayout.EAST);
	}
	
	public static void main(String[] args)
	{
		new Vossen();
	}
}
