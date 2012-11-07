package ui.controller;

import ui.main.Main;
import ui.model.*;
import javax.swing.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener 
{
	private static final long serialVersionUID = -7413164724294460746L;
	private JButton mineen;
	private JButton pluseen;
	private JButton start;
	private JButton stop;
	
	public Controller(Model model) {
		super(model);
		
		setSize(80, 250);
		start=new JButton("Start");
		start.addActionListener(this);
		
		stop=new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(start);
		add(stop);
		start.setBounds(0, 0, 70, 30);
		stop.setBounds(0, 40, 70, 30);

		setVisible(true);	
	}

	public void actionPerformed(ActionEvent e) 
	{		
		if (e.getSource() == start) 
		{
			Main.simulator.simulate(1);
		}
		
		if (e.getSource() == stop) 
		{
			model.stop();
		}
	}
}