package src.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Grid extends TexturedObject2D{

	int gsize;
	public Grid(double xs, double ys, int widths, int heights, boolean filled) {
		super(xs, ys, widths, heights, filled);
	}

	public void setGridSize(int size)
	{
		gsize = size;
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		for(int x =0;x< width;x+=gsize)
		{
			g.drawLine(x, 0, x, height);
		}
		for(int y =0;y< height;y+=gsize)
		{
			g.drawLine(0, y, width, y);
		}
		g.dispose();
	}

	@Override
	public void update() {
		
	}
}
