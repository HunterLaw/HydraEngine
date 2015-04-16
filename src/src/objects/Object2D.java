package src.objects;

import java.awt.Dimension;

public class Object2D 
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean enabled = false;
	
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
	
	public void enable()
	{
		enabled = true;
	}
	
	public void disable()
	{
		enabled = false;
	}
	
	public void setX(int xs)
	{
		x = xs;
	}
	
	public void setY(int ys)
	{
		y = ys;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
