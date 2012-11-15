package program;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import program.view.GridView;
import program.view.LineView;
import program.view.PieView;

public class UI
{
	public JFrame frame;
	private JTabbedPane tabbedPane;

	public UI()
	{
		frame = new JFrame();
		
		frame.setEnabled(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setTitle("Vossen en Konijnen");
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnSimulator = new JMenu("Simulator");
		menuBar.add(mnSimulator);
		
		JMenuItem mntmStart = new JMenuItem("Start");
		mnSimulator.add(mntmStart);
		mntmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = true;
				Vossen.simulateSteps = Integer.MAX_VALUE;				
			}
		});
		
		JMenuItem mntmPause = new JMenuItem("Pause");
		mnSimulator.add(mntmPause);
		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = false;			
			}
		});
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mnSimulator.add(mntmReset);
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vossen.simulate = false;
				Vossen.simulateSteps = 0;
				Vossen.simulator.reset();
				Vossen.resetStats();
			}
		});
		
		JSeparator separator = new JSeparator();
		mnSimulator.add(separator);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnSimulator.add(mntmSettings);
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				new Settings();
			}
		});
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Grid = new GridView();
		tabbedPane.addTab("Veld", null, Grid, null);
		
		JPanel Line = new LineView();
		tabbedPane.addTab("Lijngrafiek", null, Line, null);
		
		JPanel Pie = new PieView();
		tabbedPane.addTab("Taartdiagram", null, Pie, null);
	}
}