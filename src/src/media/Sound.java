package src.media;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
