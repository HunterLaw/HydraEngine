package src.UI;

public class FPS{
	
	public final static String version = "0_2";
	private int updates = 60;
	private final long million = 1000000;
	private final long billion = 1000000000;
	private long begin;
	private long wait;
	private long elapsed;
	
	private double updatetime = billion/updates;
	private long lastTime = System.nanoTime();
	private long now;
	private double delta=0;
	
	private long beginfps;
	private long fpstimeelapse;
	private long fpstime = 1000;
	private int fps;
	
	
	/*
	 * Constructor set the maximum updates/sec
	 */
	public FPS(int updatess)
	{
		updates = updatess;
		updatetime = billion/updates;
	}
	
	public FPS()
	{
		
	}
	/*
	 * beginTime()
	 * 
	 * Sets the begining time of the frame
	 */
	public void beginTime()
	{
		begin = System.nanoTime();
	}
	/*
	 * endTime()
	 * 
	 * Sets the ending time of the frame then calculates how long it should wait before starting the next frame
	 */
	public boolean endTime()
	{
		elapsed =System.nanoTime() - begin;
		if(elapsed > updatetime)
		{
			return true;
		}
		return false;
	}
	/*
	 * getWaitTime()
	 * 
	 * returns the time the program should wait before starting the next frame
	 */
	public long getWaitTime()
	{
		return wait;
	}
	/*
	 * startFPSTime()
	 * 
	 * Sets the amount of frames that have occurred to zero then sets the time in which is started looking for fps
	 */
	public void startFPSTime()
	{
		fps = 0;
		beginfps = System.nanoTime();
	}
	/*
	 * addFPS()
	 * 
	 * Adds one to fps
	 */
	public void addFPS()
	{
		fps++;
	}
	/*
	 * getFPS()
	 * 
	 * returns how many frames have occurred
	 */
	public int getFPS()
	{
		return fps;
	}
	/*
	 * secondDone()
	 * 
	 * returns weather or not a second has occurred between startFPSTime() and now
	 */
	public boolean secondDone()
	{
		fpstimeelapse = System.nanoTime();
		return ((fpstimeelapse-beginfps)/million) >= fpstime;
	}
	
	public boolean update()
	{
		now = System.nanoTime();
		delta += (now - lastTime)/updatetime;
		lastTime = now;
		if(delta >= 1)
		{
			delta--;
			return true;
		}
		return false;
	}
}
