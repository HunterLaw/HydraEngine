package src.objects;

import java.awt.Rectangle;

public class NewRectangle extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NewRectangle(int x, int y, int width, int height)
	{
		super(x,y,width,height);
	}
	
	public boolean rectXInside(Rectangle rect)
	{
		if(x < rect.getX())
		{
//			System.out.println("hit1");
			if(x+width > rect.getX()+rect.getWidth())
			{
//				System.out.println("hit2");
				return true;
			}
		}
		return false;
	}
	
	public boolean rectYInside(Rectangle rect)
	{
		if(y < rect.getY() && y+height > rect.getY()+rect.getHeight())
		{
			return true;
		}
		return false;
	}

}
