package ui.view;

import hanze.vossen.Animal;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import ui.Main;
import ui.UI;

public class SimulatorView
{	
	public static void paint(GC gc)
	{
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
	}
}
