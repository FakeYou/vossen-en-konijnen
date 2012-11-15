package vossen.helpers;

/**
 * a location object, containing a x coordinate and an y coordinate
 * 
 * @author FakeYou
 * @version 2012-11-08
 */
public class Location
{
	private int x;
	private int y;
	
	/**
	 * @param x the x coordinate to set the location to
	 * @param y the y coordinate to set the location to
	 */
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object)
	{
		if(object instanceof Location)
		{
			Location other = (Location) object;
			return x == other.getX() && y == other.getY();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return x + ", " + y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return (x << 16) + y;
	}
	
	/**
	 * @return the x coordinate of the location
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @param x the x coordinate to set the location to
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * @return the y coordinate of the location
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * @param y the y coordinate to set the location to
	 */
	public void setY(int y)
	{
		this.y = y;
	}
}
