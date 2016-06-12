package src.paths;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.objects.Object2D;
import src.objects.Wall;

public class PathFinder extends Thread implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3779212877164365901L;
	private ArrayList<Node> open,closed,openclosed,path;
	private Node start,end;
	private int nsize=0;
	private Object2D[][] map;
	private boolean foundpath = false;
	public PathFinder()
	{
		this.setName("PathFinder Thread");
		openclosed = new ArrayList<Node>();
	}
	
	public void setNodeSize(int nsizes)
	{
		nsize = nsizes;
	}
	
	public void setStartNode(int x,int y)
	{
		if(nsize != 0)
		{
			start = new Node(getTileCoord(x), getTileCoord(y), true, nsize);
			start.setColor(Color.green);
			start.enable();
		}
		else
		{
			System.out.println("Couldn't init start node");
		}
	}
	
	public void setEndNode(int x,int y)
	{
		if(nsize != 0)
		{
			end = new Node(getTileCoord(x),getTileCoord(y),true,nsize);
			end.setColor(Color.red);
			end.enable();
		}
		else
		{
			System.out.println("Couldn't init end node");
		}
	}
	
	public void setMap(Object2D[][] ma)
	{
		map = ma;
	}
	
	public int getTileCoord(int x)
	{
		if(nsize != 0)
		{
			return (int)(x/nsize);		
		}
		else
		{
			System.out.println("Couldn't calculate tile coord");
		}
		return -1;
	}
	
	public Node getCurrentNode()
	{
		ArrayList<Node> lowest = new ArrayList<Node>();
		ArrayList<Node> temp = new ArrayList<Node>();
		int low = Integer.MAX_VALUE;
//		System.out.println("Open: "+open);
		for(Node i: open)
		{
			if(i.getF() < low)
			{
				low = i.getF();
			}
		}
		for(Node i:open)
		{
			if(i.getF() == low)
			{
				lowest.add(i);
			}
		}
//		System.out.println("Lowest: "+lowest);
		if(lowest.size() > 1)
		{
			int h = Integer.MAX_VALUE;
			for(Node i:lowest)
			{
				if(i.getH() < h)
				{
					h = i.getH();
				}
			}
			temp = (ArrayList<Node>) lowest.clone();
			for(Node i:temp)
			{
				if(i.getH() != h)
				{
					lowest.remove(i);
				}
			}
		}
//		System.out.println(lowest.get(0));
//		try {
//			System.in.read();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return lowest.get(0);
	}
	
	public ArrayList<Node> reverse(ArrayList<Node> s)
	{
		ArrayList<Node> temp = new ArrayList<Node>();
		for(int i = s.size()-1; i >=0;i--)
		{
			temp.add(s.get(i));
		}
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run()
	{
		long startt = System.currentTimeMillis();
		foundpath = false;
		ArrayList<Node> temp = new ArrayList<Node>();
		ArrayList<Node> temp2 = new ArrayList<Node>();
		path = new ArrayList<Node>();
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		Node current = null;
		if(start != null && end != null && map != null && nsize != 0)
		{
			while(!foundpath)
			{
				temp.clear();
				temp2.clear();
				if(current == null)
				{
					current = start;
//					System.out.println("Current:"+current+"/"+current.getHostNode());

				}
				else
				{
					current = getCurrentNode();
//					System.out.println("Current:"+current+"/"+current.getHostNode());
					open.remove(current);
					closed.add(current);
					current.setColor(Color.blue);
					if(current.equals(end))
					{
//						System.out.println("pathfound");
//						System.out.println(start);
						while(!foundpath)
						{
							
//							System.out.println("Current:"+p+"/"+p.getHostNode());
							if(current.equals(start))
							{
								foundpath = true;
							}
							path.add(current);
							current = current.getHostNode();
						}
						path = reverse(path);
						System.out.println(path);
						break;
					}
				}
//				System.out.println(map.length);
//				System.out.println(map[0].length);
				if(current.ty != 0)
					temp.add(new Node(current.tx,current.ty-1,true,nsize));
				if(current.tx != map[0].length)
					temp.add(new Node(current.tx+1,current.ty,true,nsize));
				if(current.ty != map.length)
					temp.add(new Node(current.tx,current.ty+1,true,nsize));
				if(current.tx != 0)
					temp.add(new Node(current.tx-1,current.ty,true,nsize));
				temp2 = (ArrayList<Node>) temp.clone();
//				System.out.println("Temp: "+temp);
				for(Node i:temp)
				{
					if(i.equals(end))
					{
						end.enable();
						end.setHostNode(current);
//						i.setColor(Color.cyan);
						open.add(end);
						break;		
					}
				}
				if(open != null)
				{
					openclosed = new ArrayList<Node>();
					for(Node n:open)openclosed.add(n);
					for(Node n:closed)openclosed.add(n);
					if(openclosed.size() != 0)
					{
						for(Node n:openclosed)
						{
							for(Node i:temp2)
							{
//								System.out.println("X:"+i.x+"Y:"+i.y);
								if(n.equals(i) || (map[i.ty][i.tx] instanceof Wall))
								{
									temp.remove(i);
								}
							}
						}
					}
					else
					{
						for(Node i:temp2)
						{
//							System.out.println("X:"+i.tx+"Y:"+i.ty);
//							System.out.println(map[i.tx][i.ty]);
							if(map[i.ty][i.tx] instanceof Wall)
							{
								System.out.println("here");
								temp.remove(i);
							}
						}
					}
				}
				for(Node s:temp)
				{
					s.enable();
					s.setStart(start);
					s.setEnd(end);
					s.getF();
					s.setHostNode(current);
					s.setColor(Color.cyan);
					open.add(s);
				}
			}
		}
		else
		{
			System.out.println("Start Node, End Node, Node size, or Map is not initialized");
			System.out.println("Start:"+start);
			System.out.println("End:"+end);
			System.out.println("Nsise:"+nsize);
			System.out.println("Map:"+map);

		}
		long time = System.currentTimeMillis()-startt;
		double sec = time / 1000000000;
		System.out.println("Time: "+time);
	}
	
	public boolean isPathFound()
	{
		return foundpath;
	}
	
	public ArrayList<Node> getPath()
	{
		return path;
	}
	
	public ArrayList<Node> getOpenedClosed()
	{
		return openclosed;
	}
}
