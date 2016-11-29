package src.UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Canvas extends java.awt.Canvas
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7584536653075969018L;

	private Thread thread;
	private Dimension size;
	private BufferStrategy bs;
	private int x,y;
	private BufferedImage img;
	
	public Canvas(Dimension sizes)
	{
		size = sizes;
		setPreferredSize(size);
		setSize(size);
		setLocation(0,0);
		System.out.println("init");
//		createBufferStrategy();
//		System.exit(0);
//		this.
	}
	
	public void createBufferStrategy()
	{
//		this.addNotify();
		System.out.println("Canvas "+this.isDisplayable());
		if(bs == null)
		{
			createBufferStrategy(3);
		}
		else
		{
			return;
		}
		bs = getBufferStrategy();
//		System.out.println(bs);
		
//		System.out.println(bs);
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
	
	public void showBuffer()
	{
		if(bs != null)
		{
			bs.show();
		}
		else
		{
			System.out.println("Buffer null");
		}
	}
	
	public void draw(int xs,int ys,BufferedImage image)
	{
		x = xs;
		y = ys;
		img = image;
	}
	@Override
	public void paint(Graphics g)
	{
//		g.drawImage(img, x, y, null);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return size;
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
	
	public Graphics getBufferGraphics()
	{
		if(bs == null)
		{
			return null;
		}
		return bs.getDrawGraphics();
	}

}
