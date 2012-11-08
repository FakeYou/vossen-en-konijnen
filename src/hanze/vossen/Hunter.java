package hanze.vossen;

import java.awt.Color;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A model of a Hunter.
 * Hunters hunt and move.
 * 
 * @author Ward Holthof
 * @version 2012.11.08
 */
public class Hunter extends Entity
{
    // Characteristics shared by all hunters (class variables).
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a Hunter.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(Field field, Location location)
    {
    	super(field, location);
        
    	setColor(Color.black);
    }
    
    /**
     * This is what the hunter does most of the time: it hunts for
     * over population.
     * @param field The field currently occupied.
     * @param newHunters A list to return new hunters.
     */
    public void act(List<Entity> newHunters)
    {
    		Location newLocation = findAnimal();
    		if(newLocation == null) { 
    			newLocation = getField().freeAdjacentLocation(getLocation());
    		}
    		
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
    }
  
    /**
     * Look for Animals adjacent to the current location.
     * @return Where animal was found, or null if it wasn't.
     */
    private Location findAnimal()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Entity animal = (Entity) field.getObjectAt(where);
            
            if((animal instanceof Entity) && !(animal instanceof Hunter)) {
	                if(animal.isAlive()) {
	                	
	                	//check population
	                	if(FieldStats.getPopulationOfEntity(animal.getField(), animal.getClass()) > 10) {
	                		animal.setDead();
	                    return where;
	                	}
	                }
	            }
        	}
        return null;
    }
}
