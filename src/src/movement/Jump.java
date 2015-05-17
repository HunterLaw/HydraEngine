package src.movement;

public class Jump 
{
	boolean enabled = false;
	double initjumpspeed;
	double jumpingspeed;
	double decrement;
	
	public Jump(double jumping, double decrem)
	{
		initjumpspeed = jumping;
		decrement = jumping/(decrem/60);
//		System.out.println(decrement);
	}
	
	public void setJump(double jumping, double decrem)
	{
		initjumpspeed = jumping;
		decrement = jumping/(decrem/60);
	}
	
	public void setJump(double jumping)
	{
		initjumpspeed = jumping;
	}
	
	public double update(int ys)
	{
//		System.out.println("Before: "+ys);
		if(enabled)
		{
//			System.out.println(jumpingspeed);
			if(jumpingspeed >= 0)
			{
				jumpingspeed -= decrement;
			}
			else
			{
				disable();
//				System.out.println("update dis");
			}
//			System.out.println("After: "+(ys+jumpingspeed));
			return (int) (ys-jumpingspeed);
		}
//		System.out.println("After: "+ys);
		return ys;
	}
	
	public void enable()
	{
		enabled = true;
		jumpingspeed = initjumpspeed;
//		System.out.println("Enabled");
	}
	
	public void disable()
	{
		enabled = false;
		jumpingspeed = 0;
//		System.out.println("Disable");
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
