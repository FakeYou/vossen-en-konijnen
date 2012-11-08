package program;

import javax.swing.JFrame;

import vossen.Simulator;

import javax.swing.border.BevelBorder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import program.helpers.Json;
import program.view.View;

public class Main extends JFrame
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
	private boolean running;
	
	public Main()
	{
		config.loadFile("/config/config.json");
		JsonObject app = config.get("application").getAsJsonObject();
		
		setEnabled(true);
		
		config.set("Hello world", "application", "title");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(app.get("width").getAsInt(), app.get("height").getAsInt());
		setTitle(app.get("title").getAsString());
		setVisible(true);
		getContentPane().setLayout(null);
		
		running = true;
		simulate = false;
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);

		UI.mainScreen(this);
		
		loop();		
	}
	
	public void loop()
	{
		long tick = System.nanoTime();
		long ticks = 0;
		long totalTicks = 0;
		double simulateSpeed = 0.5;
		
		while(running)
		{
			float delta = (System.nanoTime() - tick) / 1000000.0f;
			tick = System.nanoTime();
			ticks += tick;
			
			if(ticks > simulateSpeed)
			{
				if(simulate)
				{
					totalTicks += 1;
					simulator.simulate();
					
					if(simulator.entities.isEmpty())
					{
						System.out.println("stopped at: " + totalTicks);
						simulate = false;
					}
				}
				
				repaint();
				ticks = 0;
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
