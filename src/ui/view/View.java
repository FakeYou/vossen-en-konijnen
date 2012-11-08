package ui.view;

import hanze.vossen.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JPanel;

import ui.MainSWING;
import ui.UI;

public class View extends JPanel
{
	private static final long serialVersionUID = 208243931793745149L;
	
	private int pos = 0;
	
	public View()
	{
		super();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(2, 2);
		
		double cellHeight = getHeight() / MainSWING.FIELD_HEIGHT;
		double cellWidth = getWidth() / MainSWING.FIELD_WIDTH;
		
		for(int x = 0; x < MainSWING.FIELD_WIDTH; x++)
		{
			for(int y = 0; y < MainSWING.FIELD_HEIGHT; y++)
			{
				Entity animal = (Entity) MainSWING.simulator.field.getObjectAt(x, y);
				
				if(animal != null)
				{
					g2d.setColor(animal.getColor());
					
					Double point = new Rectangle2D.Double((int) (cellWidth * x), (int) (cellHeight * y), cellWidth, cellHeight);
					g2d.fill(point);
				}
			}
		}
	}
}
