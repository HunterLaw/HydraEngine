package src.UI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7044317561147189833L;

	Thread thread;
	
	Dimension size;
	/*
	 * Constructor sets the size of the panel and the layout to null
	 */
	public Panel(Dimension sizes)
	{
		size = sizes;
		setLayout(null);
		setPreferredSize(size);
	}
	/*
	 * setRunMethod()
	 * 
	 * Sets the method it should run as the game loop
	 */
	public void setRunMethod(Runnable run)
	{
		thread = new Thread(run);
		thread.start();
	}
	
	public void setRunMethod(Runnable run,String name)
	{
		setRunMethod(run);
		thread.setName(name);
	}
	/*
	 * resize()
	 * 
	 * Sets the panel to be a new size
	 * WARNING: This is not accounted for in the engine yet
	 */
	@Override
	public void resize(int widths,int heights)
	{
		size = new Dimension(widths,heights);
	}
	/*
	 * getSize()
	 * 
	 * returns the size of the Panel
	 */
	@Override
	public Dimension getSize()
	{
		return size;
	}
	/*
	 * getWidth()
	 * 
	 * returns the width of the Panel
	 */
	@Override
	public int getWidth()
	{
		return size.width;
	}
	/*
	 * getHeight()
	 * 
	 * returns the height of the Panel
	 */
	@Override
	public int getHeight()
	{
		return size.height;
	}
}
