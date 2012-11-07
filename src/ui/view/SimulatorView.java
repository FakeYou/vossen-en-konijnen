package ui.view;

import hanze.vossen.Animal;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import ui.main.Main;
import ui.model.Model;

public class SimulatorView extends AbstractView
{
	private static final long serialVersionUID = -4350077605378691086L;

	private int cell_width;
	private int cell_height;
	
	public SimulatorView(Model model)
	{
		super(model);
		setSize(400, 400);
		
		cell_width = 400 / Main.FIELD_WIDTH;
		cell_height = 400 / Main.FIELD_HEIGHT;
	}
	
	public void paintComponent(Graphics g)
	{
		System.out.println("paint");
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 400, 400);
		
		for(int x = 0; x < Main.FIELD_WIDTH; x++) 
		{
			for(int y = 0; y < Main.FIELD_HEIGHT; y++)
			{
				Animal animal = (Animal) Main.simulator.field.getObjectAt(x, y);
				
				if(animal != null) 
				{
					g.setColor(animal.color);					
					g.fillRect(x * cell_width, y * cell_height, cell_width, cell_height);
				}
			}
		}
	}
	
	public void repaint(Graphics g)
	{
		System.out.println("repaint");
	}
}
