package vossen.helpers;

import java.util.Random;

/**
 * a global randomizer
 * 
 * @author FakeYou
 * @version 2012-11-08
 */
public class Randomizer
{
	private static final int SEED = 11111;
	private static final Random random = new Random(SEED);
	private static final boolean SHARED = true;
	
	/**
	 * constructor
	 */
	public Randomizer()
	{
		
	}
	
	/**
	 * @return get the random object
	 */
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
	
	/**
	 * reset the random object
	 */
	public static void reset()
	{
		if(SHARED)
		{
			random.setSeed(SEED);
		}
	}
}
