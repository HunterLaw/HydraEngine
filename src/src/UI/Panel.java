package src.UI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
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
	/*
	 * resize()
	 * 
	 * Sets the panel to be a new size
	 * WARNING: This is not accounted for in the engine yet
	 */
	public void resize(int widths,int heights)
	{
		size = new Dimension(widths,heights);
	}
	/*
	 * getSize()
	 * 
	 * returns the size of the Panel
	 */
	public Dimension getSize()
	{
		return size;
	}
	/*
	 * getWidth()
	 * 
	 * returns the width of the Panel
	 */
	public int getWidth()
	{
		return size.width;
	}
	/*
	 * getHeight()
	 * 
	 * returns the height of the Panel
	 */
	public int getHeight()
	{
		return size.height;
	}
}
