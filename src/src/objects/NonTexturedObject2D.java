package src.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

public abstract class NonTexturedObject2D extends Object2D {
	
	protected Color color = Color.BLACK;
	protected Rectangle rect;
	protected boolean filled = false;
	
	/*
	 * Both constructors do the same thing but one has a individual height and width and the other has a dimension
	 */
	public NonTexturedObject2D(double xs, double ys, Dimension sizes,boolean filleds) {
		super(xs, ys, sizes);
		filled = filleds;
		createRect();
		shape = Shape.rect;
	}
	
	public NonTexturedObject2D(double xs, double ys, int width, int height,boolean filleds) {
		super(xs, ys, width,height);
		filled = filleds;
		createRect();
		shape = Shape.rect;
	}
	
	public NonTexturedObject2D(double x,double y,double radius,boolean filled)
	{
		super(x,y,radius);
		this.filled = filled;
		createCircle();
		shape = Shape.circle;
	}
	/*
	 * setColor()
	 * 
	 * Sets the color of the rectangle
	 */
	public void setColor(Color colors)
	{
		color = colors;
	}
	/*
	 * getColor()
	 * 
	 * Returns the color of the rectangle
	 */
	public Color getColor()
	{
		return color;
	}
	/*
	 * getFilled()
	 * 
	 * Returns weather or not this object wants to be filled or outlined
	 */
	public boolean getFilled()
	{
		return filled;
	}
}
