package vossen.entities;

import hanze.vossen.Randomizer;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import program.Vossen;
import program.helpers.Json;

import vossen.Animal;
import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

/**
 * a rabbit entity animal, rabbits only multiply and don't eat
 * 
 * @author FakeYou
 * @version 2012-11-07
 */
public class Rabbit extends Entity implements Animal
{
	private int age;
	//private Object foodLevel;
	private Json config;
	private static Random random = Randomizer.getRandom();

	/**
	 * @param field the field in which the rabbit is placed
	 * @param location the location of the rabbit
	 */
	public Rabbit(Field field, Location location)
	{
		super(field, location);

		// get the config from the main config object
		config = Vossen.config.getAsJson("simulator", "entities", "rabbit");
		
		setColor(new Color(
			config.get("color").getAsJsonArray().get(0).getAsInt(),
			config.get("color").getAsJsonArray().get(1).getAsInt(),
			config.get("color").getAsJsonArray().get(2).getAsInt()
		));

		// set all parameters
		age = 0;
	}

	/* (non-Javadoc)
	 * @see vossen.Entity#tick(java.util.List)
	 */
	@Override
	public void tick(List<Entity> newAnimals)
	{
        config = Vossen.config.getAsJson("simulator", "entities", "rabbit");
		
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
	
	/* (non-Javadoc)
	 * @see vossen.Animal#incrementAge()
	 */
	public void incrementAge()
	{
		age += 1;
		
		if(age > config.get("maximumAge").getAsInt())
		{
			kill();
		}
	}
	
	/* (non-Javadoc)
	 * @see vossen.Animal#giveBirth(java.util.List)
	 */
	public void giveBirth(List<Entity> newRabbits)
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
	
	/* (non-Javadoc)
	 * @see vossen.Animal#breed()
	 */
	public int breed()
    {
		double breedingProbability = config.get("breedingProbability").getAsDouble();
		int breedingMax = config.get("breedingMax").getAsInt();
		
        int births = 0;
        if(canBreed() && random.nextDouble() <= breedingProbability) {
            births = random.nextInt(breedingMax) + 1;
        }
        return births;
    }
	
    /* (non-Javadoc)
     * @see vossen.Animal#canBreed()
     */
    public boolean canBreed()
    {
        return age >= config.get("breedingAge").getAsInt();
    }

	/* (non-Javadoc)
	 * @see vossen.Animal#incrementHunger()
	 */
	@Override
	public void incrementHunger()
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see vossen.Animal#findFood()
	 */
	@Override
	public Location findFood()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
