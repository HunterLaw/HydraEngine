package src.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

public class NonTexturedObject2D extends Object2D {

	protected Color color;
	protected Rectangle rect;
	protected boolean filled = false;;
	
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
	
	private void createRect()
	{
		rect = new Rectangle(x,y,width,height);
	}
	
	public void setColor(Color colors)
	{
		color = colors;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public boolean getFilled()
	{
		return filled;
	}
}
