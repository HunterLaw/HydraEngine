package src.UI;

import java.util.ArrayList;

import src.movement.Direction;
import src.objects.NonTexturedObject2D;
import src.objects.Object2D;
import src.objects.TexturedObject2D;

public class ScrollingMap extends TexturedObject2D{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7406384204090085166L;
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
	ArrayList<NonTexturedObject2D> objects = new ArrayList<NonTexturedObject2D>();
	Object2D chars;
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
	
	public void setCharacter(Object2D chars)
	{
		this.chars = chars;
	}

	public void update(ArrayList<NonTexturedObject2D> objectss, Direction lorr, Direction uord)
	{
		objects = objectss;

		if(lorr == Direction.right || lorr == Direction.left)
		{
			int charx = chars.getMidX();
//			int chary = (int)(chars.getY()+(chars.getHeight()/2));
//			System.out.println(charx);
			int winx = (int)x+(winwidth/2);
//			System.out.println(winx);
			int difx = charx-winx;
//			System.out.println("DifX:"+difx);
			if(x+difx < 0)
			{
				x = 0;
			}
			else if(x+difx+winwidth > width)
			{
				x = width-winwidth;
			}
			else
			{
				x += difx;
			}
//			System.out.println("Y:"+y);
		}
		if(uord == Direction.up || uord == Direction.down)
		{
//			int difx = (int)(chars.getX()-x);
			int chary = chars.getMidY();
//			System.out.println(chary);
			int winy = (int)y+(winheight/2);
//			System.out.println(winy);
			int dify = chary-winy;
//			System.out.println("Dify:"+dify);
			if(y+dify < 0)
			{
				y = 0;
			}
			else if(y+dify+winheight > height)
			{
				y = height-winheight;
			}
			else
			{
				y += dify;
			}
//			System.out.println("Y:"+y);
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
			int midcharx = (chars.getX())+(chars.getWidth()/2);
			int midwinx = (winwidth/2)-getWinX();
			int midchary = (chars.getY())+(chars.getHeight()/2);
			int midwiny = (winheight/2)-getWinY();
			System.out.println(midcharx+":"+midchary);
			System.out.println(midwinx+":"+midwiny);
			if(midcharx >= midwinx && midcharx <= midwinx-offset && charlorr == Direction.left)
			{
				System.out.println("here");
				chars.setX(midwinx-(chars.getWidth()/2));
				charmovex = false;
				charlorr = Direction.none;
			}
			if(midcharx <= midwinx && midcharx >= (midwinx+offset)&& charlorr == Direction.right)
			{
				System.out.println("here");

				chars.setX(midwinx-(chars.getWidth()/2));
				charmovex = false;
				charlorr = Direction.none;
			}
			
			if(midchary < midwiny && midchary >= midwiny-offset && charuord == Direction.down)
			{
				System.out.println("here");

				chars.setY(midwiny-(chars.getHeight()/2));
				charmovey = false;
				charuord = Direction.none;
			}
			if(midchary > midwiny && midchary <= midwiny+offset&& charuord == Direction.up)
			{
				System.out.println("here");

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
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
