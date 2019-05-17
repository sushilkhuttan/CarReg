package OnlineCarRegTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class fileProperties {
	File file;
	File[] files;
	public fileProperties(String Path) {

			file = new File(Path);
			files = file.listFiles();
		}
		
		
	public void getFileSize() throws IOException {	
				double k=0;
				String extn= "abc";
				for (int i=0; i<files.length; i++)
				{
					if(files[i].isFile()) {
					String name = files[i].getName();
					System.out.println("FileName of file number "+ (i+1) +" is "+name);
					k = files[i].length();
					System.out.println("size in bytes "+k);
					extn = name.substring(name.lastIndexOf("."));
					System.out.println("FileName extension of is "+extn);
					InputStream is = new BufferedInputStream(new FileInputStream(files[i]));
					String mimeType = URLConnection.guessContentTypeFromStream(is);
					System.out.println("Mime Type is " + mimeType);
					System.out.println("------------------------");
					}
				}
	}
}

