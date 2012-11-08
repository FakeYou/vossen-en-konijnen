package vossen.entities;

import java.awt.Color;
import java.util.List;

import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

public class Rabbit extends Entity
{
	public Rabbit(Field field, Location location)
	{
		super(field, location);
		setColor(new Color(98, 72, 34));
	}

	@Override
	public void tick(List<Entity> newAnimals)
	{
		
	}
}
