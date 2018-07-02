package project2;

import java.io.IOException;
import java.io.RandomAccessFile;

/* Class : get
 * Description : Extract file "myfile" from your PFS file and copy it to the  Windows (or Unix/Linux) directory.  
 */
public class get {

	/*
	 * Methods Name: get_cmd(RandomAccessFile f1,String fname, FCB fb, inode io)
	 * Return Type: void
	 * Description: Extract file "myfile" from your PFS file and copy it to the  Windows (or Unix/Linux) directory.
	 */
	public void get_cmd(RandomAccessFile f1,String fname, FCB fb, inode io) throws IOException {
		// TODO Auto-generated method stub
		int i=1,f=0;
		while (i !=5)
		{
		String inode_r=io.inodeRead(f1,fname,i); 		
		boolean res= inode_r.toLowerCase().contains(fname.toLowerCase()); // check fname exists in current inode.
		if(res==true)
		{
			f=1;
			i=4;
		int l1=inode_r.length();
		String[] inode_list = inode_r.split(",");		
		int st=Integer.valueOf(inode_list[4]);
		int end=Integer.valueOf(inode_list[5]);
	
		io.getValue(f1,st,end); // display the file in console.
		}
		i++;
		}
		if(f==0)
			System.out.println("Invalid file_name");
	}

}
