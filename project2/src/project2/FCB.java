package project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Class : FCB
 * Description : This includes the operation performed in FCB block. 
 * 
 */

public class FCB {
       long v;
	/*
	 * Methods Name: fcb_check(String fname, int avail)
	 * Return Type: String
	 * Description: It will calculate the file size and check with PFS whether sufficient blocks are available to copy the in PFS.
	 */
	String fcb_check(String fname, int avail) throws IOException
	{
		
		String s;
		 v=getLength(fname);
		int b_a=(int) (v/252);
		int b=(int) (v%252);
		if (b!=0)
			b_a +=1;
		byte[] buf=new byte[256];
		avail=avail-b_a;
		if (avail >0)
		
		 s=String.valueOf(avail);
		else
		s = "NA";
		return s;
	}
	
	/*
	 * Methods Name: getLength(String fname)
	 * Return Type: long
	 * Description: It will calculate the file size.
	 */
	long getLength(String fname)
	{
		File f=new File(fname);
		 v=f.length();
		 return v;
	}
	
	/*
	 * Methods Name: fcb_create(String fname, int block)
	 * Return Type: String
	 * Description: Creation of FCB block with attributes fname,file size,TIme,date
	 */
	String fcb_create(String fname, int block) throws IOException
	{
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat formatDate = new SimpleDateFormat("MMMM dd");
				
		String fc=fname+","+v+","+formatTime.format(new Date()).toString()+","+formatDate.format(new Date()).toString();
		return fc;
		
	}
	
	/*
	 * Methods Name: fcb_block(RandomAccessFile f1,int i,int j)
	 * Return Type: int
	 * Description: Check the FCB block whether it is free to write inode or data.
	 */
	int fcb_block(RandomAccessFile f1,int i,int j) throws IOException
	{
		f1.seek((i*256)+5);
		while( f1.readByte() != 0 && i < j)      //4
		{
		  i++;
		  f1.seek((i*256)+5);
		}
		if (i==j)                                //3
		    i=0;
		return i;
	}
	
	/*
	 * Methods Name: writeBlock(RandomAccessFile f2,String fname,int block,int rseek,int len)
	 * Return Type: void
	 * Description: Write the data from local file to PFS to a block.
	 */
	void writeBlock(RandomAccessFile f2,String fname,int block,int rseek,int len) throws IOException
	{
		char ch;
		int j=252;
		String s="";
		File rfile = new File(fname);
		RandomAccessFile f1= new RandomAccessFile(rfile,"rw");
		byte buf[];
		if(len<252)
			j=len%252;
		for (int i=0;i<j;i++)
		{
			f1.seek((rseek)+i);
		ch=(char) f1.readByte();
		s=s+ch;
		} 
		buf=s.getBytes();
		f2.seek((block*256)+4);
		f2.write(buf);
	}
	
	/*
	 * Methods Name: writePoint(RandomAccessFile f1,int prev_block, int next_block)
	 * Return Type: void
	 * Description: Write the pointer to a block. (Pointer will point to next block value to access the data or -1 to last block of a file)
	 */
	public void writePoint(RandomAccessFile f1,int prev_block, int next_block) throws IOException {
		
		byte buf[];
		f1.seek(prev_block*256);
		String fc=String.valueOf(next_block);
		buf=fc.getBytes();
		f1.write(buf);
		
	}

}
