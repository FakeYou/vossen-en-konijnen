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
 * An eagle animal entity, a scarse animal who live long and eat both rabbits and foxes
 * 
 * @author Ward Holthof
 * @version 2012-11-14
 */
public class Eagle extends Entity implements Animal
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
	 * @param field the field where the eagle is placed in
	 * @param location the location of the eagle
	 */
	public Eagle(Field field, Location location)
	{
		super(field, location);

		// get the config from the main config object
		config = Vossen.config.getAsJson("simulator", "entities", "eagle");
		
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
	public void tick(List<Entity> newAnimals)
	{
		config = Vossen.config.getAsJson("simulator", "entities", "eagle");
		
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
    	/*if(hunger < 100)
    	{
    		return null;
    	}*/
    	
        Field field = getField();
        List<Location> adjacent = field.getAdjacentLocations(getLocation());
        
        Iterator<Location> it = adjacent.iterator();
        
        while(it.hasNext()) 
        {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            
            if(animal instanceof Fox) 
            {
                Fox fox = (Fox) animal;
                
                if(fox.isAlive()) 
                { 
            		fox.kill();
                    hunger = 0;
                	
                    return where;
                }
            } 
            else if(animal instanceof Rabbit) 
            {
                Rabbit rabbit = (Rabbit) animal;
                
                if(rabbit.isAlive()) 
                { 
                	rabbit.kill();
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
            Eagle young = new Eagle(field, loc);
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
