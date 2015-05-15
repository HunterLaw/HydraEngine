package src.objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation 
{
	ArrayList<BufferedImage> images;
	int place = 0;
	int change = 0;
	int maxchange;
	public Animation(int updatestillchange)
	{
		init();
		setUpdates(updatestillchange);
	}
	
	public void init()
	{
		images = new ArrayList<BufferedImage>();
	}
	
	public void addImage(BufferedImage image)
	{	
		images.add(image);
	}
	
	public BufferedImage update()
	{
		change++;
		if(change == maxchange)
		{
			change = 0;
			if(place+1 != images.size())
			{
				place++;
			}
			else
			{
				place = 0;
			}
		}
		return images.get(place);
	}
	
	public void setUpdates(int updatestillchange)
	{
		maxchange = updatestillchange;
	}
}
