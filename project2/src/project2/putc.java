package project2;

import java.io.IOException;
import java.io.RandomAccessFile;

/* Class : putc
 * Description : Associate a program to be run against this file.  
 */
public class putc {

	/*
	 * Methods Name: putc_cmd(RandomAccessFile f1, String fname, String str, inode io)
	 * Return Type: void
	 * Description: Associate a program to be run against this file. 
	 */
	public void putc_cmd(RandomAccessFile f1, String fname, String str, inode io) throws IOException {
		// TODO Auto-generated method stub
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
			int block=Integer.valueOf(inode_list[5]); // end block.
			int seekp=(block*256)+4;
			f1.seek(seekp);
		 	  int cnt=0,j=1;
	            byte ch;
	                       
	            while((ch=f1.readByte()) != 0) // seek to eof of that block.
	            {
	            	f1.seek(seekp+j);
	            	cnt++;
	            	j++;
	         
	            }
	             j--;
				byte[] b;
				b=str.getBytes();
				f1.seek(seekp+j);
				f1.write(b);
				System.out.println("text added");
	            			
		}
		i++;
		}
		if(f==0)
			System.out.println("Invalid file_name");
	}

}
