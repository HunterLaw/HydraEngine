package src.objects;

import java.awt.Dimension;

public class Object2D 
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean enabled = false;
	
	/*
	 * Constructor sets the basic information about the object
	 */
	public Object2D(int xs,int ys,Dimension sizes)
	{
		x = xs;
		y = ys;
		width = (int)sizes.getHeight();
		height= (int)sizes.getWidth();
	}
	
	public Object2D(int xs,int ys,int widths,int heights)
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
		return x;
	}
	/*
	 * getY()
	 * 
	 * returns the y coord of the object
	 */
	public int getY()
	{
		return y;
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
}
