package project2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/* Class : rm
 * Description : Delete "myfile" from your PFS file. 
 */
public class rm {
	/*
	 * Methods Name: rm_cmd(RandomAccessFile f1,String fname,FCB fb,inode io,String p_file)
	 * Return Type: int
	 * Description: Delete "myfile" from your PFS file. 
	 */
	public int rm_cmd(RandomAccessFile f1,String fname,FCB fb,inode io,String p_file) throws IOException {
		// TODO Auto-generated method stub
		int i=1,f=0,inode_block=0,file_size=0,st=0,end=0,free_block=0,rm_flag=0;
		ArrayList<Integer> list = new ArrayList<Integer>();  
		while (i !=5)
		{
		String inode_r=io.inodeRead(f1,fname,i);
		boolean res= inode_r.toLowerCase().contains(fname.toLowerCase());
		if(res==true) // if inode contaisn file
		{
			inode_block=i;
			i=4;
			f=1;
		String[] inode_list = inode_r.split(",");		
		file_size=Integer.valueOf(inode_list[1]);
		st=Integer.valueOf(inode_list[4]);
		end=Integer.valueOf(inode_list[5]);
		
		list.add(inode_block); // adding inode block which has file information to list
		list.add(st); // adding start block.
			}
		i++;
		}
		if(f==0)
			System.out.println("Invalid file_name");
		else
		{
			int block_size=file_size/252;
			file_size=file_size%252;
			if(file_size >0)
				block_size +=1;
			free_block=block_size;
			block_size +=1; // adding inode block;
		remove_data(f1,st,end,list);
		remove(f1,list,p_file,free_block);
		rm_flag=1;
		}
		return rm_flag;
	}
	
	/*
	 * Methods Name: rm_cmd(RandomAccessFile f1,String fname,FCB fb,inode io,String p_file)
	 * Return Type: int
	 * Description: adding all the linked bloks in a list
	 */
	void remove_data(RandomAccessFile raf,int st,int end,ArrayList<Integer> list)
	{
		int pointer;
		int block=st;
		while(block!=-1)
		 {
			 
			 	try {
		            raf.seek(block*256);
		         
		         String s1="";
		            byte ch;
		                       
		            while((ch=raf.readByte()) != 0)
		            {
		            		s1=s1+String.valueOf((char) ch);
		            }
		            	pointer=Integer.valueOf(s1);
		  
		                 block=pointer;
		             if(list.contains(pointer)==false)
		            	 list.add(pointer);
		        
		        } catch (IOException e) {
		            System.out.println("IOException:");
		            e.printStackTrace();
		        }
		 }
	}
	
	/*
	 * Methods Name: rm_cmd(RandomAccessFile f1,String fname,FCB fb,inode io,String p_file)
	 * Return Type: int
	 * Description: Delete "myfile" from your PFS file. 
	 */
	void remove(RandomAccessFile f1,ArrayList<Integer> list,String p_file,int free_block) throws IOException
	{
		File file=new File("t.txt");
		RandomAccessFile f2=new RandomAccessFile(file, "rw");
		boolean result;
		String s="";
		byte[] b;
		for (int i=0;i<39;i++)
		{
			result=list.contains(i);
			if (result == false) // if the block is not present, copy the content to a new file.
			{
				s="";
				byte ch;
					
				for(int j=0;j<255;j++)
				{
					f1.seek((i*256)+j);
					ch=f1.readByte();
					s=s+String.valueOf((char) ch);
				}
				
				b=s.getBytes();
				f2.seek(i*256);
				f2.write(b);
			}
		}
		f1.close();
		f2.close();
		
		Path p2 = Paths.get(p_file);
		try {

		    Files.delete(p2); // deletion of old file.
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", p2);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", p2);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		Path source = Paths.get("t.txt");
		Files.move(source, source.resolveSibling(p_file)); // new file rename
		superblock sp=new superblock();
		
		File fi3=new File(p_file);
		RandomAccessFile f3=new RandomAccessFile(fi3,"rw");
		f1=f3;
		
		s=sp.sReadAvail(f1);  
		int avail=Integer.valueOf(s);
		System.out.println("File Removed");
		avail=avail+free_block;
		sp.sWrite(f1,String.valueOf(avail));  // updating the superblock with availeble block.
	}

}
