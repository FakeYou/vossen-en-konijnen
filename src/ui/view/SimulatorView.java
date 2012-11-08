package ui.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import hanze.vossen.Entity;

import org.eclipse.swt.graphics.GC;

import ui.Main;
import ui.UI;

public class SimulatorView
{	
	public static void paint(GC gc)
	{
		/*
		double cellWidth = UI.canvas.getSize().x / Main.FIELD_WIDTH;
		double cellHeight = UI.canvas.getSize().y / Main.FIELD_HEIGHT;
		
		Color background = UI.canvas.getBackground();
		gc.setBackground(background);
		gc.fillRectangle(0, 0, UI.canvas.getSize().x, UI.canvas.getSize().y);
		
		for(int x = 0; x < Main.FIELD_WIDTH; x++)
		{
			for(int y = 0; y < Main.FIELD_HEIGHT; y++)
			{
				Animal animal = (Animal) Main.simulator.field.getObjectAt(x, y);
				
				if(animal != null)
				{
					gc.setBackground(animal.getColor());
					gc.fillRectangle((int) (cellWidth * x), (int) (cellHeight * y), (int) cellWidth, (int) cellHeight);
				}
			}
		}
		*/
	}
	
	public static void g2dpaint(Canvas canvas)
	{
		Graphics2D g2d = (Graphics2D) canvas.getGraphics();
		
		double cellWidth = UI.canvas.getSize().x / Main.FIELD_WIDTH;
		double cellHeight = UI.canvas.getSize().y / Main.FIELD_HEIGHT;
		
		g2d.setColor(Color.white);
		g2d.clearRect(0, 0, UI.canvas.getSize().x, UI.canvas.getSize().y);
		
		for(int x = 0; x < Main.FIELD_WIDTH; x++)
		{
			for(int y = 0; y < Main.FIELD_HEIGHT; y++)
			{
				Entity animal = (Entity) Main.simulator.field.getObjectAt(x, y);
				
				if(animal != null)
				{
					g2d.setColor(animal.getColor());
					g2d.fillRect((int) (cellWidth * x), (int) (cellHeight * y), (int) cellWidth, (int) cellHeight);
					//gc.fillRectangle((int) (cellWidth * x), (int) (cellHeight * y), (int) cellWidth, (int) cellHeight);
				}
			}
		}
	}
}
