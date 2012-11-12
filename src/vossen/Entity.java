package vossen;

import java.awt.Color;
import java.util.List;

import vossen.helpers.Location;

public abstract class Entity
{
	private boolean alive;
	private Field field;
	private Location location;
	private Color color;
	
	public Entity(Field field, Location location)
	{
		alive = true;
		this.field = field;
		setLocation(location);
	}
	
	abstract public void tick(List<Entity> newAnimals);
	
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
	
	protected Field getField()
	{
	    return field;
	}
	
	protected void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
	}

	public Location getLocation()
	{
		return location;
	}
	
	protected void setLocation(Location location)
	{
	    if(location != null) 
	    {
			field.clear(location.getX(), location.getY());
	    
	        this.location = location;
	    	field.place(this, location);
	    }
	}
	
	public boolean isAlive()
	{
		return alive;
	}

	public void setField(Field field)
	{
		this.field = field;
	}
}
