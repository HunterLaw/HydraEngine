package test;

import src.movement.Direction;
import src.objects.NonTexturedObject2D;

public class Character extends NonTexturedObject2D{

	int movespeed = 5;
	
	public Character(int xs, int ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
	}
	
	public void update(Direction lorr, Direction uord)
	{
		if(lorr == Direction.left)
		{
			x -= movespeed;
			if(x < 0)
				x = 0;
		}
		else if(lorr == Direction.right)
		{
			x += movespeed;
			if(x+width > 640)
				x = 640-width;
		}
		
		if(uord == Direction.up)
		{
			y -= movespeed;
			if(y < 0)
				y = 0;
		}
		else if(uord == Direction.down )
		{
			y += movespeed;
			if(y+height > 480)
				y= 480-height;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
