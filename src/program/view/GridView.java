package program.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import program.Vossen;

import vossen.Entity;

public class GridView extends JPanel
{
	private static final long serialVersionUID = 208243931793745149L;
	
	private int pos = 0;
	
	public GridView()
	{
		super();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if(Vossen.simulator == null)
		{
			return;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(2, 2);
		
		double cellHeight = getHeight() / Vossen.FIELD_HEIGHT;
		double cellWidth = getWidth() / Vossen.FIELD_WIDTH;
		
		List<Entity> entities = new ArrayList<Entity>();
		entities.addAll(Vossen.simulator.entities);
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext())
		{
			Entity entity = (Entity) it.next();
			
			if(entity == null || entity.getLocation() == null || !entity.isAlive())
			{
				continue;
			}
			
			g2d.setColor(entity.getColor());
			
			int x = entity.getLocation().getX();
			int y = entity.getLocation().getY();
			
			Double point = new Rectangle2D.Double((int) (cellWidth * x), (int) (cellHeight * y), cellWidth, cellHeight);
			g2d.fill(point);
		}
		
		/*for(int x = 0; x < Main.simulator.field.getWidth(); x++)
		{
			for(int y = 0; y < Main.simulator.field.getHeight(); y++)
			{
				Entity entity = (Entity) Main.simulator.field.getObjectAt(x, y);
				
				if(entity != null)
				{
					g2d.setColor(entity.getColor());
					
					Double point = new Rectangle2D.Double((int) (cellWidth * x), (int) (cellHeight * y), cellWidth, cellHeight);
					g2d.fill(point);
				}
			}
		}*/
	}
}
