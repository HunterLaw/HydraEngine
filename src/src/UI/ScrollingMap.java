package src.UI;

import java.util.ArrayList;

import src.movement.Direction;
import src.objects.Object2D;
import src.objects.TexturedObject2D;

public class ScrollingMap extends TexturedObject2D{
	//TODO: Finish class
	Direction lorr = Direction.none;
	Direction uord = Direction.none;
	private int winwidth;
	private int winheight;
	private int movespeed = 5;
	private int offset = 5;
	private Direction charlorr = Direction.none;
	private Direction charuord = Direction.none;
	private boolean charmovex = false;
	private boolean charmovey = false;
	ArrayList<Object2D> objects = new ArrayList<Object2D>();
	public ScrollingMap(int xs, int ys, int widths, int heights, int winwidths, int winheights, int movespeeds) {
		super(xs, ys, widths, heights, false);
		winwidth = winwidths;
		winheight = winheights;
		movespeed = movespeeds;
	}
	public ScrollingMap(int xs, int ys, int widths, int heights, int winwidths, int winheights) {
		super(xs, ys, widths, heights, false);
		winwidth = winwidths;
		winheight = winheights;
	}
	
	public void update(ArrayList<Object2D> objectss, Direction lorr, Direction uord)
	{
		objects = objectss;
		if(charmovex)
		{
			charlorr = lorr;
		}
		else
		{
			if(lorr == Direction.right)
			{
					if((x+movespeed)< (width-winwidth))
					{
						charlorr = Direction.none;
						x += movespeed;
					}
					else
					{
						charlorr = Direction.right;
						charmovex = true;
						x = (width-winwidth);
					}
			}
			else if(lorr == Direction.left)
			{
				if((x-movespeed) > 0)
				{
					charlorr = Direction.none;
					x -= movespeed;
				}
				else
				{
					charlorr = Direction.left;
					charmovex = true;
					x = 0;
				}
			}
			else
			{
				charlorr = Direction.none;
			}
		}
		if(charmovey)
		{
			charuord = uord;
		}
		else 
		{
			if(uord == Direction.up)
			{
				if((y-movespeed) > 0)
				{
					charuord = Direction.none;
					y -= movespeed;
				}
				else
				{
					charuord = Direction.up;
					charmovey = true;
					y = 0;
				}
			}
			else if(uord == Direction.down)
			{
				if((y+movespeed) < (height-winheight))
				{
					charuord = Direction.none;
					y += movespeed;
				}
				else
				{
					charuord = Direction.down;
					charmovey = true;
					y = (height-winheight);
				}
			}
			else
			{
				charuord = Direction.none;
			}
		}
	}
	
	public void setAllObjectX(Direction lorr, int movement)
	{
		for(int x= 0;x< objects.size();x++)
		{
			if(lorr == Direction.left)
				objects.get(x).setX(objects.get(x).getX()-movement);
			else if(lorr == Direction.right)
				objects.get(x).setX(objects.get(x).getX()+movement);
		}
	}
	
	public void setAllObjectY(Direction uord, int movement)
	{
		for(int x= 0;x< objects.size();x++)
		{
			if(uord == Direction.up)
				objects.get(x).setY(objects.get(x).getY()-movement);
			else if(uord == Direction.down)
				objects.get(x).setY(objects.get(x).getY()+movement);
		}
	}
	
	public void setCharMoveX(boolean value)
	{
		charmovex = value;
	}
	
	public void setCharMoveY(boolean value)
	{
		charmovey = value;
	}
	
	public void setCharCenter(Object2D chars)
	{
		if(chars != null)
		{
			int midcharx = chars.getX()+(chars.getWidth()/2);
			int midwinx = winwidth/2;
			int midchary = chars.getY()+(chars.getHeight()/2);
			int midwiny = winheight/2;
			if(midcharx > midwinx && midcharx <= midwinx+offset && charlorr == Direction.left)
			{
				chars.setX(midwinx-(chars.getWidth()/2));
				charmovex = false;
				charlorr = Direction.none;
			}
			if(midcharx < midwinx && midcharx >= midwinx-offset&& charlorr == Direction.right)
			{
				chars.setX(midwinx-(chars.getWidth()/2));
				charmovex = false;
				charlorr = Direction.none;
			}
			
			if(midchary < midwiny && midchary >= midwiny-offset && charuord == Direction.down)
			{
				chars.setY(midwiny-(chars.getHeight()/2));
				charmovey = false;
				charuord = Direction.none;
			}
			if(midchary > midwiny && midchary <= midwiny+offset&& charuord == Direction.up)
			{
				chars.setY(midwiny-(chars.getHeight()/2));
				charmovey = false;
				charuord = Direction.none;
			}
		}
	}

	public void setOffset(int off)
	{
		offset = off;
	}
	
	public boolean getCharMoveX()
	{
		return charmovex;
	}
	
	public boolean getCharMoveY()
	{
		return charmovey;
	}
	
	public Direction getCharlorr()
	{
		return charlorr;
	}
	
	public Direction getCharuord()
	{
		return charuord;
	}

	public int getWinX()
	{
		return -getX();
	}
	
	public int getWinY()
	{
		return -getY();
	}
}
