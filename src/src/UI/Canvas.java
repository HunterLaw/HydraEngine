package src.UI;

import java.awt.Dimension;
import java.awt.Graphics;

public class Canvas extends java.awt.Canvas
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7584536653075969018L;

	private Thread thread;
	private Dimension size;
	
	public Canvas(Dimension sizes)
	{
		size = sizes;
		setPreferredSize(sizes);

	}
	
	public void createBufferStrategy()
	{
		if(getBufferStrategy() == null)
		{
			this.createBufferStrategy(3);
		}
	}
	
	public void setRunMethod(Runnable run)
	{
		thread = new Thread(run);
		thread.start();
	}
	
	public void setRunMethod(Runnable run, String name)
	{
		setRunMethod(run);
		thread.setName(name);
	}
	
	public void setSize(int width, int height)
	{
		size = new Dimension(width,height);
	}
	
	public void show()
	{
		this.getBufferStrategy().show();
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public int getWidth()
	{
		return size.width;
	}
	
	public int getHeight()
	{
		return size.height;
	}
	
	public Graphics getGraphics()
	{
		if(getBufferStrategy() == null)
		{
			return null;
		}
		return this.getBufferStrategy().getDrawGraphics();
	}
}
