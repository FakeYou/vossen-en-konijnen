package program;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import program.view.View;


public class UI
{
	public static void mainScreen(JFrame frame)
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
		
		JPanel panel = new View();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(168, 11, 446, 420);
		frame.getContentPane().add(panel);
	}
}
