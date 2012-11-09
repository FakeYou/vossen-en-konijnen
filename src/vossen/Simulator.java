package vossen;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import vossen.entities.Fox;
import vossen.entities.Rabbit;
import vossen.helpers.Location;
import vossen.helpers.Randomizer;

public class Simulator
{
	private static final int FIELD_WIDTH = 120;
	private static final int FIELD_HEIGHT = 80;
	
	private static final double FOX_CREATION_PROBABLITY = 0.00;
	private static final double RABBIT_CREATION_PROBABLITY = 0.08;
	
	
	public List<Entity> entities;
	public Field field;
	private int step;
	
	public Simulator()
	{
		this(FIELD_WIDTH, FIELD_HEIGHT);
	}
	
	public Simulator(int width, int height)
	{		
		if(width <= 0 || height <= 0)
		{
			width = FIELD_WIDTH;
			height = FIELD_HEIGHT;
		}
		
		entities = new ArrayList<Entity>();
		field = new Field(width, height);
		
		reset();
	}
	
	public void simulate(int steps)
	{
		for(int step = 0; step < steps; step++)
		{
			simulate();
		}
	}
	
	public void simulate()
	{
		step += 1;
		System.out.println(step);
		
		List<Entity> newEntities = new ArrayList<Entity>();
		Iterator<Entity> it = entities.iterator();
		
		while(it.hasNext())
		{
			Entity entity = it.next();
			
			entity.tick(newEntities);
			
			if(!entity.isAlive())
			{
				it.remove();
			}
		}
		
		entities.addAll(newEntities);
	}
	
	public void reset()
	{
		step = 0;
		entities.clear();
		populate();		
	}
	
	public void populate()
	{
		Random random = Randomizer.getRandom();
		
		field.clear();
		
		for(int x = 0; x < field.getWidth(); x++)
		{
			for(int y = 0; y < field.getHeight(); y++)
			{
				if(random.nextDouble() <= FOX_CREATION_PROBABLITY)
				{
					Location location = new Location(x, y);
					Fox fox = new Fox(field, location);
					entities.add(fox);
				}
				else if(random.nextDouble() <= RABBIT_CREATION_PROBABLITY)
				{
					Location location = new Location(x, y);
					Rabbit rabbit = new Rabbit(field, location);
					entities.add(rabbit);
				}
			}
		}
	}
}
