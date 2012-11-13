
package program;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vossen.Simulator;

import javax.swing.border.BevelBorder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import program.helpers.Json;
import program.view.Graph;
import program.view.LineGraph;
import program.view.View;
import javax.swing.JLabel;

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
	public static int simulateSteps;
	private boolean running;

	private JLabel lblSteps;
	
	public Main()
	{
		config.loadFile("/config/config.json");
		JsonObject app = config.get("application").getAsJsonObject();
		
		setEnabled(true);
		
		config.set("Hello world", "application", "title");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(639, 485);
		setTitle(app.get("title").getAsString());
		setVisible(true);
		getContentPane().setLayout(null);
		
		running = true;
		simulate = false;
		simulateSteps = 0;
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);

		ui(this);
		
		loop();		
	}
	
	public void loop()
	{
		long tick = System.nanoTime();
		long ticks = 0;
		long totalTicks = 0;
		int ticksPerSecond = 20;
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

				repaint();
			}
			
			if(simulateSteps <= 0)
			{
				simulate = false;
			}
			
			lblSteps.setText("steps: " + totalTicks);
		}
	}
	
	public void ui(JFrame frame)
	{
		JPanel sidepanel = new JPanel();
		sidepanel.setBounds(10, 11, 148, 420);
		frame.getContentPane().add(sidepanel);
		sidepanel.setLayout(null);
		
		JButton btnStartSimulatie = new JButton("Start");
		btnStartSimulatie.setBounds(10, 5, 133, 23);
		btnStartSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = true;
				Main.simulateSteps = Integer.MAX_VALUE;
			}
		});
		sidepanel.add(btnStartSimulatie);
		
		JButton btnStopSimulatie = new JButton("Pauze");
		btnStopSimulatie.setBounds(10, 33, 133, 23);
		btnStopSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = false;
			}
		});
		sidepanel.add(btnStopSimulatie);
		
		JButton btnStepSimulatie = new JButton("1 stap");
		btnStepSimulatie.setBounds(10, 61, 133, 23);
		btnStepSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = true;
				Main.simulateSteps = 1;
			}
		});
		sidepanel.add(btnStepSimulatie);
		
		JButton btnResetSimulatie = new JButton("Reset");
		btnResetSimulatie.setBounds(10, 140, 133, 23);
		btnResetSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = false;
				Main.simulateSteps = 0;
				
				Main.simulator.reset();
			}
		});
		sidepanel.add(btnResetSimulatie);
		
		lblSteps = new JLabel("Steps: 0");
		lblSteps.setBounds(10, 395, 128, 14);
		sidepanel.add(lblSteps);
		
		JPanel panel = new LineGraph();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(168, 11, 446, 420);
		frame.getContentPane().add(panel);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
