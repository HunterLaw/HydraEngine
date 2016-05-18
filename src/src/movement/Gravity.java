package src.movement;

public class Gravity 
{
	boolean enabled = false;
	double maxfallingspeed;
	double fallingspeed;
	double increment;
	public Gravity(double falling, double increm)
	{
		maxfallingspeed = falling;
		increment = increm/60;
	}
	
	public void setGravity(double falling, double increm)
	{
		maxfallingspeed = falling;
		increment = increm/60;
	}
	
	public void setGravity(double falling)
	{
		maxfallingspeed = falling;
	}
	
	public double update(double ys)
	{
//		System.out.println(ys);
		if(enabled)
		{
			if(fallingspeed < maxfallingspeed)
			{
				fallingspeed += increment;
			}
			else
			{
				fallingspeed = maxfallingspeed;
			}
//			System.out.println(ys+fallingspeed);
			return ys += fallingspeed;
		}
		return ys;
	}
	
	public void enable()
	{
		enabled = true;
	}
	
	public void disable()
	{
		enabled = false;
		fallingspeed = 0;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
