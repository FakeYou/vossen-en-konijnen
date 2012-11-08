package vossen.helpers;

import java.util.Random;

public class Randomizer
{
	private static final int SEED = 11111;
	private static final Random random = new Random(SEED);
	private static final boolean SHARED = true;
	
	public Randomizer()
	{
		
	}
	
	public static Random getRandom()
	{
		if(SHARED)
		{
			return random;
		}
		else
		{
			return new Random();
		}
	}
	
	public static void reset()
	{
		if(SHARED)
		{
			random.setSeed(SEED);
		}
	}
}
