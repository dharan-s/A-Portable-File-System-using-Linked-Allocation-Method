package project2;

import java.io.IOException;
import java.io.RandomAccessFile;
/* Class : put
 * Description : Copy the Windows (or Unix/Linux) file "myfile" into your PFS file. 
 */
public class put {

	
	/*
	 * Methods Name: put_cmd(RandomAccessFile f1,String fname,FCB fb,inode io)
	 * Return Type: int
	 * Description: Copy the Windows (or Unix/Linux) file "myfile" into your PFS file.
	 */
	public int put_cmd(RandomAccessFile f1,String fname,FCB fb,inode io) throws IOException {
		// TODO Auto-generated method stub
		
		String s;
		boolean bool=true;
		int len,inode_block,block_no,st_block=0,end_block=0,rseek=0,ret=1;
		superblock sp=new superblock();
		s=sp.sReadAvail(f1); // reading avaible block.
		int avail=Integer.valueOf(s);
		
		s=fb.fcb_check(fname, avail); // checking block availbility.
		if(s!="NA")
		{
			len=(int) fb.getLength(fname);
			sp.sWrite(f1,s);
			inode_block=fb.fcb_block(f1,1,4); // check inode block availbility 
			if(inode_block!=0)
			{
				for (int i=inode_block-1;i>0;i--) // block to check whether file already exist in PFS.
				{
					
					String inode_r=io.inodeRead(f1,fname,i);
					String[] inod=inode_r.split(",");
					if(inod[0].equals(fname))
					bool=false;	
				}
				if(bool==true)
				{
				String inode_s=	fb.fcb_create(fname, inode_block); // inode block creation
			
			int f=0,j=0,len_div=0,next_block,prev_block=0;
			while(len_div!=-1)
			{
			block_no=fb.fcb_block(f1,5,39); // getting free block
			if(f==0)
			{
			 st_block=block_no;
			 prev_block=block_no;
			 fb.writeBlock(f1,fname,block_no,rseek,len); // wrting start data into block.
			len=len-252;
			f=1;
			}
				else // writing data and pointer to blocks.
				{   
					next_block=block_no;
		            fb.writePoint(f1,prev_block,next_block);			
					rseek=rseek+252;
					fb.writeBlock(f1,fname,block_no,rseek,len);
					prev_block=block_no;
					len=len-252;
							}
				if (len<0) // writing end block data + pointer
				{
					len_div=-1;
					fb.writePoint(f1,prev_block,-1);
					end_block=block_no;
				}
			}
			
			inode_s=inode_s+","+st_block+","+end_block;
		    io.inodeWrite(f1,inode_block, inode_s); // inode update with start and end block.
		    System.out.println("File copied to PFS");
			}
				else
					System.out.println("Filename already exists");
			}
			
			else
				System.out.println("Not sufficient inode block available");
		}
		else
		{
			System.out.println("Not sufficient memory available");
			System.out.println("Creating new PFS");
			ret=0;
		}	
			return ret;
	}
	
}
