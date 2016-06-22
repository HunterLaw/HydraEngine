package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.movement.Gravity;
import src.objects.Animation;
import src.objects.TexturedObject2D;

public class Ball extends TexturedObject2D
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390629810650355105L;
	Gravity gravity = new Gravity(4,30);
	BufferedImage image1;
	BufferedImage image2;
	Animation anim;
	public Ball(int xs, int ys, int widths, int heights,boolean filled)
	{
		super(xs, ys, widths, heights,filled);
		init();
	}
	
	public void init()
	{
		anim = new Animation(120);
		try{
		image1 = ImageIO.read(new File("src/media/Saw.png"));
		image2 = ImageIO.read(new File("src/media/Spike.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		anim.addImage(image1);
		anim.addImage(image2);
	}
	
	@Override
	public void update()
	{
//		gravity.enable();
//		y = gravity.update(y);
//		if(y+height > 480)
//		{
//			y = 0;
//		}
		texture = anim.update();
	}
	
}