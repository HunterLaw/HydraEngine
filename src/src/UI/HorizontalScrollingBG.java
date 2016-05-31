package src.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class HorizontalScrollingBG implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2755389867732735169L;
	int movespeed = 2;
	int x = 0;
	int xoffset ,yoffset;
	int width, height;
	BufferedImage image,bg;
	
	/*
	 * Constructor constructs an image with the information you do or dont have
	 */
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights)
	{
		this(images,widths,heights,2,0,0);
	}
	
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,int speed)
	{
		this(images,widths,heights,speed,0,0);
	}
	
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,int speed,int yoffsets)
	{
		this(images,widths,heights,speed,yoffsets,0);
	}
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,int speed,int yoffsets,int xoffsets)
	{
		yoffset = yoffsets;
		xoffset = xoffsets;
		bg = images;
		movespeed = speed;
		width = widths*2;
		height = heights;
		x = 0;
		image = new BufferedImage(width,height,bg.getType());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(bg,(width/2)+xoffset,yoffset,widths,heights,null);
		g.drawImage(bg,0,yoffset,widths,heights,null);
		g.dispose();
	}
	
	/*
	 * update()
	 * 
	 * Updates the position of the scrolling background image
	 */
	public void update()
	{
		x-=movespeed;
		if(x < (-width/2-xoffset))
		{
			x = 0;
		}
	}

	/*
	 * setX()
	 * 
	 * Sets the X coord
	 */
	public void setX(int xs)
	{
		x = xs;
	}
	/*
	 * getX()
	 * 
	 * returns the x coord that the image should be drawn at
	 */
	public int getX()
	{
		return x;
	}
	/*
	 * getWidth()
	 * 
	 * returns the width of the VerticalScrollingBG
	 */
	public int getWidth()
	{
		return width;
	}
	/*
	 * getHeight()
	 * 
	 * returns the height of the VerticalScrollingBG
	 */
	public int getHeight()
	{
		return height;
	}
	/*
	 * getImage()
	 * 
	 * returns the image of the VerticalScrollingBG
	 */
	public BufferedImage getImage()
	{
		return image;
	}
}
