package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import src.UI.FPS;
import src.UI.Minimap;
import src.UI.Panel;
import src.UI.Renderer2D;
import src.UI.ScrollingMap;
import src.UI.Window;
import src.media.Sound;
import src.movement.Direction;
import src.objects.Object2D;

public class Hydra_Main implements Runnable, ComponentListener, KeyListener
{
	static Window window;
	static Panel panel;
	static Renderer2D renderer = new Renderer2D(640*2,480*2,BufferedImage.TYPE_INT_RGB);
	BufferedImage image;
	static boolean running = false;
	boolean fpsd = false;
	int updated = 0;
	FPS fps = new FPS();
	static Character chars;
	static Direction lorr = Direction.none;
	static Direction uord = Direction.none;
	File loc = new File("src/media/song.wav");
	Sound sound = new Sound(loc);
	boolean playmusic = false;
	static File bgs = new File("src/media/TestScrolling.png");
	static BufferedImage bg;
	static Minimap mini;
	static ScrollingMap map;
	static final int minimapsize = 96;
	
	static ArrayList<Object2D> objects;
	public static void main(String[] args)
	{
		GUI();
	}
	public static void GUI()
	{
		objects = new ArrayList<Object2D>();
		map = new ScrollingMap(0,0,640*2,480*2,640,480);
		map.loadBasicImage(bgs);
		map.enable();
		panel = new Panel(new Dimension(640,480));
		window = new Window(panel,"Test");
		window.addKeyListener(new Hydra_Main());
		//window.addComponentListener(new Hydra_Main());
		mini = new Minimap((640-minimapsize),(480-minimapsize),minimapsize,minimapsize);
		mini.setBorder(1, 1, Color.green);
		mini.enable();
		chars = new Character((640/2)-10,(480/2)-10,20,20,true);
		chars.setColor(Color.red);
		chars.enable();
		
		
		objects.add(chars);
		renderer.setObjectArray(objects);
		renderer.setMinimap(mini);
		renderer.setBgObject(map, map.getWidth(), map.getHeight());
//		renderer.setBgObject(bg,bg.getWidth(),bg.getHeight());
		
		running = true;
		panel.setRunMethod(new Hydra_Main());
	}
	
	public void update()
	{
//		map.setCharCenter(chars);
		map.update(objects, lorr, uord);
		map.setCharCenter(chars);
//		System.out.println(map.getCharlorr()+":"+map.getCharuord());
		chars.update(map.getCharlorr(), map.getCharuord());
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
//			System.out.println(map.getX());
			g.drawImage(image, map.getWinX(),map.getWinY(), null);
			g.setColor(Color.cyan);
			g.drawLine(320, 0, 320, 480);
			g.drawLine(0,240,640,240);
			g.dispose();
		}
	}
	
	public void run()
	{
		if(playmusic)
			sound.playSound();
		while(running)
		{
			if(fps.secondDone())
			{
//				System.out.println("FPS: "+fps.getFPS());
				fpsd = false;
//				System.out.println(updated);
//				System.out.println("Updates: "+updated + " -- FPS: "+fps.getFPS());
				updated = 0;
			}
			if(!fpsd)
			{
				fps.startFPSTime();
				fpsd = true;
			}
			if(fps.update() && updated <60)
			{
				update();
				updated++;
			}
			render();
			draw();
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
		@Override
		public void keyPressed(KeyEvent arg0) {
			int key = arg0.getKeyCode();
			switch(key)
			{
			case KeyEvent.VK_LEFT:
				lorr = Direction.left;
				break;
			case KeyEvent.VK_RIGHT:
				lorr = Direction.right;
				break;
			case KeyEvent.VK_UP:
				uord = Direction.up;
				break;
			case KeyEvent.VK_DOWN:
				uord = Direction.down;
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			int key = arg0.getKeyCode();
			switch(key)
			{
			case KeyEvent.VK_LEFT:
				if(lorr != Direction.right)
				{
					lorr = Direction.none;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(lorr != Direction.left)
				{
					lorr = Direction.none;
				}
				break;
			case KeyEvent.VK_UP:
				if(uord != Direction.down)
				{
					uord = Direction.none;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(uord != Direction.up)
				{
					uord = Direction.none;
				}
				break;
			}
		}
		@Override
		public void keyTyped(KeyEvent arg0) {		
		}
}
