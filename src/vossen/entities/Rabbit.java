package vossen.entities;

import hanze.vossen.Randomizer;

import java.awt.Color;
import java.util.List;
import java.util.Random;

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
	private static Random random = Randomizer.getRandom();

	public Rabbit(Field field, Location location)
	{
		super(field, location);
        
		config = Main.config.getAsJson("simulator", "entities", "rabbit");
		
		setColor(new Color(
			config.get("color").getAsJsonArray().get(0).getAsInt(),
			config.get("color").getAsJsonArray().get(1).getAsInt(),
			config.get("color").getAsJsonArray().get(2).getAsInt()
		));
		
		age = 0;
	}

	@Override
	public void tick(List<Entity> newAnimals)
	{
		incrementAge();
		if(isAlive())
		{
			giveBirth(newAnimals);
			
			Location location = getField().getFreeRandomAdjacentLocation(getLocation());
			if(location != null)
			{
				setLocation(location);
			}
			else
			{
				kill();
			}
		}
	}
	
	private void incrementAge()
	{
		age += 1;
		
		if(age > config.get("maximumAge").getAsInt())
		{
			kill();
		}
	}
	
	private void giveBirth(List<Entity> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        int births = breed();
        
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(field, loc);
            newRabbits.add(young);
        }
    }
	
	private int breed()
    {
		double breedingProbability = config.get("breedingProbability").getAsDouble();
		int breedingMax = config.get("breedingMax").getAsInt();
		
        int births = 0;
        if(canBreed() && random.nextDouble() <= breedingProbability) {
            births = random.nextInt(breedingMax) + 1;
        }
        return births;
    }
	
    private boolean canBreed()
    {
        return age >= config.get("breedingAge").getAsInt();
    }
}
