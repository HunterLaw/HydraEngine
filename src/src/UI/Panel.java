package src.UI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	Thread thread;
	
	Dimension size;
	
	public Panel(Dimension sizes)
	{
		size = sizes;
		setLayout(null);
		setPreferredSize(size);
	}
	
	public void setRunMethod(Runnable run)
	{
		thread = new Thread(run);
		thread.start();
	}
	
	public void resize(int widths,int heights)
	{
		size = new Dimension(widths,heights);
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
}
