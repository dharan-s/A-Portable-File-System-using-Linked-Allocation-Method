# A-Portable-File-System-using-Linked-Allocation-Method

Implemented a Portable File System (PFS) with Linked Allocation Method, which can perform “Allocate a file”, and “Move files from the Windows file system into my file.” I created my own directory structure, allocation table, etc. inside my file. Move files back out of my file to the Windows file system or Linux/Unix file system.

Download the PFS.jar file and run in ecliplse IDE.
You can download the project and run in IDE. 

Codes are available at project2/src/

Copy or create your own file to process in PFS.

You can create your PFS and which allocates 10KB to it with block size of 256 KB each. You are able run with list of below commands given as input 

-> open PFSfile - Allocate a new 10 KByte "PFS" file if it does not already exist. If it does exist, begin using it for further commands.

-> put localfile - Copy the Windows (or Unix/Linux) file "myfile" into your PFS file. 


> open PFSfile - Allocate a new 10 KByte "PFS" file if it does not already exist. If it does exist, begin using it for further commands.
> put localfile - Copy the Windows (or Unix/Linux) file "myfile" into your PFS file.
> get myfile - Extract file "myfile" from your PFS file and copy it to the current Windows (or Unix/Linux) directory.
> dir - List the files in your PFS file.
> putr myfile Remarks - Append remarks to the directory entry for myfile in your PFS file.
> rm myfile - Delete "myfile" from your PFS file.
> kill PFSfile - Delete the PFSfile from the Windows file system.
> putc myfile data - Append data to the myfile
> run file - Associate a program to be run against this file.
> quit - Exit PFS.

Assumption: I have kept FCB block fixed to 4, so only 4 file can be saved in one PFS.If you want modify it, you can change the same in FCB.java src file.

