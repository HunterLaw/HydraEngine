package src.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class VerticalScrollingBG
{
	int movespeed = 2;
	int y = 0;
	int yoffset;
	int xoffset;
	int width;
	int height;
	BufferedImage image;
	BufferedImage bg;
	
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
	
	public void update()
	{
		y+=movespeed;
		if(y > yoffset)
		{
			y = (-height/2)-yoffset;
		}
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int ys)
	{
		y = ys;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
