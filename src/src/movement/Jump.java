package src.movement;

import java.io.Serializable;

public class Jump implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4451031923577565657L;
	boolean enabled = false;
	boolean completed = true;
	double initjumpspeed;
	double jumpingspeed;
	double decrement;
	
	public Jump(double jumping, double decrem)
	{
		initjumpspeed = jumping;
		decrement = decrem/60;
//		System.out.println(decrement);
	}
	
	public void setJump(double jumping, double decrem)
	{
		initjumpspeed = jumping;
		decrement = decrem/60;
	}
	
	public void setJump(double jumping)
	{
		initjumpspeed = jumping;
	}
	
	public double update(double ys)
	{
//		System.out.println("Before: "+ys);
		if(enabled && !completed)
		{
//			System.out.println(jumpingspeed);
			if(jumpingspeed >= 0)
			{
				jumpingspeed -= decrement;
			}
			else
			{
				completed = true;
//				System.out.println("update dis");
			}
//			System.out.println("After: "+(ys+jumpingspeed));
			return (ys-jumpingspeed);
		}
//		System.out.println("After: "+ys);
		return ys;
	}
	
	public void enable()
	{
		enabled = true;
		completed = false;
		jumpingspeed = initjumpspeed;
//		System.out.println("Enabled");
	}
	
	public void disable()
	{
		enabled = false;
		jumpingspeed = 0;
		completed = true;
//		System.out.println("Disable");
	}
	
	public boolean isCompleted()
	{
		return completed;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
