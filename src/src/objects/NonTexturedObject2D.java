package src.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

public class NonTexturedObject2D extends Object2D {

	protected Color color;
	protected Rectangle rect;
	protected boolean filled = false;;
	
	/*
	 * Both constructors do the same thing but one has a individual height and width and the other has a dimension
	 */
	public NonTexturedObject2D(int xs, int ys, Dimension sizes,boolean filleds) {
		super(xs, ys, sizes);
		filled = filleds;
		createRect();
	}
	
	public NonTexturedObject2D(int xs, int ys, int width, int height,boolean filleds) {
		super(xs, ys, width,height);
		filled = filleds;
		createRect();
	}
	
	/*
	 * createRect()
	 * 
	 * Creates a rectangle to the dimensions given
	 */
	private void createRect()
	{
		rect = new Rectangle(x,y,width,height);
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
