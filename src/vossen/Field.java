package vossen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vossen.helpers.Location;

import hanze.vossen.Randomizer;

/**
 * the field to simulate in
 * 
 * @author FakeYou
 * @version 2012-11-09
 */
public class Field
{
	private static final Random random = Randomizer.getRandom();
	
	private int width, height;
	private Object[][] field;
	
	/**
	 * @param width width of the field
	 * @param height height of the field
	 */
	public Field(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		field = new Object[width][height];
	}
	
	/**
	 * @param object the object to place
	 * @param location the location to place at
	 */
	public void place(Object object, Location location)
	{
		place(object, location.getX(), location.getY());
	}
	
	/**
	 * @param object the object to place
	 * @param x the x coordinate to place at
	 * @param y the y coordinate to place at
	 */
	public void place(Object object, int x, int y)
	{
		field[x][y] = object;
	}
	
	/**
	 * @param location location to get an object from
	 * @return the object found at the given location
	 */
	public Object getObjectAt(Location location)
	{
		return getObjectAt(location.getX(), location.getY());
	}
	
	/**
	 * @param x the x coordinate to get an object from
	 * @param y the y coordinate to get an object from
	 * @return the object found at the given x, y
	 */
	public Object getObjectAt(int x, int y)
	{		
		return field[x][y];
	}
	
	/**
	 * @return the object found at a random location on the field
	 */
	public Object getObjectAtRandomLocation()
	{
		int x = random.nextInt(getWidth());
		int y = random.nextInt(getHeight());
		
		return field[x][y];
	}
	
	/**
	 * get a random location, occupied or not, adjacent to the given location
	 * 
	 * @param location the location
	 * @return the random location
	 */
	public Location getRandomAdjacentLocation(Location location)
	{
		return GetRandomAdjacentLocation(location.getX(), location.getY());
	}
	
	/**
	 * get a random location, occupied or not, adjacent to the given location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the random location
	 */
	public Location GetRandomAdjacentLocation(int x, int y)
	{
		List<Location> adjacent = getAdjacentLocations(x, y);
		
		int location = random.nextInt(adjacent.size());
		
		return adjacent.get(location);
	}
	
	/**
	 * get adjacent locations which aren't occupied
	 * 
	 * @param location the location
	 * @return a list of location adjacent to the given location which are free
	 */
	public List<Location> getFreeAdjacentLocations(Location location)
	{
		return getFreeAdjacentLocations(location.getX(), location.getY());
	}
	
	/**
	 * get adjacent locations which aren't occupied
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return a list of location adjacent to the given location which are free
	 */
	public List<Location> getFreeAdjacentLocations(int x, int y)
	{
		List<Location> free = new LinkedList<Location>();
		List<Location> adjacent = getAdjacentLocations(x, y);
		
		for(Location location : adjacent)
		{
			if(getObjectAt(location) == null)
			{
				free.add(location);
			}
		}
		
		return free;
	}

	/**
	 * get a random location, which isn't occupied, adjacent to the given location
	 * 
	 * @param location the given location
	 * @return random location
	 */
	public Location getFreeRandomAdjacentLocation(Location location)
	{
		return getFreeRandomAdjacentLocation(location.getX(), location.getY());
	}

	/**
	 * get a random location, which isn't occupied, adjacent to the given location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return random location
	 */
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

	/**
	 * get all locations adjacent to the given location
	 * 
	 * @param location the given location
	 * @return a list of locations adjacent
	 */
	public List<Location> getAdjacentLocations(Location location)
	{
		return getAdjacentLocations(location.getX(), location.getY());
	}

	/**
	 * get all locations adjacent to the given location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return a list of locations adjacent
	 */
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
	
	/**
	 * set all items in field to null
	 */
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
	
	/**
	 * @param location the location to clear
	 */
	public void clear(Location location)
	{
		clear(location.getX(), location.getY());
	}
	
	/**
	 * @param x the x coordinate to clear
	 * @param y the y coordinate to clear
	 */
	public void clear(int x, int y)
	{
		field[x][y] = null;
	}
	
	/**
	 * @return the width of the field
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return the height of the field
	 */
	public int getHeight()
	{
		return height;
	}
}
