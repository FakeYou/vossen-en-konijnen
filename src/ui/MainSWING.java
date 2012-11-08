package ui;

import hanze.vossen.Simulator;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;

public class MainSWING extends JFrame
{	
	public static final String TITLE = "Vossen en konijnen 0.1";
	
	public static final int FIELD_WIDTH = 80;
	public static final int FIELD_HEIGHT = 60;
	
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	public static Simulator simulator;
	private boolean simulatorRunning;
	private boolean running;
	
	public MainSWING()
	{
		setEnabled(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setTitle(TITLE);
		getContentPane().setLayout(null);
		
		JPanel sidepanel = new JPanel();
		sidepanel.setBounds(10, 11, 148, 420);
		getContentPane().add(sidepanel);
		sidepanel.setLayout(null);
		
		JButton btnStartSimulatie = new JButton("Start Simulatie");
		btnStartSimulatie.setBounds(10, 5, 133, 23);
		btnStartSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				simulatorRunning = true;
			}
		});
		sidepanel.add(btnStartSimulatie);
		
		JButton btnStopSimulatie = new JButton("Stop simulatie");
		btnStopSimulatie.setBounds(10, 33, 133, 23);
		btnStopSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				simulatorRunning = false;
			}
		});
		sidepanel.add(btnStopSimulatie);
		
		JPanel panel = new View();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(168, 11, 446, 420);
		getContentPane().add(panel);
		setVisible(true);
		
		running = true;
		simulatorRunning = false;
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);
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
				if(simulatorRunning)
				{
					totalTicks += 1;
					simulator.simulateOneStep();
					
					if(simulator.animals.isEmpty())
					{
						System.out.println("stopped at: " + totalTicks);
						simulatorRunning = false;
					}
				}
				
				repaint();
				ticks = 0;
			}
		}
	}
	
	public static void main(String[] args)
	{
		new MainSWING();
	}
}
