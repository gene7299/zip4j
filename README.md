# zip4j
A simple zip tool for java

How to use?

1.Something to zip

    public static boolean genZipFile(final String source,final String dest) {
    	
    	System.out.println("Generating Zip file ...");
    	
    	boolean ret = false; 
    	
    	ret = Zip4jtool.AddFolder(source,dest,"password");
    	if(ret == true)
    	{
    		 System.out.println( "Success !! " +  dest);
    	}else
    	{
    		 System.err.println( "Generate Zip file error!! Source="+ source + "   ;   Dest=" + dest);
    	}
    	return ret;
    }


2.Extract zip file to folder

    public static boolean extratZipFile(final String source,final String dest) {
    	
    	System.out.println("Extracting Zip file ...");
    	
    	boolean ret = false; 
    	
    	ret = Zip4jtool.ExtractByLoopAllFiles(source,dest,"password");
    	if(ret == true)
    	{
    		 System.out.println( "Success !! " +  dest);
    	}else
    	{
    		 System.err.println( "Extrat Zip file error!! Source="+ source + "   ;   Dest=" + dest);
    	}
    	return ret;
    }
