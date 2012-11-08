package vossen.entities;

import java.awt.Color;
import java.util.List;

import vossen.Entity;
import vossen.Field;
import vossen.helpers.Location;

public class Fox extends Entity
{
	public Fox(Field field, Location location)
	{
		super(field, location);
		setColor(new Color(235, 169, 55));
	}

	@Override
	public void tick(List<Entity> newAnimals)
	{
		
	}
}
