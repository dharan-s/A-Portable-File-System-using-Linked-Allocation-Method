package project2;

import java.io.IOException;
import java.io.RandomAccessFile;
/* Class : putr
 * Description : Append remarks to the directory entry for myfile in your PFS file. 
 */
public class putr {

	/*
	 * Methods Name: putr_cmd(RandomAccessFile f1, String fname, String str,inode io)
	 * Return Type: void
	 * Description: Append remarks to the directory entry for myfile in your PFS file.  
	 */
	public void putr_cmd(RandomAccessFile f1, String fname, String str,inode io) throws IOException {
		// TODO Auto-generated method stub
		int i=1,f=0;
		while (i !=5)
		{
		String inode_r=io.inodeRead(f1,fname,i);
		
		boolean res= inode_r.toLowerCase().contains(fname.toLowerCase());
		if(res==true)
		{
			f=1;
			int block=i;
			i=4;
			inode_r=inode_r+","+str;
			byte[] b;
			b=inode_r.getBytes();
			f1.seek(block*256);
			f1.write(b);
			System.out.println("Remarks added");
		}
		i++;
		}
		if(f==0)
			System.out.println("Invalid file_name");
	}
		
	}


