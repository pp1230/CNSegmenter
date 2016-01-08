package cn.libanalyze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {

	public void write(String content, String path){
		if(content!=null){
			  File file = new File(path);

		  try (FileOutputStream fop = new FileOutputStream(file)) {

		   // if file doesn't exists, then create it
		   if (!file.exists()) {
		    file.createNewFile();
		   }

		   // get the content in bytes
		   byte[] contentInBytes = content.getBytes();

		   fop.write(contentInBytes);
		   fop.flush();
		   fop.close();

		   System.out.println("Done");

		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		}
		else
			System.out.println("no content!");
	}
	
}
