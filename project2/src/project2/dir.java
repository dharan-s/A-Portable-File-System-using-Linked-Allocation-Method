package project2;

import java.io.IOException;
import java.io.RandomAccessFile;
/* Class : dir
 * Description : List the files in your PFS file.   
 */
public class dir {

	/*
	 * Methods Name: dir_cmd(RandomAccessFile raf)
	 * Return Type: void
	 * Description: List the files in your PFS file.
	 */
	public void dir_cmd(RandomAccessFile raf) {
		// TODO Auto-generated method stub
		String s="";
	
		for (int i=1;i<5;i++)
		{
		try {
		    
             raf.seek(i*256);
            // Read a character
            String s1="";
            byte ch;
            while((ch=raf.readByte()) != 0)
            {
          		s1=s1+String.valueOf((char) ch);
            }
      
           s=s1;
       	    
 
        } catch (IOException e) {
            System.out.println("IOException:");
            e.printStackTrace();
        }
		if (s != "")
		{
			int ii=0;
		String[] inode_list = s.split(",");		
		ii=inode_list.length;
		if(ii==6)
		System.out.println(inode_list[0]+"\t"+inode_list[1]+"\t"+inode_list[2]+"\t"+inode_list[3]);
		else
			System.out.println(inode_list[0]+"\t"+inode_list[1]+"\t"+inode_list[2]+"\t"+inode_list[3]+"\t"+inode_list[6]);	
		}
		
		}
	}

}
