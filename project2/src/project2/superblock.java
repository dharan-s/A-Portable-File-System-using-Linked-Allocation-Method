package project2;


import java.io.IOException;
import java.io.RandomAccessFile;

/* Class : superblock
 * Description : This includes the operation performed in superblock. 
 */
public class superblock {

	/*
	 * Methods Name: sReadAvail(RandomAccessFile f1)
	 * Return Type: String
	 * Description: It will read the availability of block in PFS.
	 */
	String sReadAvail(RandomAccessFile f1) throws IOException
	{
		String s="";
		char ch;
		for (int i=3;i<5;i++)
		{
					
			f1.seek(i);
		ch=(char)f1.readByte();
		s=s+ch;
		} 
		return s;
	}
	
	/*
	 * Methods Name: sWrite(RandomAccessFile f1,String content)
	 * Return Type: void
	 * Description: It will write the availability of block in PFS.
	 */
	void sWrite(RandomAccessFile f1,String content) throws IOException
	{
		byte buf[];
		f1.seek(3);
		buf=content.getBytes();
		f1.write(buf);
	
	}
}
