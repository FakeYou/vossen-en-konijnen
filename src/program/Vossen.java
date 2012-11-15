
package program;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import vossen.Simulator;

import program.helpers.Json;
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
	private static DefaultCategoryDataset singleDataset;
	
	public Vossen()
	{
		config.loadFile("/config/config.json");
		
		running = true;
		simulate = false;
		simulateSteps = 0;
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);
		simulator.reset();

		dataset = new DefaultCategoryDataset();
		singleDataset = new DefaultCategoryDataset();
		
		ui = new UI();
		
		loop();		
	}
	
	public static void updateStats()
	{		
		singleDataset.clear();
		
		HashMap<String, Integer> count = Vossen.simulator.countEntities();
		
		Iterator<Entry<String, Integer>> it = count.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			
			dataset.addValue((Number) entry.getValue(), entry.getKey().toString(), Vossen.simulator.step);
			singleDataset.addValue((Number) entry.getValue(), entry.getKey().toString(), 0);			
		}
	}
	
	public static DefaultCategoryDataset getStats()
	{
		return dataset;
	}
	
	public static DefaultCategoryDataset getSingleStats()
	{
		return singleDataset;
	}
	
	public static void resetStats()
	{
		dataset.clear();
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
	
	public static void main(String[] args)
	{
		new Vossen();
	}
}
