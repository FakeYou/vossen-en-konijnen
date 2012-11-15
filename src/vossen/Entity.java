package vossen;

import java.awt.Color;
import java.util.List;

import vossen.helpers.Location;

/**
 * the abstract entity class extended by the actors in the simulation
 * 
 * @author FakeYou
 * @version 2012-11-10
 */
public abstract class Entity
{
	private boolean alive;
	private Field field;
	private Location location;
	private Color color;
	
	/**
	 * @param field the field in which the entity is placed
	 * @param location the location of the entity
	 */
	public Entity(Field field, Location location)
	{
		alive = true;
		this.field = field;
		setLocation(location);
	}
	
	/**
	 * @param newAnimals the list of newAnimals
	 */
	abstract public void tick(List<Entity> newAnimals);
	
	/**
	 * kill the entity, set the alive boolean to false and clear its location
	 */
	public void kill()
	{
		alive = false;
		
		if(location != null)
		{
			field.clear(location.getX(), location.getY());
			location = null;
			field = null;
		}
	}
	
	/**
	 * @return the field in which the entity is placed
	 */
	protected Field getField()
	{
	    return field;
	}
	
	/**
	 * @param color the color to set the entity to
	 */
	protected void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * @return the color the entity is set to
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * @return the location of the entity
	 */
	public Location getLocation()
	{
		return location;
	}
	
	/**
	 * @param location the location of the entity to set to
	 */
	protected void setLocation(Location location)
	{
	    if(location != null) 
	    {
			field.clear(location.getX(), location.getY());
	    
	        this.location = location;
	    	field.place(this, location);
	    }
	}
	
	/**
	 * @return wether the entity is alive or not
	 */
	public boolean isAlive()
	{
		return alive;
	}

	/**
	 * @param field set the field of the entity
	 */
	public void setField(Field field)
	{
		this.field = field;
	}
}
