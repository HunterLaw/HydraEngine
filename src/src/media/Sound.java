package src.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7282905491376559168L;
	File file;
	Media clip;
	MediaPlayer player;
	static File loc;
	static BufferedImage image;
	static boolean muted = false;
	static boolean paused = false;
	static boolean init = false;
	double vol =0;
	/*
	 * File must be in the .wav format to work correctly
	 */
	public Sound(File files)
	{
		new JFXPanel();
		if(files != null)
		{
			init = true;
			file = files;
			try {
				image = ImageIO.read(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip =new Media(file.toURI().toString());
			player = new MediaPlayer(clip);
		}
	}
	
	public void setVolume(double vol)
	{
		if(vol > 100)
		{
			vol = 100;
		}
		else if(vol < 0)
		{
			vol =0;
		}
		if(vol ==0)
		{
			muted = true;
		}
		else
		{
			muted = false;
		}
		player.setVolume(vol/100);
	}
	
	public double getVolume()
	{
		if(player != null)
			return (int)player.getVolume()*100;
		else
			return Double.NaN;
	}
	
	public void mute()
	{
		if(muted)
		{
			setVolume(vol);
			muted = false;
		}
		else
		{
			vol = player.getVolume()*100;
			setVolume(0);
			muted = true;
		}
	}
	
	public void pause()
	{
		if(paused)
		{
			paused = false;
			player.play();
		}
		else
		{
			paused = true;
			player.pause();
		}
	}
	
	public void setFile(File files)
	{
		if(player != null)
		{
			player.stop();
		}
		file = files;
		try {
			image = ImageIO.read(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip =new Media(file.toURI().toString());
		player = new MediaPlayer(clip);
	}
	
	/*
	 * playsound()
	 * 
	 * Plays the sound clip provided
	 */
	public void playSound()
	{
		player.setStartTime(new Duration(0));
		player.play();
	}
	
	public void seek(int time)
	{
		player.seek(new Duration(time*1000));
	}
	
	
	/*
	 * loopSound()
	 * 
	 * Contiously loops the sound file until you call stopSound()
	 */
	public void loopSound()
	{
		player.setAutoPlay(true);
		player.play();
	}
	/*
	 * stopSound()
	 * 
	 * Stops the sound
	 */
	public void stopSound()
	{
		player.stop();
	}
	
	public void setDuration(Duration dur)
	{
		player.setStartTime(dur);
	}
	
	public boolean isMuted()
	{
		return muted;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public boolean isInitialized()
	{
		return init;
	}
	
	public String getDurationAsMinSecString(Duration dur)
	{
		String string;
		int secs = (int)dur.toSeconds();
		int mins = (int)dur.toMinutes();
		string = String.format("%d",mins);
		string += ":";
		string += String.format("%02d",secs-(mins*60));
		return string;
	}
	
	public Duration getCurrentDuration()
	{
		if(player != null)
		{
			return player.getCurrentTime();
		}
		return new Duration(0);
	}
	
	public Duration getTotalDuration()
	{
		if(player != null)
		{
			return player.getTotalDuration();
		}
		return new Duration(0);
	}
	
	public boolean isSoundDone()
	{
		if(file == null)
		{
			return false;
		}
		return player.getCurrentTime().greaterThanOrEqualTo(player.getStopTime());
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	
}
