package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JApplet;

import src.Direction;
import src.UI.FPS;
import src.UI.Panel;
import src.UI.Renderer2D;
import src.UI.Window;
import src.media.Sound;

public class Hydra_Main implements Runnable, ComponentListener
{
	static Window window;
	static Panel panel;
	static Renderer2D renderer = new Renderer2D(640,480,BufferedImage.TYPE_INT_RGB);
	BufferedImage image;
	static boolean running = false;
	boolean fpsd = false;
	FPS fps = new FPS(60);
	static Ball ball;
	Direction lorr = Direction.right;
	Direction uord = Direction.down;
	File loc = new File("src/media/song.wav");
	Sound sound = new Sound(loc);
	boolean playmusic = false;
	public static void main(String[] args)
	{
		GUI();
	}
	public static void GUI()
	{
		panel = new Panel(new Dimension(640,480));
		window = new Window(panel,"Test");
		//window.addComponentListener(new Hydra_Main());
		ball = new Ball(20,20,20,20,true);
		ball.setColor(Color.red);
		renderer.addObject(ball);
		running = true;
		panel.setRunMethod(new Hydra_Main());
	}
	
	public void update()
	{
		if(ball.getX()-20>panel.getWidth())
			ball.setX(panel.getWidth()-21);
		else if(ball.getX()+20 <0)
			ball.setX(1);
		if(ball.getY()-20>panel.getHeight())
			ball.setY(panel.getHeight()-21);
		else if(ball.getY()+20 <0)
			ball.setY(1);
		if(lorr == Direction.right)
		{
			ball.setX(ball.getX()+5);
		}
		else if(lorr == Direction.left)
		{
			ball.setX(ball.getX()-5);
		}
		
		if(uord == Direction.up)
		{
			ball.setY(ball.getY()-5);
		}
		else if(uord == Direction.down)
		{
			ball.setY(ball.getY()+5);
		}
		
		if(ball.getX()+ball.getWidth() > panel.getWidth()|| ball.getX() <0)
		{
			if(lorr == Direction.right)
				lorr = Direction.left;
			else if(lorr == Direction.left)
				lorr = Direction.right;
		}
		
		if(ball.getY()+ball.getHeight() > panel.getHeight() || ball.getY() <0)
		{
			if(uord == Direction.up)
				uord = Direction.down;
			else if(uord == Direction.down)
				uord = Direction.up;
		}
		
	}
	
	public void render()
	{
		image = renderer.render();
	}
	
	public void draw()
	{
		Graphics2D g = (Graphics2D)panel.getGraphics();
		if(g !=null)
		{
			g.drawImage(image, 0, 0, null);
			g.dispose();
		}
	}
	
	public void run()
	{
		if(playmusic)
			sound.playSound();
		while(running)
		{
			if(!fpsd)
			{
				fps.startFPSTime();
				fpsd = true;
			}
			if(fps.secondDone())
			{
				System.out.println("FPS: "+fps.getFPS());
				fpsd = false;
			}
			fps.beginTime();
			update();
			render();
			draw();
			fps.endTime();
			try{
				Thread.sleep(fps.getWaitTime());
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			fps.addFPS();

		}
	}
		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			panel.resize(window.getWidth()-22, window.getHeight()-56);
			renderer.resize(window.getWidth()-22, window.getHeight()-56);
		}
}
