package project2;

import java.awt.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String p_file="",fname="",input,ex="";
		BufferedReader br = null;
		int rm_flag=0;
		int ret=1,z=1,z1=0;
		//boolean command;
		int op=0;
		//Creation of inode, open, FCB, File, RandomAccessFile class.
		inode io=new inode();
		open o=new open();
		FCB fb=new FCB();
		File file=new File("test1.txt");
		RandomAccessFile f1=new RandomAccessFile(file,"rw");
		String[] inp = null;
		String[] p_fm= null;		
		while(ex!="quit")
		{
		
			if(rm_flag==1) // open PFS after rm command.
			{
				inp[0]="open";
				inp[1]=p_file;
				rm_flag=0;
				
			}
			else if(ret!=0 )
			{
		System.out.println("Dharan_PFS > ");
		BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
		input = brr.readLine();
		inp = input.split(" ");
			}
			else if (ret==0)  // creation of new PFS, if the file is too large to fit the current PFS.
			{   
				if(z1==1)
				{
				ret=1;
				z1=0;
				inp[0]="put";
				inp[1]=fname;
				}
				else
				{
				p_fm=p_file.split("\\.");
				p_file=p_fm[0]+z+"."+p_fm[1];
				inp[0]="open";
				inp[1]=p_file;
				z++;
				z1++;
				}
				
			}
			
			
		switch(inp[0])
		{
			case "open":  //Allocate a new 10 KByte "PFS" file if it does not already exist.     
				{
				p_file=inp[1];
				f1=o.open_cmd(inp[1]);
				op=1;
				break;
			}
			case "put": // Copy the Windows (or Unix/Linux) file "myfile" into your PFS file. 
			{
				if(op==1)
				{
				fname=inp[1];
				put p=new put();
				ret=p.put_cmd(f1,inp[1],fb,io);
				}
				else
					System.out.println("PFS is not opened");
				break;
			}
			case "get":
			{
				if(op==1) //Extract file "myfile" from your PFS file and copy it to the current Windows (or Unix/Linux) directory. 
				{
				get g=new get();
				g.get_cmd(f1,inp[1],fb,io);
				System.out.println("Dharan_PFS > ");
				}
				else
					System.out.println("PFS is not opened");
						break;	
						
			}
			case "dir": //List the files in your PFS file. 
			{
				if(op==1)
				{
				dir d=new dir();
				d.dir_cmd(f1);
				}
				else
					System.out.println("PFS is not opened");
						break;
						
			}
			case "rm": //Delete "myfile" from your PFS file.
			{
				if(op==1)
				{
				rm rem=new rm();
				rm_flag=rem.rm_cmd(f1,inp[1],fb,io,p_file);
				}
				else
					System.out.println("PFS is not opened");
						break;
			}
			case "kill": //Delete the PFSfile from the Windows file system. 
			{
				f1.close();
				Path p2 = Paths.get(inp[1]);
				try {

				    Files.delete(p2);
				    System.out.println("PFS deleted");
				} catch (NoSuchFileException x) {
				    System.err.format("%s: no such" + " file or directory%n", p2);
				} catch (DirectoryNotEmptyException x) {
				    System.err.format("%s not empty%n", p2);
				} catch (IOException x) {
				    // File permission problems are caught here.
				    System.err.println(x);
				}
				break;
			}
			
			case "putr": //Append remarks to the directory entry for myfile in  your PFS file.
			{
				if(op==1)
				{
				putr pr=new putr();
				pr.putr_cmd(f1,inp[1],inp[2],io);
				}
				else
					System.out.println("PFS is not opened");
						break;
			}
			
			case "putc": // Associate a program to be run against this file. 
			{
				if(op==1)
				{
				putc pc=new putc();
				pc.putc_cmd(f1,inp[1],inp[2],io);
				}
				else
					System.out.println("PFS is not opened");
						break;
			}
			
			case "run": //Extract "myfile" from the PFS and run it with notepad.
			{
				if(op==1)
				{
				run r=new run();
				r.run_cmd(f1,inp[1],io);
				}
				else
					System.out.println("PFS is not opened");
						break;
			}
			
			case "quit": // Exit PFS.
			{
				ex="quit";
				break;
			}
			default:
				System.out.println("Invalid Command");
			
		}
			
		}
				
	}
	

}
