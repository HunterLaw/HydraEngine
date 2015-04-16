package src.objects;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TexturedObject2D extends NonTexturedObject2D {

	protected BufferedImage texture;
	protected File file;
	
	public TexturedObject2D(int xs, int ys, Dimension sizes,boolean filled) {
		super(xs, ys, sizes,filled);
	}

	public TexturedObject2D(int xs, int ys, int widths, int heights,boolean filled) {
		super(xs, ys, widths,heights,filled);
	}
	
	public void loadBasicImage(File files)
	{
		file = files;
		try
		{
			texture = ImageIO.read(files);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadTiledImage(int x,int y,int width,int height,File file)
	{
		loadBasicImage(file);
		if(texture !=null)
		{
			texture = texture.getSubimage(x, y, width, height);
		}
	}
	
	public BufferedImage getImage()
	{
		return texture;
	}
	
}
