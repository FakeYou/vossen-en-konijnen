package vossen.entities;

import java.awt.Color;
import java.util.List;

import program.Vossen;
import program.helpers.Json;

import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

public class Hunter extends Entity
{
	private Json config;

	public Hunter(Field field, Location location)
	{
		super(field, location);

		config = Vossen.config.getAsJson("simulator", "entities", "hunter");
		
		setColor(new Color(
			config.get("color").getAsJsonArray().get(0).getAsInt(),
			config.get("color").getAsJsonArray().get(1).getAsInt(),
			config.get("color").getAsJsonArray().get(2).getAsInt()
		));
	}

	public void tick(List<Entity> newAnimals)
	{
		config = Vossen.config.getAsJson("simulator", "entities", "hunter");
		
		if(isAlive())
		{
            Object object = getField().getObjectAtRandomLocation();
            
            if(object != null && object instanceof Entity)
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
