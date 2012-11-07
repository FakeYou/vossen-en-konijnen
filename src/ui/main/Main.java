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
	
	public static Simulator simulator;
	
	public static final int FIELD_WIDTH = 100;
	public static final int FIELD_HEIGHT = 100;
	
	public Main() 
	{
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);
		
		model = new Model();
		controller = new Controller(model);
		simulatorview = new SimulatorView(model);
		
		screen = new JFrame("Model View Controller/Dynamic Model with thread");
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
	
	public static void repaint()
	{
		simulatorview.repaint();
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}
}
