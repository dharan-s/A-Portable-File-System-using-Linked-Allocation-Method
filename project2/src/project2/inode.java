package project2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
/* Class : inode
 * Description : This includes the operation performed in inode and data block. 
 */
public class inode {
	
	/*
	 * Methods Name: inodeRead(RandomAccessFile raf,String fname,int start)
	 * Return Type: String
	 * Description: It will read the inode block for the given file.
	 */
	String inodeRead(RandomAccessFile raf,String fname,int start) throws IOException
	{
		
		String s="";
		int f=1;
		try {
		    
             raf.seek(start*256);
            // Read a character
            String s1="";
            byte ch;
            char c;
            while((ch=raf.readByte()) != 0)
            {
          		s1=s1+String.valueOf((char) ch);
            }
                  s=s1;
           
 
        } catch (IOException e) {
            System.out.println("IOException:");
            e.printStackTrace();
        }
    
		
		 return s;
	}

	/*
	 * Methods Name: inodeWrite(RandomAccessFile f1,int block,String s)
	 * Return Type: String
	 * Description: It will write the inode block for the specified block..
	 */
	void inodeWrite(RandomAccessFile f1,int block,String s) throws IOException
	{
		byte[] buf;
		buf=s.getBytes();
		f1.seek((block*256));
		f1.write(buf);
	
	}
	
	/*
	 * Methods Name: getValue(RandomAccessFile raf,int start,int end)
	 * Return Type: void
	 * Description: It will print the data in blocks from start till end block.
	 */
	void getValue(RandomAccessFile raf,int start,int end) throws IOException
	{
	 int pointer,f=0,cursor;
	 int block,nxt_block;
	 block=start;
	 System.out.println("");
	 String s1="";
	  
	 while(block!=-1)
	 {
		 
		 	try {
	            raf.seek(block*256);
	             s1="";
	            byte ch;
	            char c;
	            
	            while((ch=raf.readByte()) != 0)
	            {
	            		s1=s1+String.valueOf((char) ch);
	            }
	            	pointer=Integer.valueOf(s1);
	               	cursor=(block*256)+4;
	      
	           	
	         for(int i=0;i<251;i++)
	            {
	            	raf.seek(cursor+i);
	            	ch=raf.readByte();
	            	c=(char) ch;
	            	System.out.print(c);
	            }
	            block=pointer;
	     
	               
	        } catch (IOException e) {
	            System.out.println("IOException:");
	            e.printStackTrace();
	        }
	 }  
	} 
	
}
