package src.saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaverLoader implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4207472138172171168L;
	static FileOutputStream ofile;
	static FileInputStream ifile;
	static ObjectOutputStream oobject;
	static ObjectInputStream iobject;
	static File file;
	public SaverLoader()
	{
		file = new File("save.sav");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean createFile()
	{
		if(!file.exists())
		{
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean fileCreated()
	{
		return file.exists();
	}
	
	public static boolean save(Serializable ob)
	{
		if(file.exists())
		{
			try
			{
				ofile = new FileOutputStream(file);
				oobject = new ObjectOutputStream(ofile);
				oobject.writeObject(ob);
				oobject.flush();
				oobject.close();
				ofile.close();
				return true;
			}catch(IOException e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
	
	public Object loadObject()
	{
		Object obj = null;
		if(file.exists())
		{
			try {
				ifile = new FileInputStream(file);
				iobject = new ObjectInputStream(ifile);
				obj = iobject.readObject();
				iobject.close();
				ifile.close();
				return obj;
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
}
