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
	
	/*
	 * Constructor sets the properties of the BufferedImage and initializes its object array
	 */
	public Renderer2D(int widths, int heights,int imageTypes)
	{
		width = widths;
		height = heights;
		imageType = imageTypes;
		objects = new ArrayList<Object2D>();
	}
	/*
	 * addObject()
	 * 
	 * Add and object to render to the object array
	 */
	public void addObject(Object2D object)
	{
		objects.add(object);
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
		NonTexturedObject2D nonObject;
		TexturedObject2D object;
		image = new BufferedImage(width,height,imageType); //Resets the image
		Graphics2D g = (Graphics2D) image.getGraphics();
			if(bg instanceof VerticalScrollingBG) //Draws a VerticalScrollingBG image
			{
				g.drawImage(((VerticalScrollingBG)bg).getImage(), 0, ((VerticalScrollingBG)bg).getY() ,bgwidth,bgheight, null);
			}
			else //Draws a static background image
			{
				g.drawImage(((BufferedImage)bg),0,0,bgwidth,bgheight,null);
			}
			for(int i = 0;i<max;i++) //Goes through the object array and draws all of the Textured and Non-Textured Object2Ds
			{
				if(objects.get(i).isEnabled())//Checks to see if the object in the array "wants to be drawn" to the screen
				{
					if(objects.get(i) instanceof TexturedObject2D) //If a TexturedObject2D, draw the texture
					{
						object = (TexturedObject2D)objects.get(i);
						g.drawImage(object.getImage(),object.getX(),object.getY(),object.getWidth(),object.getHeight(),null);
						object = null;
					}
					else if(objects.get(i) instanceof NonTexturedObject2D) //If a NonTexturedObject2D, draw the rectangle of the object in the color specified
					{
						nonObject = (NonTexturedObject2D)objects.get(i);
						g.setColor(nonObject.getColor());
						if(!nonObject.getFilled()) //Checks to see if the object should be drawn as a filled or outlined rectangle
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
