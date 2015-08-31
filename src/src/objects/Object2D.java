package src.objects;

import java.awt.Dimension;

import src.movement.Direction;

public class Object2D 
{
	protected double x;
	protected double y;
	protected double movespeed = 2;
	protected int width;
	protected int height;
	protected boolean enabled = false;
	
	/*
	 * Constructor sets the basic information about the object
	 */
	public Object2D(double xs,double ys,Dimension sizes)
	{
		x = xs;
		y = ys;
		width = (int)sizes.getHeight();
		height= (int)sizes.getWidth();
	}
	
	public Object2D(double xs,double ys,int widths,int heights)
	{
		x = xs;
		y = ys;
		width = widths;
		height= heights;
	}
	/*
	 * enable()
	 * 
	 * Enables the object to be drawn if using Renderer2D
	 */
	public void enable()
	{
		enabled = true;
	}
	/*
	 * disable()
	 * 
	 * Disables the object to be drawn if using Renderer2D
	 */
	public void disable()
	{
		enabled = false;
	}
	/*
	 * setX()
	 * 
	 * Sets the x coord
	 */
	public void setX(int xs)
	{
		x = xs;
	}
	/*
	 * setY()
	 * 
	 * Sets the y coord
	 */
	public void setY(int ys)
	{
		y = ys;
	}
	
	public void setMovespeed(double move)
	{
		movespeed = move;
	}
	
	public int simulateX(Direction lorr)
	{
		if(lorr == Direction.right)
		{
			return (int)(x+movespeed);
		}
		else if(lorr == Direction.left)
		{
			return (int)(x-movespeed);
		}
		return (int)x;
	}
	
	public int simulateY(Direction uord)
	{
		if(uord == Direction.up)
		{
			return (int)(y-movespeed);
		}
		else if(uord == Direction.down)
		{
			return (int)(y+movespeed);
		}
		return (int)x;
	}
	
	/*
	 * isEnabled()
	 * 
	 * returns weather or not the object is enabled
	 */
	public boolean isEnabled()
	{
		return enabled;
	}
	/*
	 * getX()
	 * 
	 * returns the x coord of the object
	 */
	public int getX()
	{
		return (int)x;
	}
	/*
	 * getY()
	 * 
	 * returns the y coord of the object
	 */
	public int getY()
	{
		return (int)y;
	}
	/*
	 * getWidth()
	 * 
	 * returns the objects width
	 */
	public int getWidth()
	{
		return width;
	}
	/*
	 * getHeight()
	 * 
	 * returns the objects height
	 */
	public int getHeight()
	{
		return height;
	}
	/*
	 * getMovespeed()
	 * 
	 * return the objects movespeed
	 */
	public double getMovespeed()
	{
		return movespeed;
	}
}
