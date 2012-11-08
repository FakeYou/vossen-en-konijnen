package hanze.vossen;

import java.awt.Color;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A model of a Eagle.
 * Eagles age, move, eat rabbits AND GoldenEaglees, and die (AND RULE!!!).
 * 
 * @author Ward Holthof
 * @version 2012.11.08
 */
public class GoldenEagle extends Animal
{
    // Characteristics shared by all eagles (class variables).
    
    // The age at which a eagle can start to breed.
    private static final int BREEDING_AGE = 301;
    // The age to which a eagle can live.
    private static final int MAX_AGE = 675;
    // The likelihood of a eagle breeding.
    private static final double BREEDING_PROBABILITY = 0.005;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    // The food value of a single rabbit or fox. In effect, this is the
    // number of steps (an eagle flies but who cares...)
    //			a eagle can go before it has to eat again.
    private static final int FOOD_VALUE = 300;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The eagles age.
    private int age;
    // The eagles food level, which is increased by eating rabbits and foxes.
    private int foodLevel;

    /**
     * Create a eagle. A eagle can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the eagle will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public GoldenEagle(boolean randomAge, Field field, Location location)
    {
    	super(field, location);
        
    	setColor(Color.green);
    	
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = FOOD_VALUE;
        }
    }
    
    /**
     * This is what the eagle does most of the time: it hunts for
     * rabbits and foxes. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newGoldenEagles A list to return newly born eagles.
     */
    public void act(List<Animal> newGoldenEagles)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newGoldenEagles);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age. This could result in the eagles death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this eagle more hungry. This could result in the eagles death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits and foxes adjacent to the current location.
     * Only the first live rabbit or fox is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
    	if(foodLevel > 100)
    	{
    		return null;
    	}
    	
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if(fox.isAlive()) { 
            		fox.setDead();
            		foodLevel = FOOD_VALUE;
                	return where;
                }
            } else
            	if(animal instanceof Rabbit) {
                    Rabbit rabbit = (Rabbit) animal;
                    if(rabbit.isAlive()) { 
                    	rabbit.setDead();
                    	foodLevel = FOOD_VALUE;
                        return where;
                    }
            	}
        }
        return null;
    }
    
    /**
     * Check whether or not this eagle is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newGoldenEagles A list to return newly born foxes.
     */
    private void giveBirth(List<Animal> newGoldenEagles)
    {
        // New eagles are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            GoldenEagle young = new GoldenEagle(false, field, loc);
            newGoldenEagles.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A eagle can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}