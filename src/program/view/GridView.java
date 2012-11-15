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

/**
 * draw a grid based graphical representation of the field
 * 
 * @author FakeYou
 * @version 2012-11-09
 */
public class GridView extends JPanel
{
	private static final long serialVersionUID = 208243931793745149L;
	
	private int pos = 0;
	
	/**
	 * Constructor
	 */
	public GridView()
	{
		super();
	}
	
	/**
	 * draws the field with all entities
	 * 
	 * @param Graphics g graphics object for awt
	 */
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if(Vossen.simulator == null)
		{
			return;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(2, 2);
		
		// calculate the cell size based on panel size and field size
		double cellHeight = getHeight() / 80;
		double cellWidth = getWidth() / 80;
		
		/*
		 * loop through all the entities and draw them at their position
		 */
		List<Entity> entities = new ArrayList<Entity>();
		entities.addAll(Vossen.simulator.entities);
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext())
		{
			Entity entity = (Entity) it.next();
			
			// skip if there isn't a valid entity
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
	}
}
