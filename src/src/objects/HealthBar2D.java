package src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HealthBar2D extends TexturedObject2D
{
	private Rectangle healthbar;
	private Rectangle health;
	private Color healthcolor;
	
	private int x =0;
	private int y =0;
	private int healthsize = 30;
	private double totalhealth;
	private double healthpercent = 1.0;
	private int yoffset;
	
	private Color high = Color.green;
	private Color med = Color.yellow;
	private Color low = Color.red;
	/*
	 * Constructor creates the rectangle for the bar and then the actual current health
	 */
	public HealthBar2D(double totalhealths,int yoffsets)
	{
		super(0,0,32,5,false);
		healthbar = new Rectangle(32,5);
		health = new Rectangle(30,3);
		healthcolor = high;
		totalhealth = totalhealths;
		yoffset=yoffsets;
	}
	/*
	 * update()
	 * 
	 * Updates the position of the HealthBar and the current health then sets the color of the health bar based on how much
	 * health is left
	 */
	public void update(int xs,int ys,double currenthealth)
	{
		x = xs;
		y = ys+yoffset;
		if(currenthealth > 0.0)
		{
			healthcolor = low;
		}
		if(currenthealth > totalhealth*0.25)
		{
			healthcolor = med;
		}
		if(currenthealth > totalhealth*0.60)
		{
			healthcolor = high;
		}
		healthpercent = currenthealth/totalhealth;
		health.setSize((int)(healthsize*healthpercent), 3);
		texture = new BufferedImage(healthbar.width,healthbar.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)texture.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, healthbar.width, healthbar.height);
		g.setColor(healthcolor);
		g.fillRect(1, 1, health.width, health.height);
		g.dispose();
	}
	/*
	 * setHealthColors
	 * 
	 * Sets the color of high, med, or low health
	 * If sent a null that status color stays the same
	 */
	public void setHealthColors(Color highs,Color meds,Color lows)
	{
		if(highs != null)
			high = highs;
		if(meds != null)
			med = meds;
		if(lows != null)
			low = lows;
	}
	/*
	 * isDead()
	 * 
	 * returns weather or not the healthpercent is below zero
	 */
	public boolean isDead()
	{
		if(healthpercent <= 0)
			return true;
		else 
			return false;
	}
	/*
	 * getHealthBar()
	 * 
	 * returns the healthbar rectangle
	 */
	public Rectangle getHealthBar()
	{
		return healthbar;
	}
	/*
	 * getHealth()
	 * 
	 * returns the health
	 */
	public Rectangle getHealth()
	{
		return health;
	}
	/*
	 * getHealthColor()
	 * 
	 * returns the current health color
	 */
	public Color getHealthColor()
	{
		return healthcolor;
	}
	/*
	 * getX()
	 * 
	 * returns the x coord of the HealthBar2D
	 */
	public int getX()
	{
		return x;
	}
	/*
	 * getY()
	 * 
	 * returns the y coord of the HealthBar2D
	 */
	public int getY()
	{
		return y;
	}
}
