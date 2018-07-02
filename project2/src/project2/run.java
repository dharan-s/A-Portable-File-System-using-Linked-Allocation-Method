package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/* Class : run
 * Description : Extract "myfile" from the PFS and run it with the associated command.   
 */
public class run {

	/*
	 * Methods Name: rm_cmd(RandomAccessFile f1,String fname,FCB fb,inode io,String p_file)
	 * Return Type: void
	 * Description: Extract "myfile" from the PFS and run it with the associated command. 
	 */
	public void run_cmd(RandomAccessFile f1, String fname, inode io) throws IOException {

		int i=1,f=0;
		while (i !=5)
		{
		String inode_r=io.inodeRead(f1,fname,i);
		boolean res= inode_r.toLowerCase().contains(fname.toLowerCase());
		if(res==true)
		{
			f=1;
			i=4;
		String[] inode_list = inode_r.split(",");		
		int st=Integer.valueOf(inode_list[4]);
		int end=Integer.valueOf(inode_list[5]);
		String value="";
		
		 int pointer,ff=0,cursor;
		 int block,nxt_block;
		 block=st;
		 System.out.println("");
		 String s1="";
		  
		 while(block!=-1)
		 {
			 
			 	try {
		            f1.seek(block*256);
		             s1="";
		            byte ch;
		            char c;
		            
		            while((ch=f1.readByte()) != 0)
		            {
		            		s1=s1+String.valueOf((char) ch);
		            }
		            	pointer=Integer.valueOf(s1);
		            	cursor=(block*256)+4;
		           	
		         for(int ii=0;ii<251;ii++)
		            {
		            	f1.seek(cursor+ii);
		            	ch=f1.readByte();
		            	c=(char) ch;
		            	value=value+c;
		            }
		              block=pointer;
		         	               
		        } catch (IOException e) {
		            System.out.println("IOException:");
		            e.printStackTrace();
		        }
		 } 
		/* creating new file and copy the content of fname which needs to be dispalyed. */
		 Path p2 = Paths.get("output.txt");
		 Files.delete(p2);
		File file=new File("output.txt");
		RandomAccessFile f2=new RandomAccessFile(file, "rw");
		
		byte[] b;
		b=value.getBytes();
		f2.write(b);
		f2.close();
		// open the file content in notepad
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "output.txt");
		pb.start();
		
		}
		i++;
		}
		if(f==0)
			System.out.println("Invalid file_name");
		
	}

}
