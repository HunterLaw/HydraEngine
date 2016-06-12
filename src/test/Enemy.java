package test;

import java.io.Serializable;
import java.util.ArrayList;

import src.movement.Direction;
import src.objects.NonTexturedObject2D;
import src.paths.Node;

public class Enemy extends NonTexturedObject2D implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8289670786368800460L;
	private ArrayList<Node>nodes;
	private int nodei; 
	public Enemy(double xs, double ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
	}

	public void setNodeList(ArrayList<Node> ns)
	{
		nodes = ns;
		System.out.println();
	}
	
	
	@Override
	public void update()
	{
		if(nodes != null)
		{
			if(nodei < nodes.size())
			{
				if(closeEnoughToNode())
				{
					nodei++;
				}
				else
				{
					moveTowardsNode();
				}
			}
		}
//		if(lr == Direction.left)
//		{
//			x -= movespeed;
//		}
//		else if(lr == Direction.right)
//		{
//			x += movespeed;
//		}
//		
//		if(ud == Direction.up)
//		{
//			y -= movespeed;
//		}
//		else if(ud == Direction.down)
//		{
//			y += movespeed;
//		}
	}
	
	public void moveTowardsNode()
	{
		int nx = nodes.get(nodei).getX();
		int ny = nodes.get(nodei).getY();
		if(nx < x)
		{
			x-=movespeed;
		}
		else if(nx > x)
		{
			x+= movespeed;
		}
		if(ny < y)
		{
			y-=movespeed;
		}
		else if(ny > y)
		{
			y+=movespeed;
		}
	}
	
	public boolean closeEnoughToNode()
	{
		if(Math.abs(nodes.get(nodei).getX()-x) <= movespeed && Math.abs(nodes.get(nodei).getY()-y)<= movespeed)
		{
			return true;
		}
		return false;
	}
	

}
