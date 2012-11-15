
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


/**
 * Main class, this class runs the main program loop where all the operations of the program are executed
 * 
 * @author FakeYou
 * @version 2012-11-15
 */
public class Vossen extends JFrame
{
	private static final long serialVersionUID = 1186051179116663399L;

	// Json config worker
	public static Json config = new Json();
	
	public static Simulator simulator;
	public static boolean simulate;
	public static int simulateSteps;
	private boolean running;

	public UI ui;
	
	private JLabel lblSteps;

	// long term simulation data
	private static DefaultCategoryDataset dataset;
	// short teem simulation data
	private static DefaultCategoryDataset singleDataset;
	
	/**
	 * Contstructor, the config is loaded, simulator started and UI built
	 */
	public Vossen()
	{
		// load json file
		config.loadFile("/config/config.json");
		
		running = true;
		simulate = false;
		simulateSteps = 0;
		simulator = new Simulator(80, 80); // create new simulator with a 80x80 field
		simulator.reset();

		dataset = new DefaultCategoryDataset();
		singleDataset = new DefaultCategoryDataset();
		
		// create the UserInterface
		ui = new UI();
		
		// start the main execution loop
		loop();		
	}
	
	/**
	 * The main program loop. All necessary program section all handled here
	 */
	public void loop()
	{
		long tick = System.nanoTime();
		long ticks = 0;
		long totalTicks = 0;
		int ticksPerSecond = 30;
		long deltaSinceUpdate = 0;
		
		while(running)
		{
			float delta = (System.nanoTime() - tick) / 1000000.0f;
			tick = System.nanoTime();
			deltaSinceUpdate += delta;
			
			// check if its time to simulate another step based on the ticksPerSecond
			if(simulate && deltaSinceUpdate > 1000 / ticksPerSecond)
			{
				totalTicks += 1;
				deltaSinceUpdate = 0;
				simulator.simulate();
				simulateSteps -= 1;
				
				// stop the simulator if not entities are left
				if(simulator.entities.isEmpty())
				{
					System.out.println("stopped at: " + totalTicks);
					simulate = false;
				}
				
				simulator.field.clear();
				updateStats();
			}

			// repaint the views with the new data
			ui.frame.repaint();
			
			// pause the simulation if the given steps are done
			if(simulateSteps <= 0)
			{
				simulate = false;
			}
		}
	}
	
	/**
	 * Update the long term and short term simulation data for the views
	 */
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
	
	/**
	 * @return returns the long term simulation data
	 */
	public static DefaultCategoryDataset getStats()
	{
		return dataset;
	}
	
	/**
	 * @return returns the short them simulator data
	 */
	public static DefaultCategoryDataset getSingleStats()
	{
		return singleDataset;
	}
	
	/**
	 * resets the stats
	 */
	public static void resetStats()
	{
		dataset.clear();
		singleDataset.clear();
	}
	
	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Vossen();
	}
}
