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
import src.objects.Grid;
import src.objects.NonTexturedObject2D;
import src.objects.Wall;
import src.paths.Node;
import src.paths.PathFinder;

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
	static Enemy enemy;
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
	static Level lv;
	static Wall[][] maps;
	static ArrayList<NonTexturedObject2D> objects;
	static PathFinder pf = new PathFinder();
	static Grid grid;
	static boolean gpath = false;
	public static void main(String[] args)
	{
		GUI();
		try{
			
			pf.start();
		}catch(Exception e)
		{
			System.out.println("caught");
		}
	}
	public static void GUI()
	{
		
		objects = new ArrayList<NonTexturedObject2D>();
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
		enemy = new Enemy(0,0,32,32,true);
		enemy.setColor(Color.orange);
		enemy.enable();
		objects.add(enemy);
		map.setCharacter(chars);
		
		grid = new Grid(0,0,1280,960,false);
		grid.setColor(Color.black);
		grid.setGridSize(32);
		grid.enable();
		objects.add(grid);
		
		maps = new Wall[Level.lv.length][Level.lv[0].length];
		pf.setMap(maps);
		pf.setNodeSize(32);
		for(int y =0;y<30;y++)
		{
			for(int x = 0;x<40;x++)
			{
				if(Level.lv[y][x] == 1 || Level.lv[y][x] == 2 || Level.lv[y][x] == 3)
				{
					maps[y][x] = new Wall(x*32,y*32,32,32,true);
					if(Level.lv[y][x] == 2)
					{
						maps[y][x].setColor(Color.green);
						pf.setStartNode(x*32, y*32);
					}
					else if(Level.lv[y][x] == 3)
					{
						maps[y][x].setColor(Color.red);
						pf.setEndNode(x*32, y*32);
					}
					maps[y][x].enable();
					objects.add(maps[y][x]);
				}
			}
		}
	
		objects.add(chars);
		renderer.setObjectArray(objects);
		renderer.setMinimap(mini);
		renderer.setBgObject(map, map.getWidth(), map.getHeight());
//		renderer.setBgObject(bg,bg.getWidth(),bg.getHeight());
		
		
		running = true;
		panel.setRunMethod(new Hydra_Main(),"Game Loop");
		window.pack();
		
		
	}
	
	public void update()
	{
//		map.setCharCenter(chars);
//		map.setCharCenter(chars);
//		System.out.println(map.getCharlorr()+":"+map.getCharuord());
		chars.update(lorr,uord);
		map.update(objects, lorr, uord);
		for(Node i:pf.getOpenedClosed())
		{
			if(!objects.contains(i))
			{
				objects.add(i);
			}
		}
		if(pf.isPathFound() && !gpath)
		{
			System.out.println("here");
			gpath = true;
			enemy.setNodeList(pf.getPath());
		}
		objects.remove(enemy);
		objects.add(enemy);
		enemy.update();
		renderer.setObjectArray(objects);
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
