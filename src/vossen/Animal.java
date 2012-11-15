package vossen;

import java.util.List;

import vossen.helpers.Location;

public interface Animal
{
	/**
	 * increment the age by 1
	 */
	abstract void incrementAge();
	/**
	 * increment the hunger by 1
	 */
	public void incrementHunger();
	/**
	 * find a adjacent location where another entity to be eaten can be found
	 * 
	 * @return a location where food is found
	 */
	public Location findFood();
    /**
     * create new entities based on breeding probability
     * 
     * @param newEntities the object where newEntities are placed
     */
    public void giveBirth(List<Entity> newEntities);
    /**
     * @return the amount children to give birth to, can be null
     */
    public int breed();
    /**
     * @return if the entity can breed
     */
    public boolean canBreed();
}
