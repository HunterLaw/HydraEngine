package src;

import java.awt.Color;
import java.awt.Rectangle;

public class HealthBar2D {
	private Rectangle healthbar;
	private Rectangle health;
	private Color healthcolor;
	
	private int x =0;
	private int y =0;
	private int healthsize = 31;
	private double totalhealth;
	private double healthpercent = 1.0;
	private int yoffset;
	
	public HealthBar2D(double totalhealths,int yoffsets)
	{
		healthbar = new Rectangle(32,5);
		health = new Rectangle(31,3);
		healthcolor = Color.GREEN;
		totalhealth = totalhealths;
		yoffset=yoffsets;
	}
	public void update(int xs,int ys,double currenthealth)
	{
		x = xs;
		y = ys+yoffset;
		if(currenthealth > 0.0)
		{
			healthcolor = Color.RED;
		}
		if(currenthealth > totalhealth*0.25)
		{
			healthcolor = Color.YELLOW;
		}
		if(currenthealth > totalhealth*0.60)
		{
			healthcolor = Color.GREEN;
		}
		healthpercent = currenthealth/totalhealth;
		health.setSize((int)(healthsize*healthpercent), 4);

	}
	public boolean isDead()
	{
		if(healthpercent <= 0)
			return true;
		else 
			return false;
	}
	public Rectangle getHealthBar()
	{
		return healthbar;
	}
	public Rectangle getHealth()
	{
		return health;
	}
	public Color getHealthColor()
	{
		return healthcolor;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
