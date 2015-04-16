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
	
	
	
	public FPS(int numframes)
	{
		frames = numframes;
		targetfps = 1000/frames;
	}
	
	public void beginTime()
	{
		begin = System.nanoTime();
	}
	public void endTime()
	{
		end = System.nanoTime();
		wait = targetfps-((end - begin)/1000000);
		if(wait < 0)
		{
			wait = 0;
		}
	}
	
	public long getWaitTime()
	{
		return wait;
	}
	
	public void startFPSTime()
	{
		fps = 0;
		beginfps = System.nanoTime();
	}
	
	public void addFPS()
	{
		fps++;
	}
	
	public int getFPS()
	{
		return fps;
	}
	
	public boolean secondDone()
	{
		fpstimeelapse = System.nanoTime();
		return ((fpstimeelapse-beginfps)/1000000) >= fpstime;
	}
}
