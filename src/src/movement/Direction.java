package src.movement;

public enum Direction {
	/*
	 * Just some directions
	 * 
	 */
	left,right,up,down,none;
	public Direction getOpposite()
	{
		if(this == Direction.left)
		{
			return Direction.right;
		}
		else if(this == Direction.right)
		{
			return Direction.left;
		}
		else if(this == Direction.up)
		{
			return Direction.down;
		}
		else if(this == Direction.down)
		{
			return Direction.up;
		}
		else 
		{
			return Direction.none;
		}
	}
}
