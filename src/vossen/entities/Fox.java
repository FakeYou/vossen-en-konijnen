package vossen.entities;

import hanze.vossen.Randomizer;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import program.Vossen;
import program.helpers.Json;

import vossen.Animal;
import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

/**
 * the fox animal entity
 * 
 * @author FakeYou
 * @version 2012-11-10
 */
public class Fox extends Entity implements Animal
{
	private Json config;
	private static Random random = Randomizer.getRandom();
	
	private int age;
	private int maxAge;
	private int hunger;
	private int hungerEndurance;
	private int breedingAge;
	private double breedingProbability;
	private int breedingMax;

	/**
	 * @param field the field in which the fox is placed
	 * @param location the location of the fox
	 */
	public Fox(Field field, Location location)
	{
		super(field, location);

		// get the config from the main config object
		config = Vossen.config.getAsJson("simulator", "entities", "fox");
		
		setColor(new Color(
			config.get("color").getAsJsonArray().get(0).getAsInt(),
			config.get("color").getAsJsonArray().get(1).getAsInt(),
			config.get("color").getAsJsonArray().get(2).getAsInt()
		));

		// set all parameters
		age = 0;
		maxAge = config.get("maximumAge").getAsInt();
		hunger = 0;
		hungerEndurance = config.get("hungerEndurance").getAsInt();
		breedingAge = config.get("breedingAge").getAsInt();
		breedingProbability = config.get("breedingProbability").getAsDouble();
		breedingMax = config.get("breedingMax").getAsInt();
	}

	/* (non-Javadoc)
	 * @see vossen.Entity#tick(java.util.List)
	 */
	@Override
	public void tick(List<Entity> newAnimals)
	{
		config = Vossen.config.getAsJson("simulator", "entities", "fox");
		
		incrementAge();
		incrementHunger();

        if(isAlive()) 
        {
            giveBirth(newAnimals);      
        	
            Location newLocation = findFood();
            if(newLocation == null)
            { 
                newLocation = getField().getFreeRandomAdjacentLocation(getLocation());
            }
            
            if(newLocation != null) 
            {
                setLocation(newLocation);
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
        age++;
        if(age > maxAge) 
        {
            kill();
        }
    }
	
    /* (non-Javadoc)
     * @see vossen.Animal#incrementHunger()
     */
    public void incrementHunger()
    {
    	hunger++;
        if(hunger > hungerEndurance) 
        {
            kill();
        }
    }
    
    /* (non-Javadoc)
     * @see vossen.Animal#findFood()
     */
    public Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.getAdjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        
        while(it.hasNext()) 
        {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) 
            {
                Rabbit rabbit = (Rabbit) animal;
                
                if(rabbit.isAlive()) { 
                    rabbit.kill();
                    //hunger -= Main.config.get("simulator", "entities", "rabbit", "foodValue").getAsInt();
                    hunger = 0;
                    return where;
                }
            }
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see vossen.Animal#giveBirth(java.util.List)
     */
    public void giveBirth(List<Entity> newFoxes)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        int births = breed();
        
        for(int b = 0; b < births && free.size() > 0; b++) 
        {
            Location loc = free.remove(0);
            Fox young = new Fox(field, loc);
            newFoxes.add(young);
        }
    }
    
    /* (non-Javadoc)
     * @see vossen.Animal#breed()
     */
    public int breed()
    {
        int births = 0;
        
        if(canBreed() && random.nextDouble() <= breedingProbability) 
        {
            births = random.nextInt(breedingMax) + 1;
        }
        
        return births;
    }
    
    /* (non-Javadoc)
     * @see vossen.Animal#canBreed()
     */
    public boolean canBreed()
    {
        return age >= breedingAge;
    }
}
