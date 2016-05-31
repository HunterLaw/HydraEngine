package src.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import src.objects.NonTexturedObject2D;
import src.objects.TexturedObject2D;

public class Renderer2D implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1680824798029018860L;
	BufferedImage image;
	ArrayList<NonTexturedObject2D> objects;
	Minimap mini;
	Object bg;
	int bgwidth;
	int bgheight;
	int width;
	int height;
	int imageType;
	
	/*
	 * Constructor sets the properties of the BufferedImage and initializes its object array
	 */
	public Renderer2D(int widths, int heights,int imageTypes)
	{
		width = widths;
		height = heights;
		imageType = imageTypes;
		objects = new ArrayList<NonTexturedObject2D>();
	}
	/*
	 * setBgObject()
	 * 
	 * Use this if you want a static background image and you dont want to scale the image
	 */
	public void setBgObject(Object object)
	{
		bg = object;
	}
	
	public void setMinimap(Minimap map)
	{
		mini = map;
	}
	
	public void setObjectArray(ArrayList<NonTexturedObject2D> objectss)
	{
		objects = objectss;
	}
	/*
	 * setBgObject()
	 * 
	 * Use this if you want to scale a static background image or you want to use a VerticalScrollingBG
	 */
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
		else if(object instanceof HorizontalScrollingBG)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
		else if(object instanceof ScrollingMap)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
	}
	/*
	 * resize()
	 * 
	 * Sets the new width and height of the BufferedImage
	 * 
	 * WARNING: Will be used to scale games but has not been tested
	 */
	public void resize(int widths,int heights)
	{
		width =widths;
		height = heights;
	}
	/*
	 * render()
	 * 
	 * Renders the BufferedImage and then returns that image
	 */
	public BufferedImage render()
	{
		int max = objects.size(); //Sets the max size of the object array
		image = new BufferedImage(width,height,imageType); //Resets the image
		Graphics2D g = (Graphics2D) image.getGraphics();
		if(bg instanceof VerticalScrollingBG) //Draws a VerticalScrollingBG image
		{
			g.drawImage(((VerticalScrollingBG)bg).getImage(), 0, ((VerticalScrollingBG)bg).getY() ,bgwidth,bgheight, null);
		}
		else if(bg instanceof HorizontalScrollingBG)
		{
			g.drawImage(((HorizontalScrollingBG)bg).getImage(), ((HorizontalScrollingBG)bg).getX() , 0 ,bgwidth,bgheight, null);
		}
		else if(bg instanceof ScrollingMap)
		{
			g.drawImage(((ScrollingMap)bg).getImage(), 0, 0, null);
		}
		else //Draws a static background image
		{
			g.drawImage(((BufferedImage)bg),0,0,bgwidth,bgheight,null);
		}
		for(int i = 0;i<max;i++) //Goes through the object array and draws all of the Textured and Non-Textured Object2Ds
		{
			if(objects.get(i).isEnabled())//Checks to see if the object in the array "wants to be drawn" to the screen
			{
				drawObject(g,objects.get(i));
			}
		}
//		if(mini != null)
//		{
//			mini.update(image);
//			g.drawImage(mini.getImage(), mini.getX(), mini.getY(), mini.getWidth(), mini.getHeight(), null);
//		}
		g.dispose();
		if(mini != null)
		{
			Graphics2D gg = (Graphics2D) image.getGraphics();
			mini.update(image);
			gg.drawImage(mini.getImage(), ((ScrollingMap)bg).getX()+mini.getX(), ((ScrollingMap)bg).getY()+mini.getY(), null);
			gg.dispose();
		}
		return image;
	}
	
	public void drawObject(Graphics2D g,NonTexturedObject2D object)
	{
		BufferedImage image = null;
		boolean fill = object.getFilled();
		if(object instanceof TexturedObject2D)
		{
			image = ((TexturedObject2D) object).getImage();
			g.drawImage(image,object.getX(),object.getY(),object.getWidth(),object.getHeight(),null);
		}
		else
		{
			g.setColor(object.getColor());
			switch(object.getShape())
			{
			case rect:
				if(!fill) //Checks to see if the object should be drawn as a filled or outlined rectangle
					g.drawRect(object.getX(), object.getY(),object.getWidth(), object.getHeight());
				else
					g.fillRect(object.getX(),object.getY(), object.getWidth(), object.getHeight());
			break;
			
			case circle:
				if(!fill) //Checks to see if the object should be drawn as a filled or outlined circle
					g.drawOval(object.getX(), object.getY(),object.getDiameter(), object.getDiameter());
				else
					g.fillOval(object.getX(), object.getY(),object.getDiameter(), object.getDiameter());
			break;
			
			default:
				System.out.println("DEFAULT STATEMENT IN RENDERER");
			}
		}

	}
	
	public ArrayList<NonTexturedObject2D> getObjects()
	{
		return objects;
	}
}
