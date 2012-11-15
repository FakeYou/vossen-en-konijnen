package vossen.entities;

import java.awt.Color;
import java.util.List;

import program.Vossen;
import program.helpers.Json;

import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

/**
 * a hunter entity which kills animals when overpopulating
 * 
 * @author Ward Holthof
 * @version 2012-11-09
 */
public class Hunter extends Entity
{
	private Json config;

	/**
	 * @param field the field in which the hunter is placed
	 * @param location the location of the hunter
	 */
	public Hunter(Field field, Location location)
	{
		super(field, location);

		// get the config from the main config object
		config = Vossen.config.getAsJson("simulator", "entities", "hunter");
		
		setColor(new Color(
			config.get("color").getAsJsonArray().get(0).getAsInt(),
			config.get("color").getAsJsonArray().get(1).getAsInt(),
			config.get("color").getAsJsonArray().get(2).getAsInt()
		));
	}

	/* (non-Javadoc)
	 * @see vossen.Entity#tick(java.util.List)
	 */
	public void tick(List<Entity> newAnimals)
	{
		config = Vossen.config.getAsJson("simulator", "entities", "hunter");
		
		if(isAlive())
		{
            Object object = getField().getObjectAtRandomLocation();
            
            if(object != null && object instanceof Entity && !(object instanceof Hunter))
            {
            	Entity entity = (Entity) object;
            	
            	entity.kill();
            }
            
            Location location = getField().getFreeRandomAdjacentLocation(getLocation());
            
            if(location != null)
            {
            	setLocation(location);
            }
		}
	}

}
