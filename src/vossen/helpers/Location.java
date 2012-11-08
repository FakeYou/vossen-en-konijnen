package vossen.helpers;

public class Location
{
	private int x;
	private int y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof Location)
		{
			Location other = (Location) object;
			return x == other.getX() && y == other.getY();
		}
		
		return false;
	}

	public String toString()
	{
		return x + ", " + y;
	}
	
	public int hashCode()
	{
		return (x << 16) + y;
	}
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}
