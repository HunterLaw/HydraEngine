package src.UI;

public class FPS{
	
	public final static String version = "0_2";
	private int frames = 30;
	private long targetfps;
	private long begin;
	private long end;
	private long wait;
	
	private long beginfps;
	private long fpstimeelapse;
	private long fpstime = 1000;
	private int fps;
	
	
	/*
	 * Constructor set the maximum frames then sets the amount of time one frame should take
	 */
	public FPS(int numframes)
	{
		frames = numframes;
		targetfps = 1000/frames;
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
	public void endTime()
	{
		end = System.nanoTime();
		wait = targetfps-((end - begin)/1000000);
		if(wait < 0)
		{
			wait = 0;
		}
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
		return ((fpstimeelapse-beginfps)/1000000) >= fpstime;
	}
}
