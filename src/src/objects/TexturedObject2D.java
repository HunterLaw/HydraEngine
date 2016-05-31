package src.objects;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class TexturedObject2D extends NonTexturedObject2D {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8634265408847983708L;
	protected BufferedImage texture;
	protected File file;
	
	/*
	 * Constructors call NonTexturedObject2D's constructor
	 */
	public TexturedObject2D(double xs, double ys, Dimension sizes,boolean filled) {
		super(xs, ys, sizes,filled);
	}

	public TexturedObject2D(double xs, double ys, int widths, int heights,boolean filled) {
		super(xs, ys, widths,heights,filled);
	}
	
	public TexturedObject2D(double x,double y,double radius)
	{
		super(x,y,radius,false);
	}
	
	/*
	 * setImage()
	 * 
	 * Sets the image from a BufferedImage
	 */
	public void setImage(BufferedImage image)
	{
		texture = image;
	}
	/*
	 * loadBasicImage()
	 * 
	 * Just loads the image from the file into a BufferedImage
	 */
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
	/*
	 * loadTiledImage()
	 * 
	 * Loads the image and then grabs the subimage from the specified area given
	 */
	public void loadTiledImage(int x,int y,int width,int height,File file)
	{
		loadBasicImage(file);
		if(texture !=null)
		{
			texture = texture.getSubimage(x, y, width, height);
		}
	}
	/*
	 * getImage()
	 * 
	 * returns the BufferedImage
	 */
	public BufferedImage getImage()
	{
		return texture;
	}
	
}
