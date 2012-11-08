package ui.main;

import hanze.vossen.Simulator;

import javax.swing.*;

import ui.controller.*;
import ui.model.*;
import ui.view.*;

public class Main {
	private Model model;
	private JFrame screen;
	private Controller controller;
	private static AbstractView simulatorview;
	
	public Main() 
	{
		//simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);
		
		model = new Model();
		controller = new Controller(model);
		//simulatorview = new SimulatorView(model);
		
		screen = new JFrame("Vossen en konijnen");
		screen.setSize(508, 450);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(simulatorview);
		
		controller.setBounds(10, 10, 80, 250);
		simulatorview.setBounds(90, 10, 400, 400);
		
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
}
