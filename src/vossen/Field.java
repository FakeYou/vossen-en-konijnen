package vossen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vossen.helpers.Location;

import hanze.vossen.Randomizer;

public class Field
{
	private static final Random random = Randomizer.getRandom();
	
	private int width, height;
	private Object[][] field;
	
	public Field(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		field = new Object[width][height];
	}
	
	public void place(Object object, Location location)
	{
		place(object, location.getX(), location.getY());
	}
	
	public void place(Object object, int x, int y)
	{
		field[x][y] = object;
	}
	
	public Object getObjectAt(Location location)
	{
		return getObjectAt(location.getX(), location.getY());
	}
	
	public Object getObjectAt(int x, int y)
	{		
		return field[x][y];
	}
	
	public Object getObjectAtRandomLocation()
	{
		int x = random.nextInt(getWidth());
		int y = random.nextInt(getHeight());
		
		return field[x][y];
	}
	
	public Location getRandomAdjacentLocation(Location location)
	{
		return GetRandomAdjacentLocation(location.getX(), location.getY());
	}
	
	public Location GetRandomAdjacentLocation(int x, int y)
	{
		List<Location> adjacent = getAdjacentLocations(x, y);
		
		int location = random.nextInt(adjacent.size());
		
		return adjacent.get(location);
	}
	
	public List<Location> getFreeAdjacentLocations(Location location)
	{
		return getFreeAdjacentLocations(location.getX(), location.getY());
	}
	
	public List<Location> getFreeAdjacentLocations(int x, int y)
	{
		List<Location> free = new LinkedList<Location>();
		List<Location> adjacent = getAdjacentLocations(x, y);
		
		for(Location location : adjacent)
		{
			boolean bool = (getObjectAt(location) == null);
			if(bool)
			{
				free.add(location);
			}
		}
		
		return free;
	}

	public Location getFreeRandomAdjacentLocation(Location location)
	{
		return getFreeRandomAdjacentLocation(location.getX(), location.getY());
	}
	
	public Location getFreeRandomAdjacentLocation(int x, int y)
	{
		List<Location> adjacent = getFreeAdjacentLocations(x, y);
		
		if(adjacent.size() == 0)
		{
			return null;
		}
		
		int location = random.nextInt(adjacent.size());
		
		return adjacent.get(location);
	}
	
	public List<Location> getAdjacentLocation(Location location)
	{
		if(location == null)
		{
			return new LinkedList<Location>();
		}
		
		return getAdjacentLocations(location.getX(), location.getY());
	}

	public List<Location> getAdjacentLocations(Location location)
	{
		return getAdjacentLocations(location.getX(), location.getY());
	}
	
	public List<Location> getAdjacentLocations(int x, int y)
	{
		List<Location> locations = new LinkedList<Location>();
		
		if(x > width || y > height)
		{
			return locations;
		}
		
		for(int xOffset = -1; xOffset <= 1; xOffset++)
		{
			int xCheck = x + xOffset;
			
			if(xCheck < 0 || xCheck > width - 1)
			{
				continue;
			}			
			
			for(int yOffset = -1; yOffset <= 1; yOffset++)
			{
				int yCheck = y + yOffset;
				
				if(yCheck < 0 || yCheck > height - 1)
				{
					continue;
				}
				
				locations.add(new Location(xCheck, yCheck));
			}
		}
		
		return locations;
	}
	
	public void clear()
	{
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				field[x][y] = null;
			}
		}
	}
	
	public void clear(Location location)
	{
		clear(location.getX(), location.getY());
	}
	
	public void clear(int x, int y)
	{
		field[x][y] = null;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
