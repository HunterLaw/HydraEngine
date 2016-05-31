package src.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class VerticalScrollingBG implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1606902216074874863L;
	int movespeed = 2;
	int y = 0;
	int yoffset;
	int xoffset;
	int width;
	int height;
	BufferedImage image;
	BufferedImage bg;
	
	/*
	 * Constructor constructs an image with the information you do or dont have
	 */
	public VerticalScrollingBG(BufferedImage images,int widths,int heights)
	{
		this(images,widths,heights,2,0,0);
	}
	
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,int speed)
	{
		this(images,widths,heights,speed,0,0);
	}
	
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,int speed,int yoffsets)
	{
		this(images,widths,heights,speed,yoffsets,0);
	}
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,int speed,int yoffsets,int xoffsets)
	{
		yoffset = yoffsets;
		xoffset = xoffsets;
		bg = images;
		movespeed = speed;
		width = widths;
		height = heights*2;
		y = -height/2;
		image = new BufferedImage(width,height,bg.getType());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(bg,xoffsets,(height/2)+yoffset,widths-xoffsets,heights,null);
		g.drawImage(bg,xoffsets,0,widths-xoffset,heights,null);
		g.dispose();
	}
	/*
	 * update()
	 * 
	 * Updates the position of the scrolling background image
	 */
	public void update()
	{
		y+=movespeed;
		if(y > yoffset)
		{
			y = (-height/2)-yoffset;
		}
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
	 * getY()
	 * 
	 * returns the y coord that the image should be drawn at
	 */
	public int getY()
	{
		return y;
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
