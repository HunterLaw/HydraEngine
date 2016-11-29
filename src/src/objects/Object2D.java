package src.objects;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.Serializable;

import javafx.scene.shape.Ellipse;
import src.movement.Direction;

public abstract class Object2D implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5135083994308040717L;

	public enum Shape { rect,circle};

	protected Shape shape;
	
	protected Rectangle rect;
	protected Ellipse cir;
	
	protected double x,y,cx,cy,diameter;
	protected double movespeed = 2;
	
	protected int width,height;
	
	protected boolean enabled = true;
	
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
		shape = Shape.rect;
	}
	
	public Object2D(double x,double y,double diameter)
	{
		cx = x;
		cy = y;
		this.x = cx-(diameter/2);
		this.y = cy-(diameter/2);
		this.diameter = diameter;
		shape = Shape.rect;
	}
	
	public abstract void update();
	
	protected void createCircle()
	{
		double d = diameter*2;
		cir = new Ellipse(x-diameter,y-diameter,d,d);
		shape = Shape.circle;
	}
	/*
	 * createRect()
	 * 
	 * Creates a rectangle to the dimensions given
	 */
	protected void createRect()
	{
		rect = new Rectangle(getX(),getY(),width,height);
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
	
	/**
	 * getMidX()
	 * 
	 * @return Center X point of this object
	 */
	public int getMidX()
	{
		return (int)(x + (width/2));
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
	
	/**
	 * getMidY()
	 * @return Center Y point of this object
	 */
	public int getMidY()
	{
		return (int)(y + (height/2));
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
	
	public int getDiameter()
	{
		return (int)diameter;
	}
	
	public Shape getShape()
	{
		return shape;
	}
	
	public Ellipse getCircle()
	{
		return cir;
	}
	
	public Rectangle getRect()
	{
		createRect();
		return rect;
	}
	
	public String toString()
	{
		return "X: "+x+"/Y:"+y;
	}
}
