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
		
		JButton btnStartSimulatie = new JButton("Start Simulatie");
		btnStartSimulatie.setBounds(10, 5, 133, 23);
		btnStartSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = true;
			}
		});
		sidepanel.add(btnStartSimulatie);
		
		JButton btnStopSimulatie = new JButton("Stop simulatie");
		btnStopSimulatie.setBounds(10, 33, 133, 23);
		btnStopSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main.simulate = false;
			}
		});
		sidepanel.add(btnStopSimulatie);
		
		JPanel panel = new View();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(168, 11, 446, 420);
		frame.getContentPane().add(panel);
	}
}
