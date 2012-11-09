package vossen.entities;

import hanze.vossen.Randomizer;

import java.awt.Color;
import java.util.List;

import com.google.gson.JsonObject;

import program.Main;
import program.helpers.Json;

import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

public class Rabbit extends Entity
{
	private int age;
	private Object foodLevel;
	private Json config;

	public Rabbit(Field field, Location location)
	{
		super(field, location);
		setColor(new Color(98, 72, 34));
        
		config = Main.config.getAsJson("simulator", "entities", "rabbit");
		
		age = 0;
	}

	@Override
	public void tick(List<Entity> newAnimals)
	{
		incrementAge();
		if(isAlive())
		{
			Location location = getField().getFreeRandomAdjacentLocation(getLocation());
			if(location != null)
			{
				setLocation(location);
			}
		}
	}
	
	public void incrementAge()
	{
		age += 1;
		
		if(age > config.get("maximumAge").getAsInt())
		{
			//kill();
		}
	}
}
