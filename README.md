# A-Portable-File-System-using-Linked-Allocation-Method

Download the PFS.jar file and run in ecliplse IDE.
You can download the project and run in IDE. 

Codes are available at project2/src/

Copy or create your own file to process in PFS.

You can create your PFS and which allocates 10KB to it with block size of 256 KB each. You are able run with list of below commands given as input 

> open PFSfile
> put localfile
> get file
> dir
> putr file Remarks
> rm file
> kill PFSfile
> putc file data
> run file
> quit

Assumption: I have kept FCB block fixed to 4, so only 4 file can be saved in one PFS.If you want modify it, you can change the same in FCB.java src file.

