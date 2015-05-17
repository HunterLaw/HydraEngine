package src.media;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound
{
	File file;
	AudioClip clip;
	static File loc;
	/*
	 * File must be in the .wav format to work correctly
	 */
	public Sound(File files)
	{
		file = files;
		try {
			clip =Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * playsound()
	 * 
	 * Plays the sound clip provided
	 */
	public void playSound()
	{
		clip.play();
	}
	/*
	 * loopSound()
	 * 
	 * Contiously loops the sound file until you call stopSound()
	 */
	public void loopSound()
	{
		clip.loop();
	}
	/*
	 * stopSound()
	 * 
	 * Stops the sound
	 */
	public void stopSound()
	{
		clip.stop();
	}
	
}
