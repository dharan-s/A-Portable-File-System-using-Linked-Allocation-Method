package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*  Class: Open
 *  Description: Allocate a new 10 KByte "PFS" file if it does not already exist. If it does exist, begin using it for further commands. 
 *  Superblock is created. Total blocks : 40, Available : 35
 */

public class open {

	public RandomAccessFile open_cmd(String fname) throws IOException
	{
		File f = new File(fname);
		RandomAccessFile f1;
		if(f.exists() && !f.isDirectory()) { 
		   f1= new RandomAccessFile(f,"rw");
		}
		else
		{
			byte[] buf;
			File file=new File(fname);
			 f1= new RandomAccessFile(file,"rw");
			byte[] b=new byte[10000];
			f1.setLength(b.length);
			System.out.println("File Creaated success");
			String s="14035";
			buf=s.getBytes();
			f1.write(buf);
				}
		return f1;
	}
	

}
