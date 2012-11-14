package vossen;

import java.util.List;

import vossen.helpers.Location;

public interface Animal
{
	abstract void incrementAge();
	public void incrementHunger();
	public Location findFood();
    public void giveBirth(List<Entity> newFoxes);
    public int breed();
    public boolean canBreed();
}
