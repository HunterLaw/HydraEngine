package src.UI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.objects.TexturedObject2D;

public class Minimap extends TexturedObject2D{
	
	BufferedImage temp;
	private Color bordercolor;
	private int borderwidth, borderheight;
	
	public Minimap(int xs, int ys, int widths, int heights) {
		super(xs, ys, widths, heights, false);
	}

	public Minimap(BufferedImage maps, int xs, int ys, int widths, int heights) {
		super(xs, ys, widths, heights, false);
		
	}
	
	public void setBorder(int width, int height, Color color)
	{
		borderwidth = width;
		borderheight = height;
		bordercolor = color;
	}
	
	public void update(BufferedImage map)
	{
		temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) temp.getGraphics();
		g.setColor(bordercolor);
		g.fillRect(0,0,width,height);
		g.drawImage(map, borderwidth, borderheight, (width-(borderwidth*2)), (height-(borderheight*2)), null);
		g.dispose();
		texture = temp;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
