package src.UI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9071679076520409247L;
	/*
	 * Constructor sets the title, default close operation, sets resizable to false, adds the panel(s) specified, sets
	 * the location to relative to nothing, and sets visible to true
	 */
	public Window(JPanel panel,String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setResizable(false);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
	}
	
	public Window(JPanel panel,JPanel panel2,String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setResizable(false);
		add(panel);
		add(panel2);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
	}
	
	public Window(Canvas can, String title)
	{
//		Panel panel = new Panel(can.getSize());
////		panel.setSize(can.getSize());
//		panel.add(can);
//		setLayout(null);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setMinimumSize(can.getSize());
//		add(can);
//		setResizable(false);
		add(can);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		can.createBufferStrategy();
//		System.out.println(isDoubleBuffered());
//		System.out.println(isDisplayable());
//		pack();
//		baseSetup(title);
	}
	
	/*
	 * centerFrame()
	 * 
	 * Centers the frame to the middle of the screen
	 */
	public void centerFrame()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth())/2);
		int y = (int) ((dimension.getHeight()- this.getHeight())/2);
		setLocation(x,y);
	}
}
