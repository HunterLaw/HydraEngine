package src.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.objects.NonTexturedObject2D;
import src.objects.Object2D;
import src.objects.TexturedObject2D;

public class Renderer2D {
	BufferedImage image;
	ArrayList<Object2D> objects;
	Object bg;
	int bgwidth;
	int bgheight;
	int width;
	int height;
	int imageType;
	
	public Renderer2D(int widths, int heights,int imageTypes)
	{
		width = widths;
		height = heights;
		imageType = imageTypes;
		objects = new ArrayList<Object2D>();
	}
	
	public void addObject(Object2D object)
	{
		objects.add(object);
	}
	
//	public void setBg(BufferedImage bgs,int widths,int heights)
//	{
//		bg = bgs;
//		bgwidth = widths;
//		bgheight = heights;
//	}
	
	public void setBgObject(Object object)
	{
		bg = object;
	}
	
	public void setBgObject(Object object,int widths,int heights)
	{
		if(object instanceof BufferedImage)
		{
			bg = (BufferedImage) object;
			bgwidth = widths;
			bgheight = heights;
		}
		else if(object instanceof VerticalScrollingBG)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
	}

	public void resize(int widths,int heights)
	{
		width =widths;
		height = heights;
	}
	
	public BufferedImage render()
	{
		int max = objects.size();
		NonTexturedObject2D nonObject;
		TexturedObject2D object;
		image = new BufferedImage(width,height,imageType);
		Graphics2D g = (Graphics2D) image.getGraphics();
			if(bg instanceof VerticalScrollingBG)
			{
				g.drawImage(((VerticalScrollingBG)bg).getImage(), 0, ((VerticalScrollingBG)bg).getY() ,bgwidth,bgheight, null);
			}
			else
			{
				g.drawImage(((BufferedImage)bg),0,0,bgwidth,bgheight,null);
			}
			for(int i = 0;i<max;i++)
			{
				if(objects.get(i).isEnabled())
				{
					if(objects.get(i) instanceof TexturedObject2D)
					{
						object = (TexturedObject2D)objects.get(i);
						g.drawImage(object.getImage(),object.getX(),object.getY(),object.getWidth(),object.getHeight(),null);
						object = null;
					}
					else if(objects.get(i) instanceof NonTexturedObject2D)
					{
						nonObject = (NonTexturedObject2D)objects.get(i);
						g.setColor(nonObject.getColor());
						if(!nonObject.getFilled())
							g.drawRect(nonObject.getX(), nonObject.getY(), nonObject.getWidth(), nonObject.getHeight());
						else
							g.fillRect(nonObject.getX(), nonObject.getY(), nonObject.getWidth(), nonObject.getHeight());
						nonObject = null;
					}
				}
			}
		g.dispose();
		return image;
	}
}
